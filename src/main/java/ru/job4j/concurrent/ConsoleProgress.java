package ru.job4j.concurrent;

import java.util.concurrent.TimeUnit;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] str = {"   ", "\\  ", "\\| ", "\\|/"};
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + "progress " + str[count++]);
            if (count == 4) {
                count = 0;
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
        System.out.println("\rend");

    }

}
