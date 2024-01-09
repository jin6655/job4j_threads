package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenCountFive() throws InterruptedException {
        Random randomNum = new Random();
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(5);
        Thread threadOffer = new Thread(
                () -> {
                    while (Thread.currentThread().getState() != Thread.State.WAITING) {
                        int i = randomNum.nextInt(100);
                        simpleBlockingQueue.offer(i);
                        System.out.println("offer " + i);
                    }
                    System.out.println("threadOffer status " + Thread.currentThread().getState());
                }
        );
        Thread threadPoll = new Thread(
                () -> {
                    while (Thread.currentThread().getState() != Thread.State.WAITING) {
                        System.out.println("poll " + simpleBlockingQueue.poll());
                    }
                    System.out.println("threadPoll status " + Thread.currentThread().getState());
                }
        );
        threadOffer.start();
        threadOffer.join();
        assertEquals(simpleBlockingQueue.getQueue().size(), 5);
        threadPoll.start();
        threadPoll.join(100);
        assertEquals(simpleBlockingQueue.getQueue().size(), 0);


    }

}