package ru.job4j.concurrent;

import org.junit.Test;
import org.hamcrest.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenCountFive() throws InterruptedException {
        Random randomNum = new Random();
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(10);
        Thread threadOffer = new Thread(
                () -> {
                    for (int i = 0; i < 6; i++) {
                        simpleBlockingQueue.offer(i);
                    }
                }
        );
        Thread threadPoll = new Thread(
                () -> {
                    while (Thread.currentThread().getState() != Thread.State.WAITING) {
                        try {
                            simpleBlockingQueue.poll();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        threadOffer.start();
        threadOffer.join();
        threadPoll.start();
        threadPoll.join(100);
        int expected = 0;
        assertEquals(expected, simpleBlockingQueue.getQueue().size());
    }

    @Test
    public void whenStartTwoThreads() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        producer.join();
        assertEquals(buffer, Arrays.asList());
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        consumer.interrupt();
        consumer.join();
        assertEquals(buffer, Arrays.asList(0, 1, 2, 3, 4));
    }

    @Test
    public void whenDoubleTwoThreads() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> {
                    for (int i = 1; i < 6; i++) {
                        queue.offer(i);
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            list.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        producer.join();
        assertEquals(5, queue.getQueue().size());
        consumer.start();
        consumer.interrupt();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

}