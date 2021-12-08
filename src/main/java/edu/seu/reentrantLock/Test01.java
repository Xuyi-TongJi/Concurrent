package edu.seu.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Test01 {
    private final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock(); // 获取锁
        try {
            System.out.println("enter main");
            // 再次调用lock.lock() 即发生锁重入
            method01();
            method02();
        } finally {
            lock.unlock();
        }
    }

    public static void method01(){
        lock.lock();
        try{
            System.out.println("enter method01");
        } finally {
            lock.unlock();
        }
    }

    public static void method02(){
        lock.lock();
        try{
            System.out.println("enter method02");
        } finally {
            lock.unlock();
        }
    }

}
