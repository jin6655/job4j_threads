package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer expected;
        Integer newValue;
        do {
            expected = get(); // входящее значение
            newValue = expected + 1; // исходящее значение
        } while (!count.compareAndSet(expected, newValue)); // если равны -> считай заново
    }

    public int get() {
        return count.get();
    }

}
