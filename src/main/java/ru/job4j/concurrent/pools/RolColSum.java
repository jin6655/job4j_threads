package ru.job4j.concurrent.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static class Sums {

        private int rowSum;

        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    //Дан каркас класса. Ваша задача подсчитать суммы по строкам и столбцам квадратной матрицы.
    //- sums[i].rowSum - сумма элементов по i строке
    //- sums[i].colSum - сумма элементов по i столбцу

    //Реализовать последовательную версию программы
    //Реализовать асинхронную версию программы. i - я задача считает сумму по i столбцу и i строке
    //Написать тесты

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        for (int i = 0; i < n; i++) {
            sums[i] = new Sums();
            for (int j = 0; j < n; j++) {
                sums[i].rowSum += matrix[i][j];
                sums[i].colSum += matrix[j][i];
            }
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        for (int i = 0; i < n; i++) {
            int x = i;
            CompletableFuture<Sums> completableFuture = CompletableFuture.supplyAsync(
                    () -> {
                        Sums sum = new Sums();
                        for (int j = 0; j < n; j++) {
                            sum.rowSum += matrix[x][j];
                            sum.colSum += matrix[j][x];
                        }
                        return sum;
                    }
            );
            sums[i] = completableFuture.get();
        }
        return sums;
    }

}
