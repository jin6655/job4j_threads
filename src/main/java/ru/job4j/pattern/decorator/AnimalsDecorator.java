package ru.job4j.pattern.decorator;

public abstract class AnimalsDecorator implements Animals{

    private final Animals animals;

    public AnimalsDecorator(Animals animals) {
        this.animals = animals;
    }

    @Override
    public void run() {
        animals.run();
    }

    @Override
    public void cry() {
        animals.cry();
    }

}
