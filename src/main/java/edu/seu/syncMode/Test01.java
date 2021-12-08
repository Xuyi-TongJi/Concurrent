package edu.seu.syncMode;

public class Test01 {

    private static final Object lock = new Object();
    private static boolean isT2Run = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!isT2Run) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("1");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("2");
                isT2Run = true;
                lock.notifyAll();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
