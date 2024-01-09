package ru.job4j.pattern.testp;

public class Dog {

    private String name;
    private int age;

    public Dog() {
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Bob", 15);
        Dog dog01 = new Dog(dog.name, dog.age);
        Dog dog02 = dog;
        Dog dog03 = dog;
        System.out.println("dog01 = " + dog01);
        System.out.println("dog02 = " + dog02);
        System.out.println("dog03 = " + dog03);
        System.out.println("______________");
        dog.name = "Foks";
        System.out.println("dog01 = " + dog01);
        System.out.println("dog02 = " + dog02);
        System.out.println("dog03 = " + dog03);
    }


    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
