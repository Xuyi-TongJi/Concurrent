package edu.seu.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Test02 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("没有获得锁");
                e.printStackTrace();
            }
            try {
                System.out.println("获取到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        t1.start();
        Thread.sleep(1000);
        t1.interrupt(); // 打断t1
    }
}
