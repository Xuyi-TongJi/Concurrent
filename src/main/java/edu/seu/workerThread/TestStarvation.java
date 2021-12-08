package edu.seu.workerThread;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestStarvation {
    private static final List<String> MENU = Arrays.asList("地三鲜", "宫保鸡丁", "辣子鸡丁", "烤鸡翅");
    private static final Random random = new Random();
    private static String cooking(){
        return MENU.get(random.nextInt(MENU.size()));
    }

    public static void main(String[] args) {
        ExecutorService waiterPool = Executors.newFixedThreadPool(1);
        ExecutorService cookPool = Executors.newFixedThreadPool(1);
        waiterPool.execute(() -> {
            // 1. 点餐
            System.out.println("处理点餐1");
            // 2. 做菜【必须先点完餐，等菜做好，上菜，在此期间点餐线程必须等待】
            Future<String> f = cookPool.submit(() -> {
                System.out.println("做菜1");
                return cooking();
            });
            try {
                System.out.println("上菜1" + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        // 线程池中的两个线程都去点餐，没有更多线程做菜，陷入死等，但没有发生死锁
        waiterPool.execute(() -> {
            System.out.println("处理点餐2");
            Future<String> f = cookPool.submit(() -> {
                System.out.println("做菜2");
                return cooking();
            });
            try {
                System.out.println("上菜2" + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
