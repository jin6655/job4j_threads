package ru.job4j.pattern.decorator;

public class Cat implements Animals{

    @Override
    public void run() {
        System.out.println("run Cat");
    }

    @Override
    public void cry() {
        System.out.println("cry Cat");
    }

    public static void main(String[] args) {
        Animals cat = new Cat();
        Animals cat01 = new DoubleRunAnimals(cat);
        cat = new SmileAnimals(cat);
        cat.run();
        System.out.println("______________");
        cat01.run();
    }

}
