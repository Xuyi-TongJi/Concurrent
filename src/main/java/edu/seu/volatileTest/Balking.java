package edu.seu.volatileTest;

public class Balking {

    private Thread monitorThread;

    volatile private boolean stop = false;
    // 判断是否执行过start方法
    volatile private boolean starting = false;

    public void start(){
        // 对starting既有读又有写，必须保证原子性
        // synchronized代码块里的代码必须尽量少
        synchronized (this) {
            if (starting){
                return;
            }
            starting = true;
        }
        monitorThread = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (stop) {
                    System.out.println("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        }, "monitor");
        monitorThread.start();
    }

    // 其他线程调用stop
    public void stop() {
        starting = false;
        stop = true;
        monitorThread.interrupt();
    }
}
