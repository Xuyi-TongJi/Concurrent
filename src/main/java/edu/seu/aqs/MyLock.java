package edu.seu.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 自定义锁（不可重入锁）
public class MyLock implements Lock {
    @Override // 加锁(不成功会进入等待队列等待)
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override // 尝试加锁(只尝试一次，不会进入队列等待)
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override // 尝试加锁，如果超过时间未得到锁，则放弃尝试，不进入等待队列等待
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override  // 解锁
    public void unlock() {
        // 与tryRelease的区别：tryRelease不会唤醒WaitSet中阻塞线程。
        sync.release(1);
    }

    @Override  // 条件队列的条件对象 signal await
    public Condition newCondition() {
        return newCondition();
    }

    // 独占锁
    static class MySync extends AbstractQueuedSynchronizer{

        /**
         * 尝试获取锁
         * @param arg 可重入锁的计数操作
         * @return 是否成功获取锁
         */
        @Override
        protected boolean tryAcquire(int arg) {
            // cas实现state修改
            if(compareAndSetState(0, 1)){
                // 加锁成功,设置owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            // 加锁失败
            return false;
        }

        @Override  // 尝试释放锁
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null); // 普通方法
            setState(0); // volatile方法
            return true;
        }

        @Override // 是否持有独占锁
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }
    }
    private final MySync sync = new MySync();
}
