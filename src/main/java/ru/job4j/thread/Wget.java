package ru.job4j.thread;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.URI;
import java.nio.file.Path;

public class Wget implements Runnable {

    private final String url;

    private final int speedMls;

    public Wget(String url, int speed) {
        this.url = url;
        this.speedMls = speed;
    }

    @Override
    public void run() {
        String[] str = url.split("/");
        String nameSaveFile = str[str.length - 1];
        //try (BufferedInputStream in = new BufferedInputStream(URI.create(url).toURL().openStream());
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(Path.of(url).toFile()));
        //     FileOutputStream out = new FileOutputStream(nameSaveFile + "_wget")) {
             FileOutputStream out = new FileOutputStream(new File(nameSaveFile))) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int count = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                System.out.println("цикл записи " + count++);
                out.write(dataBuffer, 0, bytesRead);
                long time = System.currentTimeMillis() - startTime;
                if (time < speedMls) {
                    try {
                        long pause = speedMls - time;
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
        if (args.length != 2) {
            throw new IllegalArgumentException("Не задан путь к файлу для скачивания");
        }
        String url = args[0];
        int speedMls = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speedMls));
        wget.start();
        wget.join();
    }

}
