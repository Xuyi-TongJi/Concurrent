package edu.seu.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class AccountCas implements Account{

    private AtomicInteger balance; // 原子整数

    public AccountCas(Integer balance){
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getAmount() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while(true) {
            int prev = balance.get(); // 获取余额的最新值
            int next = prev - amount; // 修改的余额
            if(balance.compareAndSet(prev, next)){
                break;
            }
        }
    }
}