package daily.concurrency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerPracticeWaitNotify {

    private static final class Buffer<T> {
        private final Queue<T> list;
        private final int cap;
        public Buffer(int N) {
            list = new LinkedList<>();
            this.cap = N;
        }

        public synchronized void put(T next) throws InterruptedException {
            while(list.size() == cap) {
                this.wait();
            }
            list.add(next);
            this.notifyAll();
        }

        public synchronized T take() throws InterruptedException {
            while(list.isEmpty()) {
                this.wait();
            }
            var res =  (T) list.poll();
            this.notifyAll();
            return res;
        }

    }

    static final class Producer implements Runnable {

        private final Buffer<Integer> q;
        private volatile boolean running = true;
        public Producer(Buffer<Integer> q) {
            this.q = q;
        }

        public void stop() {
            this.running = false;
        }

        public void run() {
            String currentThreadName = "Producer-" + Thread.currentThread().getName();
            try {
                while(running & !Thread.currentThread().isInterrupted()) {
                    int nextInt = ThreadLocalRandom.current().nextInt(10);
                    q.put(nextInt);
                    System.out.println(currentThreadName + " put " + nextInt);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println(currentThreadName + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    static final class Consumer implements Runnable {
        private final Buffer<Integer> q;
        private volatile boolean running = true;
        public Consumer(Buffer<Integer> q) {
            this.q = q;
        }

        public void stop() {
            this.running = false;
        }

        public void run() {
            String currentThreadName = "Consumer-" + Thread.currentThread().getName();
            try {
                while(running & !Thread.currentThread().isInterrupted()) {
                    int next = q.take();
                    System.out.println(currentThreadName + " takes " + next);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                System.out.println(currentThreadName + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var es = Executors.newFixedThreadPool(5);
        var bq = new Buffer<Integer>(10);
        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            producers.add(new Producer(bq));
        }
        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            consumers.add(new Consumer(bq));
        }

        for (Producer prod : producers) {
            es.submit(prod);
        }
        for (Consumer cons: consumers) {
            es.submit(cons);
        }

        Thread.sleep(5000);
        for (Producer prod : producers) {
            prod.stop();
        }

        for (Consumer cons: consumers) {
            cons.stop();
        }
        es.shutdownNow();
        es.awaitTermination(3, TimeUnit.SECONDS);
    }
}
