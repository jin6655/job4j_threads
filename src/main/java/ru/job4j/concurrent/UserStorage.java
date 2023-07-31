package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final HashMap<Integer, User> users = new HashMap<>();

    public synchronized Map<Integer, User> getUsers() {
        return users;
    }

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) != null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    public synchronized void transfer(int formId, int told, int amount) {
        if (!users.containsKey(formId) || !users.containsKey(told)) {
            throw new IllegalArgumentException("Пользоавтели не найдены");
        } else if (users.get(formId).getAmount() < amount) {
            throw new IllegalArgumentException("На счету пользователя отправителя не достаточно средств");
        }
        User forUser = users.get(formId);
        User toUser = users.get(told);
        forUser.setAmount(forUser.getAmount() - amount);
        toUser.setAmount(toUser.getAmount() + amount);
        users.replace(formId, forUser);
        users.replace(told, toUser);
    }

    public static void main(String[] args) {
        User anna = new User(1);
        User jo = new User(2);
        User fu = new User(3);
        anna.setAmount(100);
        jo.setAmount(200);
        UserStorage userStorage = new UserStorage();
        userStorage.add(anna);
        userStorage.add(jo);
        userStorage.add(fu);
        userStorage.update(new User(3));
        for (Map.Entry<Integer, User> i : userStorage.getUsers().entrySet()) {
            System.out.println(i);
        }
        userStorage.delete(fu);
        userStorage.transfer(1, 2, 50);
        System.out.println("________________________");
        for (Map.Entry<Integer, User> i : userStorage.getUsers().entrySet()) {
            System.out.println(i);
        }
    }

}
