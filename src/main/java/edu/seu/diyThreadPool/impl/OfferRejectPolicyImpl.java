package edu.seu.diyThreadPool.impl;

import edu.seu.diyThreadPool.BlockingQueue;
import edu.seu.diyThreadPool.RejectPolicy;

import java.util.concurrent.TimeUnit;

/**
 * 超时等待实现类
 * @param <T>
 */
public class OfferRejectPolicyImpl<T> implements RejectPolicy<T> {
    @Override
    public void reject(BlockingQueue<T> taskQueue, T task) {
        taskQueue.offer(task, 500, TimeUnit.MILLISECONDS);
    }
}