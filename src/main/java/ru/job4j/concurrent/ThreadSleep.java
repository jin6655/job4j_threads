package ru.job4j.concurrent;

import ru.job4j.Caty;

public class ThreadSleep {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.print("Start Load");
                        Thread.sleep(2000);
                        for (int i = 0; i <= 100; i++) {
                            String load = String.format("\rLoading: %s%%", i);
                            System.out.print(load );
                            Thread.sleep(50);
                        }
                        Thread.sleep(1000);
                        System.out.println("\nLoaded.");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        thread.start();
    }

}
