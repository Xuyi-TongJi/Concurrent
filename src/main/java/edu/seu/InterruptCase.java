package edu.seu;

public class InterruptCase {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println("sleep....");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        System.out.println("interrupt...");
        t1.interrupt();
        System.out.println("打断标记：" + t1.isInterrupted()); // 获取打断标记
    }
}
