package edu.seu.aqs;

public class TestAqs {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() ->{
            lock.lock();
            try {
                try {
                    System.out.println("t1,running");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally{
                lock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t2,running");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
