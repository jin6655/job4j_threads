package ru.job4j.pattern.testp;

public class Cat extends Animals {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String voice() {
        String voice = super.voice() + " meow";
        return voice;
    }

    public static void main(String[] args) {
        Animals cat = new Cat("Katty", 5);
        System.out.println(cat.voice());
        System.out.println(cat.run());
    }

}
