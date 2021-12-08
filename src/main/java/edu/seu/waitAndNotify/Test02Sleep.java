package edu.seu.waitAndNotify;

import static java.lang.Thread.sleep;

public class Test02Sleep {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakenOut = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room){
                // sleep不会释放对象锁，其他人不能占用该资源
                if (!hasCigarette){
                    try {
                        System.out.println("没烟！");
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("有烟吗" + hasCigarette);
                if (hasCigarette){
                    System.out.println("开始工作！");
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
            hasCigarette = true;
            System.out.println("烟到了");
        }, "送烟线程").start();
    }
}
