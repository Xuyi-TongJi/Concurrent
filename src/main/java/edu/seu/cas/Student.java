package edu.seu.cas;

public class Student {

    volatile public String name;

    /*public void setName(String name) {
        this.name = name;
    }*/

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
