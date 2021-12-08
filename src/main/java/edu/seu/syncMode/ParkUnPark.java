package edu.seu.syncMode;

import java.util.concurrent.locks.LockSupport;

public class ParkUnPark {

    private int loopNumber;

    public ParkUnPark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.println(str);
            LockSupport.unpark(next); // 唤醒下一个线程
        }
    }
}
