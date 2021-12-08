package edu.seu.test.sellTickets;

public class TicketWindow {
    private int count;

    public TicketWindow(int count){
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    /**
     * 卖票
     * @param amount 购买了几张
     * @return 返回购买了几张
     */
    public synchronized int sell(int amount){
        if (this.count >= amount){
            this.count -= amount;
            return amount;
        } else{
            return 0;
        }
    }
}
