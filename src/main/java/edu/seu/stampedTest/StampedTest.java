package edu.seu.stampedTest;

public class StampedTest {
    public static void main(String[] args) throws InterruptedException {
        DataContainerStamped container = new DataContainerStamped(1);
        new Thread(() -> {
            container.read(1);
        }, "t1").start();
        Thread.sleep(500);
        new Thread(() -> {
            container.read(0);
        }, "t2").start();
        // 两个线程并发，且为乐观读操作
    }
}
