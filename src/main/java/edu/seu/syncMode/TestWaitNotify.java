package edu.seu.syncMode;

public class TestWaitNotify {
    public static void main(String[] args) {
        // 公共对象WaitNotify
        WaitNotify wn = new WaitNotify(1, 3);
        new Thread(() -> {
            wn.print("a", 1, 2);
        }, "t1").start();

        new Thread(() -> {
            wn.print("b", 2, 3);
        }, "t2").start();

        new Thread(() -> {
            wn.print("c", 3, 4);
        }, "t1").start();
    }
}
