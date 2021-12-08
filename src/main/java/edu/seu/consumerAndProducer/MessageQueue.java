package edu.seu.consumerAndProducer;

import java.util.LinkedList;

/*
    消息队列类,实现java线程之间通信
 */
public class MessageQueue {

    private final LinkedList<Message> list = new LinkedList<>();
    private final int capacity; // 消息队列的容量

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    System.out.println("队列为空，消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // consume
            Message message =  list.removeFirst();
            System.out.println("已消费消息" + message);
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    System.out.println("队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // produce
            list.addLast(message);
            System.out.println("已生产消息" + message);
            list.notifyAll();
        }
    }
}