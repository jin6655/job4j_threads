package ru.job4j;

import java.sql.SQLOutput;
import java.sql.Time;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Caty {

    private int x;
    private String name;

    public Caty(int x, String name) {
        this.x = x;
        this.name = name;
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
        int count = 0;
        while (true) {
            System.out.println("! " + count++);
            if (count == 10) {
                count = 0;
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

}
