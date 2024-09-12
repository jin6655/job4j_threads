package ru.job4j;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public int sum (int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        System.out.println("!");
    }

}
