package ru.job4j;

import java.util.Objects;

public class Caty {

    public static final String AGE = "18";

    private int x;
    private String name;

    public Caty(int x, String name) {
        if (x >= 101) {
            throw  new IllegalArgumentException("Полe x объекта Caty должно удовлетоварять условию x >= 100.");
        } else {
            this.x = x;
            this.name = name;
        }
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

    public static void main(String[] args) {
        System.out.println("!");
    }

}
