package edu.seu.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest01 {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(3);
        // 底层原理还是AQS等待
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    s.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    s.release();
                    System.out.println("end...");
                }
            }).start();
        }
    }
}
