package ru.job4j;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Caty {

    int x;

    double y;

    String str;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caty caty = (Caty) o;
        return x == caty.x && Double.compare(caty.y, y) == 0 && Objects.equals(str, caty.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, str);
    }

    @Override
    public String toString() {
        return "Caty{" +
                "x=" + x +
                ", y=" + y +
                ", str='" + str + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        for (int i = 0; i < 5; i++) {
                            System.out.print(i + " ");
                            System.out.println(Thread.currentThread().getName());
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                break;
                            }
                        }
                    }
                }, "thread_Anna"
        );
    }

}
