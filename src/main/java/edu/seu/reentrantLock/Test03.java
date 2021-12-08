package edu.seu.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Test03 {
    private static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            // 尝试获得锁
            System.out.println("尝试获得锁");
            // 获取锁失败
            if (!lock.tryLock()){
                System.out.println("获取不到锁");
                return;
            }
            // 获得到锁
            try{
                System.out.println("获得到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        t1.start();
    }
}
