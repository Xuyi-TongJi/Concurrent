package edu.seu.test.sellTickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Sell {
    public static void main(String[] args) throws InterruptedException {
    // 模拟多人买票
        TicketWindow window = new TicketWindow(10000);
        // 卖出的票数统计
        List<Integer> amountList = new Vector<>();
        // 所有线程的集合
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 3000; i++) {
            Thread thread = new Thread(() -> {
                int amount = window.sell(randomAmount());
                try {
                    Thread.sleep(randomAmount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                amountList.add(amount);
            });
            threadList.add(thread);
            thread.start();
        }
        for (Thread thread:
             threadList) {
            thread.join();
        }

        // 统计卖出的票数和剩余的票数
        System.out.println("余票数" + window.getCount());
        System.out.println("卖出的票数" + amountList.stream().mapToInt(i -> i).sum()); // 流式编程
    }

    private static final Random random = new Random();

    public static int randomAmount(){
        return random.nextInt(5) + 1;
    }
}
