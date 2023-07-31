package ru.job4j.pattern.decorator;

public class DoubleRunAnimals extends AnimalsDecorator {

    public DoubleRunAnimals(Animals animals) {
        super(animals);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("x2 run");
    }

    @Override
    public void cry() {
        super.cry();
    }

}
