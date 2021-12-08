package edu.seu;

public class TwoPhaseTermination {
    private Thread monitor;

    /**
     * 启动监控线程
     */
    public void start(){
        monitor = new Thread(()->{
            while(true){
                Thread current = Thread.currentThread();
                if (current.isInterrupted()){
                    System.out.println("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    // 如果在sleep状态下被打断，会抛出异常，进入catch块
                    System.out.println("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    current.interrupt(); // 重新设置打断标记为true;
                }
            }
        });
        monitor.start();
    }

    /**
     * 停止监控线程
     */
    public void stop(){
        monitor.interrupt();
    }
}
