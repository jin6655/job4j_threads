package ru.job4j.concurrent.cas;

import java.util.Objects;

public class Base {

    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                '}';
    }
}
