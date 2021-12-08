package edu.seu.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiConditionTest {

    private static final ReentrantLock Room = new ReentrantLock();
    private static final Condition waitCigarette = Room.newCondition();
    private static final Condition waitTakeout = Room.newCondition();

    private static boolean hasCigarette = false;
    private static boolean hasTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        // 等烟线程
        new Thread(() -> {
            Room.lock();
            try {
                System.out.println("有烟没");
                while (!hasCigarette) {
                    System.out.println("没烟，先等会");
                    try {
                        waitCigarette.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // hasCigarette == true
                System.out.println("有烟了，可以干活了");
            } finally {
                // 释放锁
                Room.unlock();
            }
        }, "等烟线程").start();

        new Thread(() -> {
            Room.lock();
            try{
                System.out.println("有外卖没 ");
                while (!hasTakeout){
                    System.out.println("没有外卖，先等会");
                    try {
                        waitTakeout.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // hasTakeout == true
                System.out.println("有外卖了，可以开始吃饭了");
            } finally {
                Room.unlock();
            }
        }, "等外卖线程").start();

        Thread.sleep(1000);
        new Thread(() -> {
            Room.lock();
            try{
                hasTakeout = true;
                waitTakeout.signal();
            } finally {
                Room.unlock();
            }
        }, "送外卖线程").start();

        new Thread(() -> {
            Room.lock();
            try{
                hasCigarette = true;
                waitCigarette.signal();
            } finally {
                Room.unlock();
            }
        }, "送烟线程").start();
    }
}
