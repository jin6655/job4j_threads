package ru.job4j.pattern;

import ru.job4j.Caty;

public final class Singleton {

    private final String name = "Anna";
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance () {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "name='" + name + '\'' +
                '}';
    }

}
