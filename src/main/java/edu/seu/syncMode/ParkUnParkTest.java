package edu.seu.syncMode;

import java.util.concurrent.locks.LockSupport;

public class ParkUnParkTest {

    private static Thread t1;
    private static Thread t2;
    private static Thread t3;

    public static void main(String[] args) throws InterruptedException {
        ParkUnPark pu = new ParkUnPark(5);
        t1 = new Thread(() -> {
            pu.print("a", t2);
        }, "t1");

        t2 = new Thread(() -> {
            pu.print("b", t3);
        }, "t2");

        t3 = new Thread(() -> {
            pu.print("c", t1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);
        LockSupport.unpark(t1);
    }
}
