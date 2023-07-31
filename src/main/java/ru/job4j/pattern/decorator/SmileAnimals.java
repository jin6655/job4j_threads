package ru.job4j.pattern.decorator;

public class SmileAnimals extends AnimalsDecorator{


    public SmileAnimals(Animals animals) {
        super(animals);
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void cry() {
        super.cry();
    }

    public void smile() {
        System.out.println("Animal smile");
    }

}
