package daily.concurrency;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * In this example, we implement:
 *
 * A task queue where tasks are submitted.
 *
 * A fixed number of worker threads.
 *
 * If no tasks are available, workers wait().
 *
 * When a task is added, one worker is notified().
 *
 */
public class InterThreadWaitNotify {

    // wait / notify
    static class TaskQueue {
        private final Queue<Runnable> queue = new LinkedList<>();

        public synchronized void submit(Runnable task) {
            queue.offer(task);
            notify();
        }

        public synchronized Runnable fetch() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            return queue.poll();
        }
    }

    static class Worker extends Thread {
        private final TaskQueue taskQueue;
        private volatile boolean running = true;

        public Worker(TaskQueue queue) {
            this.taskQueue = queue;
        }

        public void run() {
            while(running) {
                try {
                    Runnable task = taskQueue.fetch();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void shutdown() {
            running  = false;
            this.interrupt();
        }

    }

    static class CustomThreadPool {
        private final Worker[] workers;
        private final TaskQueue taskQueue = new TaskQueue();

        public CustomThreadPool(int numThreads) {
            workers = new Worker[numThreads];
            for (int i = 0; i < numThreads; i++) {
                workers[i] = new Worker(taskQueue);
                workers[i].start();
            }
        }

        public void submit(Runnable task) {
            taskQueue.submit(task);
        }

        public void shutdown() {
            for (var worker : workers) {
                worker.shutdown();
            }
        }
    }

    public static void main(String[] args) {
        CustomThreadPool pool = new CustomThreadPool(3);

        // submit 10 tasks
        for (int i =0; i < 10; i++) {
            int taskNum = i;
            pool.submit(() -> {
                System.out.println("Executing Task " + taskNum + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
            });
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e){}
        pool.shutdown();
    }

}
