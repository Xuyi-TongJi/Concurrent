package edu.seu.guardedSuspension.lock;

public class GuardedObject {

    private Object response;

    public Object get(){
        synchronized (this){
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
