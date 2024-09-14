package ru.job4j.concurrent.pools;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class RolColSumTest {

    @Test
    public void whenGoSum() {
        int[][] matrix = new int[][]{{1,2,3}, {2, 3, 4}, {3, 8, 5}};
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        System.out.println("Matrix rsl:");
        for (int i = 0; i < sums.length; i++) {
            System.out.println("Row " + i + ": " + sums[i].getRowSum() + ", Col " + i + ": " + sums[i].getColSum());
        }
        int expectedlength = matrix.length;
        int expectedSumRowTwo = 9;
        int expectedSumColTwo = 13;
        assertThat(expectedlength, Matchers.is(sums.length));
        assertThat(expectedSumRowTwo, Matchers.is(sums[1].getRowSum()));
        assertThat(expectedSumColTwo, Matchers.is(sums[1].getColSum()));
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{1,2,3}, {2, 3, 4}, {3, 8, 5}};
        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        System.out.println("Matrix rsl:");
        for (int i = 0; i < sums.length; i++) {
            System.out.println("Row " + i + ": " + sums[i].getRowSum() + ", Col " + i + ": " + sums[i].getColSum());
        }
        int expectedlength = matrix.length;
        int expectedSumRowTwo = 9;
        int expectedSumColTwo = 13;
        assertThat(expectedlength, Matchers.is(sums.length));
        assertThat(expectedSumRowTwo, Matchers.is(sums[1].getRowSum()));
        assertThat(expectedSumColTwo, Matchers.is(sums[1].getColSum()));
    }

}