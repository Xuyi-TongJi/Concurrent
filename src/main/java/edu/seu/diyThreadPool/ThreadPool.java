package edu.seu.diyThreadPool;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    private final BlockingQueue<Runnable> taskQueue;

    private final HashSet<Worker> workers = new HashSet<>(); // 非线程安全

    // 核心线程数
    private final int coreSize;

    // 设置任务的超时时间
    private final TimeUnit timeUnit;

    private final long timeout;

    // 拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, TimeUnit timeUnit, long timeout, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeUnit = timeUnit;
        this.timeout = timeout;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    // 执行任务
    public void execute(Runnable task) throws Exception {
        // 当任务数没有超过coreSize时，直接交给Worker对象执行
        // 如果超过了，就要加入任务队列暂存
        synchronized (workers) {
            if(workers.size() < coreSize){
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            }else{
                taskQueue.put(task);
                /*
                    策略模式
                    1. 死等 put
                    2. 带超时等待 offer
                    3. 让调用者放弃任务执行
                    4. 让调用着抛出异常
                    5. 让调用者自己执行任务
                 */
                // 拒绝策略
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 1.当task不为空
            // 2.当task执行完毕，再接着从任务队列获取任务并执行
            // 死等实现
            while(task != null || (task = taskQueue.take()) != null){
                try{
                    task.run();
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized(workers){
                workers.remove(this);
            }
        }
    }
}
