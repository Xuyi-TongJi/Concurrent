package edu.seu.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("task finish");
        }); // 第三个参数为runnable，用于其他任务完成时执行
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            service.submit(() -> {
                System.out.println("Thread1 begin");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    barrier.await(); // 2-1 = 1 当前线程阻塞
                    System.out.println("Thread 1 finish");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            service.submit(() -> {
                System.out.println("Thread2 begin");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    barrier.await();  // 1-1 = 0 所有阻塞线程恢复运行
                    System.out.println("Thread 2 finish");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
