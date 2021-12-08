package edu.seu.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(3);
        new Thread(() -> {
            System.out.println("begin...");
            try {
                Thread.sleep(1000);
                cdl.countDown(); // 计数减1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("begin...");
            try {
                Thread.sleep(1500);
                cdl.countDown(); // 计数减1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("begin...");
            try {
                Thread.sleep(2000);
                cdl.countDown(); // 计数减1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        cdl.await(); // 主线程等待计数减为0后，继续执行
        System.out.println("await end");
    }
}
