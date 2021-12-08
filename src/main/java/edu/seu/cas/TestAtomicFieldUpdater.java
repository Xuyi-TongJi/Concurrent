package edu.seu.cas;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class TestAtomicFieldUpdater {
    public static void main(String[] args) {
        Student stu = new Student();
        AtomicReferenceFieldUpdater<Student, String> updater
                = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        updater.compareAndSet(stu, null, "张三");
        System.out.println(stu);
    }
}
