package edu.seu.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 创建一个新的条件变量
        Condition condition = lock.newCondition();

        lock.lock();
        // 进入condition的waitSet等待
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        condition.signal();
    }
}
