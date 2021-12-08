package edu.seu.waitAndNotify;

import static java.lang.Thread.sleep;

public class Test02Wait {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakenOut = false;

    // 可能引起虚假唤醒：可以使用notifyAll()解决
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room){
                if (!hasCigarette){
                    try {
                        System.out.println("没烟！");
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (hasCigarette){
                    System.out.println("小南开始工作！");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room){
                    System.out.println("其他线程开始工作！");
                }
            }, "其他人").start();
        }

        sleep(1);
        // 送烟线程
        new Thread(() -> {
            // 即使加synchronized(room)，送烟进程也无法执行
            synchronized (room) {
                hasCigarette = true;
                System.out.println("烟到了");
                room.notify();
            }
        }, "送烟线程").start();
    }
}
