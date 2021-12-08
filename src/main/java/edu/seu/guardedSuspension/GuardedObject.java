package edu.seu.guardedSuspension;

public class GuardedObject {

    private int id;

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private Object response;

    public Object get(long timeout){
        // 关键：this——GuardedObject对象实例
        synchronized (this){
            // 记录等待的开始时间
            long begin = System.currentTimeMillis();
            // 记录经历的时间
            long passedTime = 0;
            while (response == null) {
                long waitTime = timeout - passedTime;
                try {
                    // 总等待时间超过了timeout时，就退出循环
                    if (waitTime <= 0){
                        break;
                    }
                    this.wait(waitTime); // 避免虚假唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    // 结果已经产生
    public void complete(Object response){
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
