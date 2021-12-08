package edu.seu.diyThreadPool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {
    // 1. 任务队列
    private final Deque<T> queue = new ArrayDeque<T>();// 性能优于linkedList

    // 2. 锁[一个任务只能被一个线程获取，用锁保护队列头，尾的元素]
    private final ReentrantLock lock = new ReentrantLock();

    // 3. 消费者条件变量
    private final Condition fullWaitSet = lock.newCondition();

    // 4. 生产者条件变量
    private final Condition emptyWaitSet = lock.newCondition();

    // 5. Blocking Queue capacity
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // 阻塞获取
    public T take(){
        lock.lock();
        try{
            while(queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signalAll();
            return t;
        } finally{
            lock.unlock();
        }
    }

    // 带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit){
        lock.lock();
        try{
            long nanos = unit.toNanos(timeout);
            while(queue.isEmpty()){
                try {
                    // 返回剩余时间
                    if(nanos <= 0){
                        // 没等到
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signalAll();
            return t;
        } finally{
            lock.unlock();
        }
    }

    // 阻塞添加[死等]
    public void put(T element){
        lock.lock();
        try{
            while(queue.size() == capacity){
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyWaitSet.signalAll();
        }finally{
            lock.unlock();
        }
    }

    // 带超时时间的阻塞添加
    // 超过timeout时间依旧未添加成功，则添加失败
    public boolean offer(T element, long timeout, TimeUnit timeUnit){
        lock.lock();
        try{
            long nanos = timeUnit.toNanos(timeout);
            while(queue.size() == capacity){
                try {
                    if (nanos < 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyWaitSet.signalAll();
            return true;
        }finally{
            lock.unlock();
        }
    }

    // 获取队列元素个数
    public int size(){
        lock.lock();
        try{
            return queue.size();
        } finally{
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) throws Exception {
        lock.lock();
        try {
            if(queue.size() == capacity){
                rejectPolicy.reject(this, task);
            } else{ // 队列有空闲
                queue.addLast(task);
                emptyWaitSet.signalAll();
            }
        } finally{
            lock.unlock();
        }
    }
}
