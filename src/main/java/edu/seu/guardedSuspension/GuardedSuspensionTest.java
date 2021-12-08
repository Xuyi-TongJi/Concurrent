package edu.seu.guardedSuspension;

import edu.seu.guardedSuspension.lock.GuardedObject;

import java.util.ArrayList;
import java.util.List;

public class GuardedSuspensionTest {
    public static void main(String[] args) {
        GuardedObject go = new GuardedObject();
        new Thread(() -> {
            System.out.println("等待下载");
            Object list = go.get();
            System.out.println("list:" + list);
        }, "t1").start();

        new Thread(() -> {
            System.out.println("执行下载");
            List<Integer> list = new ArrayList<>();
            list.add(1);
            go.complete(list); // 将list传递给t1，并唤醒t1
        }, "t2").start();
    }
}
