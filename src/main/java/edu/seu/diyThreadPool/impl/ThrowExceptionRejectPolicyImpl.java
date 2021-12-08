package edu.seu.diyThreadPool.impl;

import edu.seu.diyThreadPool.BlockingQueue;
import edu.seu.diyThreadPool.RejectPolicy;

public class ThrowExceptionRejectPolicyImpl<T> implements RejectPolicy<T> {
    @Override
    public void reject(BlockingQueue<T> taskQueue, T task) {
        throw new RuntimeException("任务执行失败");
    }
}
