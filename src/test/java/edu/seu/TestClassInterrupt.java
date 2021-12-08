package edu.seu;

import org.junit.Test;

public class TestClassInterrupt {
    @Test
    public void method01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {

            }
        }, "t1");
        t1.start();
        Thread.sleep(1000); // main线程睡眠一秒后，打断t1
        System.out.println("interrupt t1");
        t1.interrupt(); // 执行该方法，t1线程并不会结束运行
    }

    @Test
    public void monitorTest() throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        Thread.sleep(3500);
        tpt.stop(); // 主线程睡眠3.5秒后，执行打断线程
    }
}
