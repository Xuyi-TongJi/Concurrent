package edu.seu.deadLock;

import java.util.concurrent.locks.ReentrantLock;

public class Chopsticks extends ReentrantLock {

    private final String name;

    public Chopsticks(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopsticks{" +
                "name='" + name + '\'' +
                '}';
    }
}
