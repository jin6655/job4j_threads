package ru.job4j.concurrent.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class ParallelSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private T item;

    private final int from;

    private final int to;

    public ParallelSearch(T[] array, T item, int from, int to) {
        this.array = array;
        this.item = item;
        this.from = from;
        this.to = to;
    }

    public T[] getArray() {
        return array;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            return processing();
        }
        int mid = (from + to) / 2;
        ParallelSearch<T> searchLeft = new ParallelSearch<>(array, item, from, mid);
        ParallelSearch<T> searckRight = new ParallelSearch<>(array, item,  mid + 1, to);
        searchLeft.fork();
        searckRight.fork();
        Integer right = searckRight.join();
        Integer left = searchLeft.join();
        return left > right ? left : right;
    }

    private int processing() {
        int rsl = -1;
        for (int i = from; i < to; i++) {
            if (array[i].equals(item)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public static <T> int searchIndex(T[] array, T item) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (int) forkJoinPool.invoke(new ParallelSearch(array,  item, 0, array.length - 1));
    }

    public static void main(String[] args) {
        String[] str01 = {"a", "b", "c", "d", "e", "f", "g", "h", "k", "l", "m", "n", "o", "p", "r",
        "s", "t", "q", "w", "!", "!!", "!!!", "2"};
        String[] str02 = {"a", "b", "c"};
        System.out.println(ParallelSearch.searchIndex(str01, "w"));
    }

}
