package ru.job4j.concurrent;

import java.util.concurrent.TimeUnit;

public class Wget {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int i = 1; i <= 100; i++) {
                            System.out.print("\rLoading : " + i + "%");
                            Thread.sleep(1000);
                        }
                        System.out.println("\rLoaded.");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        thread.start();
    }

}
