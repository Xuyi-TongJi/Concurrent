package edu.seu.joinFork;

import java.util.concurrent.ForkJoinPool;

public class joinForkTest01 {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        Integer invoke = forkJoinPool.invoke(new MyTask(1, 5));
        System.out.println(invoke);
        // MyTask拆分: 类似于递归的思想
    }
}
