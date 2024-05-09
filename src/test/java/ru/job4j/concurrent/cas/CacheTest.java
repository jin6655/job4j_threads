package ru.job4j.concurrent.cas;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddValues () {
        Cache cache = new Cache();
        Base base01 = new Base(1, 22);
        Base base02 = new Base(2, 33);
        Map<Integer, Base> expected = new ConcurrentHashMap<>();
        expected.put(base01.getId(), base01);
        expected.put(base02.getId(), base02);
        cache.add(base01);
        cache.add(base02);
        assertEquals(expected, cache.getMemory());
    }

    @Test
    public void whenNotAddValues () {
        Cache cache = new Cache();
        Base base01 = new Base(1, 22);
        Base base02 = new Base(1, 33);
        Map<Integer, Base> expected = new ConcurrentHashMap<>();
        expected.put(base01.getId(), base01);
        cache.add(base01);
        cache.add(base02);
        assertEquals(expected, cache.getMemory());
    }

    @Test
    public void whenUpdateValues () {
        Cache cache = new Cache();
        Base base01 = new Base(1, 22, "Anna");
        Base base02 = new Base(1, 22, "Gloria");
        String expected = "Gloria";
        cache.add(base01);
        cache.update(base02);
        assertEquals(expected, cache.getMemory().get(1).getName());
    }

    @Test (expected = OptimisticException.class)
    public void testExceptionOptimistExceptionwhenUpdate() {
        Cache cache = new Cache();
        Base base01 = new Base(1, 22, "Anna");
        Base base02 = new Base(1, 23, "Gloria");
        cache.add(base01);
        cache.update(base02);
    }

    @Test
    public void whenValuesDeleted () {
        Cache cache = new Cache();
        Base base01 = new Base(1, 22);
        Base base02 = new Base(2, 33);
        Map<Integer, Base> expected = new ConcurrentHashMap<>();
        expected.put(base02.getId(), base02);
        cache.add(base01);
        cache.add(base02);
        cache.delete(base01);
        assertEquals(expected, cache.getMemory());
    }

    @Test (expected = OptimisticException.class)
    public void testExceptionOptimistExceptionwhenDeleted() {
        Cache cache = new Cache();
        Base base01 = new Base(1, 22, "Anna");
        Base base02 = new Base(1, 23, "Gloria");
        cache.add(base01);
        cache.delete(base02);
    }

}