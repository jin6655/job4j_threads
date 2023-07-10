package ru.job4j.concurrent;

public class ThreadState {

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.isAlive()) {
            System.out.println("!");
        }
        System.out.println("first thread " + first.getState());
        System.out.println("second thread " + second.getState());
        System.out.println("Работа завершена.");
    }

}
