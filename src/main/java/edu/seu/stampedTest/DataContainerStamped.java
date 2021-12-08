package edu.seu.stampedTest;

import java.util.concurrent.locks.StampedLock;

public class DataContainerStamped {
    private int data;
    private final StampedLock lock = new StampedLock();

    public DataContainerStamped(int data) {
        this.data = data;
    }

    public int read(int readTime){ // readTime用于测试
        long stamp = lock.tryOptimisticRead();
        System.out.println("optimistic read locking..." + stamp);
        try {
            Thread.sleep(readTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (lock.validate(stamp)){
            System.out.println("read finish in optimistic lock..." + stamp);
            return data;
        }
        // 校验失败，升级为读锁
        try{
            stamp = lock.readLock();
            System.out.println("read lock..." + stamp);
            try {
                Thread.sleep(readTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        } finally{
            System.out.println("read unlock..." + stamp);
            lock.unlockRead(stamp);
        }
    }

    public void write(int newData){
        long stamp = lock.writeLock();;
        System.out.println("write lock..." + stamp);
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = newData;
        } finally {
            System.out.println("write unlock..." + stamp);
            lock.unlockWrite(stamp);
        }
    }
}
