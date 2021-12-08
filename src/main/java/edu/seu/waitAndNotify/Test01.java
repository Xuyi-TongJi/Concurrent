package edu.seu.waitAndNotify;

public class Test01 {
    final static Object obj = new Object();

    public void method01() throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                System.out.println("t1执行...");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("其他代码...");
            }
        }, "t1").start();

        new Thread(() ->{
            synchronized (obj){
                System.out.println("t2执行...");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("其他代码...");
            }
        }, "t2").start();

        Thread.sleep(2);
        System.out.println("唤醒obj上其他线程...");
        synchronized (obj){
            obj.notifyAll();
        }
    }
}
