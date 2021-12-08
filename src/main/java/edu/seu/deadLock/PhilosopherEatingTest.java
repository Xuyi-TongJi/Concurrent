package edu.seu.deadLock;

public class PhilosopherEatingTest {
    public static void main(String[] args) {
        Chopsticks c1 = new Chopsticks("1");
        Chopsticks c2 = new Chopsticks("2");
        Chopsticks c3 = new Chopsticks("3");
        Chopsticks c4 = new Chopsticks("4");
        Chopsticks c5 = new Chopsticks("5");

        new Philosopher("t1", c1, c2).start();
        new Philosopher("t2", c2, c3).start();
        new Philosopher("t3", c3, c4).start();
        new Philosopher("t4", c4, c5).start();
        new Philosopher("t5", c5, c1).start();
    }
}
