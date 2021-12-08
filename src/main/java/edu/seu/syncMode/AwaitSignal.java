package edu.seu.syncMode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignal extends ReentrantLock {

    private int loopNumber;

    public AwaitSignal(int loopNumber){
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition current, Condition next){
        // 三个线程进入自己各自的锁
        lock();
        try{
            try {
                current.await();
                System.out.println(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally{
            unlock();
        }
    }
}
