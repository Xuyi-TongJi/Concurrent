package edu.seu.diyThreadPool.impl;

import edu.seu.diyThreadPool.BlockingQueue;
import edu.seu.diyThreadPool.RejectPolicy;

/**
 * 死等策略实现类
 * @param <T>
 */
public class PutRejectPolicyImpl<T> implements RejectPolicy<T> {
    @Override
    public void reject(BlockingQueue<T> taskQueue, T task) {
        taskQueue.put(task);
    }
}
