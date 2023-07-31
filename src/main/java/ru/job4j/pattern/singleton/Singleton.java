package ru.job4j.pattern.singleton;

public final class Singleton {

    private final String name = "Anna";
    private static volatile Singleton instance;

    public Singleton() {
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

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance().getName());
    }

}
