package ru.job4j.concurrent;

public class ConcurrentOutput {

    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread another02 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
        );
        another.start();
        another02.start();
        System.out.println(Thread.currentThread().getName());
    }

}
