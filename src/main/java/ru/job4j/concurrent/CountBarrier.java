package ru.job4j.concurrent;

import java.util.concurrent.TimeUnit;

public class CountBarrier {

    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            System.out.println("count = " + count);
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier countBarrier = new CountBarrier(5);
        Thread threadCount = new Thread (
                () ->
                {
                    for (int i = 0; i < 50; i++) {
                        countBarrier.count();
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        Thread threadAwait = new Thread (
                () ->
                {
                    countBarrier.await();
                    System.out.println("Go await program");
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Boom!");
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        threadCount.start();
        threadAwait.start();
    }

}
