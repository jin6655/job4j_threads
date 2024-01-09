package ru.job4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Item implements  Cloneable {

    String name;

    int number;

    public Item(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return number == item.number && Objects.equals(name, item.name);
    }

    @Override
    protected Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }


    public static void main(String[] args) {
        Item first = new Item("Anna", 23);
        Item second = new Item("Bob", 10);
    }

}
