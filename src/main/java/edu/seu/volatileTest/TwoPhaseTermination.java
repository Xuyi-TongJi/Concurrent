package edu.seu.volatileTest;

public class TwoPhaseTermination {

    private Thread monitorThread;

    volatile private boolean stop = false;

    public void start(){
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
        stop = true;
        monitorThread.interrupt();
    }
}
