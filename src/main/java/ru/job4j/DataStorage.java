package ru.job4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataStorage {

    private final List<Item> storage = new ArrayList<>();

    public DataStorage() {
    }

    public Item get (String name) {
        return storage.stream().filter(s -> s.getName().equals(name)).findFirst().get();
    }

    public static void main(String[] args) {
        try {
            ServerSocket f = new ServerSocket(4000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
