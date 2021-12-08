package edu.seu.test.transfer;

public class Account {
    private int money;
    public Account(int money){
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Account target, int amount){
        /* Critical Section */
        /*
            两个账户的余额都需要保护
            不能直接在方法上直接加synchronized，因为只锁住了自己，没有锁住target.money
            相当于this.money只对自己上锁，没有为另一个线程上锁

            不能同时锁住两个对象，因为容易产生死锁
            解决方案：
                this和target共享的是Account类，所以可以用Account.class充当锁对象
                [效率不高，有后续改进方法]
         */
        synchronized (Account.class) {
            if (this.money  >= amount){
                this.setMoney(this.getMoney() - amount);
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}
