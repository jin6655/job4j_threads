package ru.job4j.thread;

import java.io.*;
import java.net.URI;
import java.net.URL;

public class Wget implements Runnable {

    private final String url;

    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String[] str = url.split("/");
        String nameSaveFile = str[str.length - 1];
        try (BufferedInputStream in = new BufferedInputStream(URI.create(url).toURL().openStream());
            FileOutputStream out = new FileOutputStream(nameSaveFile + "_wget")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int count = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                System.out.println("цикл " + count++);
                out.write(dataBuffer, 0, bytesRead);
                long time = System.currentTimeMillis() - startTime;
                if (time < speed) {
                    try {
                        long pause = speed  - time;
                        System.out.println("пауза = " + pause);
                        Thread.sleep(pause);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                startTime = System.currentTimeMillis();
            }
            System.out.println("END");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

}
