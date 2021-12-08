package edu.seu.joinFork;

import java.util.concurrent.RecursiveTask;

// 1~n 之间整数的和
public class MyTask extends RecursiveTask<Integer> {

    private final int begin;
    private final int end;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (begin == end){
            return begin;
        } else if(end - begin == 1) {
            return end + begin;
        }
        int middle = (end + begin) / 2;
        MyTask t1 = new MyTask(begin, middle);
        t1.fork();
        MyTask t2 = new MyTask(middle, end);
        t2.fork();

        return t1.join() + t2.join();
    }
}
