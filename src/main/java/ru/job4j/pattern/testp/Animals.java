package ru.job4j.pattern.testp;

public abstract class Animals {

    private String name;
    private int age;

    public Animals(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String voice() {
        String voice = "say";
        return voice;
    }

    public String run() {
        String run = "run";
        return run;
    }

}
