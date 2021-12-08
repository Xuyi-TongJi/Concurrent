package edu.seu.deadLock;

public class Philosopher extends Thread{
    private final Chopsticks left;
    private final Chopsticks right;

    public Philosopher(String name, Chopsticks left, Chopsticks right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run(){
        while(true) {
            // 尝试获得左手筷子
            if (left.tryLock()){
                try{
                    // 尝试获得右手筷子
                    if (right.tryLock()){
                        try{
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }

    protected void eat(){
        System.out.println("eating");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
