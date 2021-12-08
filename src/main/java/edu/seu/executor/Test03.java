package edu.seu.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Xuyi
 */
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<Object>> futures = executorService.invokeAll(Arrays.asList(
                () -> {
                    System.out.println("T1");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    System.out.println("T2");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    System.out.println("T3");
                    Thread.sleep(2000);
                    return "3";
                }
        ));
        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
