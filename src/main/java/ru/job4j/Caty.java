package ru.job4j;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Caty {

    private int x = 1;

    private int iteration = 0;
    private String name;

    public Caty() {
    }

    public Caty(int x, String name) {
        this.x = x;
        this.name = name;
    }

    public int counter () {
        return x++;
    }

    public int iteration () {
        return iteration++;
    }

    @Override
    public String toString() {
        return "Caty{"
                + "x=" + x
                + ", name='" + name + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Caty caty = (Caty) o;
        return x == caty.x && Objects.equals(name, caty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, name);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("!");
    }

}
