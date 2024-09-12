package ru.job4j.concurrent;


import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    //Инициализация пула должна быть по количеству ядер в системе. int size = Runtime.getRuntime().availableProcessors()

    //В каждую нить передается блокирующая очередь tasks. В методе run мы
    //должны получить задачу из очереди tasks


    private final List<Thread> threads = new LinkedList<>();

    //Если в очереди нет элементов, то нить переводится в состояние Thread.State.WAITING.
    //Когда приходит новая задача, всем нитям в состоянии Thread.State.WAITING посылается сигнал проснуться и начать работу

    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void init () {
        int size = Runtime.getRuntime().availableProcessors();
        PoolThreadRunnaable poolThreadRunnaable = new PoolThreadRunnaable(tasks);
    }

    public ThreadPool() {
        init();
    }

    //должен добавлять задачи в SimpleBlockingQueue<Runnable> tasks
    public void work(Runnable job) {
        tasks.offer(job);
    }

    // завершит все запущенные задачи
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    static class PoolThreadRunnaable extends Thread {

        private SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

        public PoolThreadRunnaable(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            super.run();
        }

    }

}
