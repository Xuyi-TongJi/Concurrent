package edu.seu;

/*@Slf4j(topic="RunVsStart")*/
public class RunVsStart {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") { // 匿名内部类
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("wakeup");
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupt");
        t1.interrupt(); // 打断t1的sleep
        System.out.println(t1.getState());
    }
}
