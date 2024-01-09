package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private int capacity;

    public SimpleBlockingQueue(int count) {
        this.capacity = count;
    }

    public Queue<T> getQueue() {
        return queue;
    }

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public void offer(T value) {
        synchronized (this) {
            while (queue.size() == capacity) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(value);
            this.notify();
        }
    }

    public T poll() {
        synchronized (this) {
        while (queue.size() == 0) {
            try {
                this.wait();
                this.notify();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        }
        return queue.poll();
    }

}
