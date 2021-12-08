package edu.seu.syncMode;

import java.util.concurrent.locks.LockSupport;

public class Test02 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("1");
        }, "t1");
        t1.start();

        new Thread(() -> {
            System.out.println("2");
            LockSupport.unpark(t1);
        }, "t2").start();
    }
}
