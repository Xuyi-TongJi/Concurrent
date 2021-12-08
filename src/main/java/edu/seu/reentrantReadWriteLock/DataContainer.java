package edu.seu.reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataContainer {
    private Object data;
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private final ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read(){
        r.lock();
        try {
            System.out.println("读取");
            return data;
        } finally {
            r.unlock();
        }
    }

    public void write(Object data){
        w.lock();
        try {
            System.out.println("写入");
            this.data = data;
        } finally {
            w.unlock();
        }
    }
}
