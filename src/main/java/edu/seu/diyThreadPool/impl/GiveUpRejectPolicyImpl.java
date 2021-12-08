package edu.seu.diyThreadPool.impl;

import edu.seu.diyThreadPool.BlockingQueue;
import edu.seu.diyThreadPool.RejectPolicy;

public class GiveUpRejectPolicyImpl<T> implements RejectPolicy<T> {
    @Override
    public void reject(BlockingQueue<T> taskQueue, T task) {
        // 什么都不执行
    }
}
