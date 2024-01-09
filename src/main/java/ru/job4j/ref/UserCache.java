package ru.job4j.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserCache {

    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        ConcurrentHashMap<Integer, User> map = new ConcurrentHashMap<>();
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        UserCache u = new UserCache();
        u.users.put(1, User.of("aaaa"));
        u.users.put(2, User.of("bbbbb"));
        List<User> list = u.findAll();
        System.out.println(list);
        list.get(0).setName("fffff");
        System.out.println(list);
        System.out.println(u.findAll());
    }

}
