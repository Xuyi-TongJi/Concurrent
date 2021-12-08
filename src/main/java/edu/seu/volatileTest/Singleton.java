package edu.seu.volatileTest;

public class Singleton {
    /**
     * 无参构造
     */
    public Singleton() {
    }

    // 懒惰实例化单例模式
    private static volatile Singleton INSTANCE = null;

    public static Singleton getInstance(){
        // 2次判断
        if(INSTANCE == null) {
            synchronized (Singleton.class){
                if(INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
