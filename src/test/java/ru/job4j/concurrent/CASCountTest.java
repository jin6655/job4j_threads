package ru.job4j.concurrent;

import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenCountOneMinute() throws InterruptedException {
        CASCount casCount = new CASCount();
        int expected = 20;
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        casCount.increment();
                        System.out.println("first increment " + casCount.get());
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        casCount.increment();
                        System.out.println("second increment " + casCount.get());
                    }
                }
        );
        first.start();
        second.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        assertEquals(20, casCount.get());
    }

}