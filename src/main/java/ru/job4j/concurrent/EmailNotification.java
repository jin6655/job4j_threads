package ru.job4j.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUsername());
        send(subject, body, user.getEmail());
    }

    public void close() throws InterruptedException {
        pool.shutdown();
        while (!pool.isTerminated()) {
            Thread.sleep(100);
        }
    }

    public void send(String subject, String body, String email) {

    }

    private class User {

        private String username;

        private String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }

}
