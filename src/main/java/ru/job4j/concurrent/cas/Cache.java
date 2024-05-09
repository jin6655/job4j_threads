package ru.job4j.concurrent.cas;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {

    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base stored = memory.get(model.getId());
        return memory.computeIfPresent(model.getId(), (key, val) -> {
            if (model.getVersion() != val.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            return val = model;
        }) != null;
    }

    public void delete(Base model) {
        Base stored = memory.get(model.getId());
        if (stored == null || stored.getVersion() != model.getVersion()) {
            throw new OptimisticException("Versions are not equal");
        }
        memory.remove(model.getId(), model);
    }

    public Map<Integer, Base> getMemory() {
        return memory;
    }

}
