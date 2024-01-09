package ru.job4j.concurrent;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class MultiUser {

    public static void main(String[] args) throws InterruptedException {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        slave.start();
        TimeUnit.MILLISECONDS.sleep(5000);
        master.start();
    }

}
