package edu.seu.diyThreadPool;

@FunctionalInterface
public interface RejectPolicy<T> {
    void reject(BlockingQueue<T> taskQueue, T task) throws Exception;
}
