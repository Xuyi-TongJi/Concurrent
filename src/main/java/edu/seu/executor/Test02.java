package edu.seu.executor;

import java.util.concurrent.*;

/**
 * @author Administrator
 */
public class Test02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future = executorService.submit(() -> {
            System.out.println("ThreadRun");
            Thread.sleep(1000);
            return "ok";
        });
        // 获取任务结果
        System.out.println(future.get());
    }
}
