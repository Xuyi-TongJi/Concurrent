package edu.seu.executor;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.*;

public class ScheduledExecutorServiceTest {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2); // 设置的是核心线程数
        // 获取当前时间
        Duration initialDelay;
        LocalDateTime now = LocalDateTime.now();
        // 获取周四时间
        LocalDateTime thursday = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        if (now.isBefore(thursday)){
            initialDelay = Duration.between(now, thursday);
        } else{
            initialDelay = Duration.between(now, thursday.plusWeeks(1));
        }
        long period = 1000 * 60 * 60 * 24 * 7;
        pool.scheduleAtFixedRate(()->{
            System.out.println(new Date());
        }, initialDelay.toMillis(), period, TimeUnit.MILLISECONDS);
    }

}
