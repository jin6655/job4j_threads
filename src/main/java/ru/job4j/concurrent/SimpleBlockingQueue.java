package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private int capacity;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue() {
    }

    public SimpleBlockingQueue(int count) {
        this.capacity = count;
    }

    public Queue<T> getQueue() {
        return queue;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

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
            this.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
        while (queue.size() == 0) {
                this.wait();
        }
        this.notifyAll();
        return queue.poll();
        }
    }

    public boolean isEmpty () {
        return queue.size() == 0;
    }

}
