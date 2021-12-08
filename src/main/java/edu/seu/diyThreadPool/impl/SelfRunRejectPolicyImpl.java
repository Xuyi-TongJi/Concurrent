package edu.seu.diyThreadPool.impl;

import edu.seu.diyThreadPool.BlockingQueue;
import edu.seu.diyThreadPool.RejectPolicy;

public class SelfRunRejectPolicyImpl<T extends Runnable> implements RejectPolicy<T> {
    @Override
    public void reject(BlockingQueue<T> taskQueue, T task) throws Exception {
        // 主线程自己执行任务，不依赖线程池里的线程
        task.run();
    }
}
