package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

@ThreadSafe
public class User {

    @GuardedBy("this")
    private int id;

    @GuardedBy("this")
    private int amount;

    public User(int id) {
        this.id = id;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }

    public synchronized int getAmount() {
        return amount;
    }

    public synchronized int getId() {
        return id;
    }

    @Override
    public synchronized String toString() {
        return "User{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }

    @Override
    public synchronized boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && amount == user.amount;
    }

    @Override
    public synchronized int hashCode() {
        return Objects.hash(id, amount);
    }
}
