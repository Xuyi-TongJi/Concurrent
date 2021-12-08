package edu.seu.test.transfer;

import java.util.Random;

public class Transfer {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a.getMoney() + "-------" + b.getMoney());
        System.out.println("总余额" + (a.getMoney() + b.getMoney()));
    }

    private static final Random random = new Random();

    public static int randomAmount(){
        return random.nextInt(5) + 1;
    }
}
