package daily.concurrency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerCondition {

    // buffer
    private record Buffer<T>(int CAP, LinkedList<T> list) {
        private static final ReentrantLock lock = new ReentrantLock();
        private static final Condition notFull = lock.newCondition();
        private static final Condition notEmpty = lock.newCondition();

        // put
        void put(T next) throws InterruptedException {
            String tName = Thread.currentThread().getName();
            lock.lockInterruptibly();
            try {
                while(list.size() == CAP) {
                    notFull.await();
                }
                list.add(next);
                notEmpty.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

        T take() throws InterruptedException {
            lock.lockInterruptibly();
            try {
                while(list.isEmpty()) {
                    notEmpty.await();
                }
                var ans = list.poll();
                notFull.signal();
                return ans;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

    }


    // consumer
    private static class Consumer implements Runnable {
        private volatile boolean isRunning = true;
        private final Buffer<Integer> bf;

        Consumer(Buffer<Integer> bf) {
            this.bf = bf;
        }

        public void stop() {
            this.isRunning = false;
        }

        @Override
        public void run() {
            String tName = "Consume-" + Thread.currentThread().getName();
            try {
                while (isRunning && !Thread.currentThread().isInterrupted()) {
                    int cur = bf.take();
                    System.out.println(tName + " takes " + cur);
                    Thread.sleep(400);
                }
                System.out.println(tName + "is Done!");
            } catch (Exception e) {
                System.out.println(tName + " is interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    // producer
    private static class Producer implements Runnable {
        private final Buffer<Integer> bf;
        private volatile boolean isRunning = true;

        Producer(Buffer<Integer> bf) {
            this.bf = bf;
        }

        public void stop() {
            isRunning = false;
        }

        @Override
        public void run() {
            String tName = "Producer-"+ Thread.currentThread().getName();
            try {
                while (isRunning && !Thread.currentThread().isInterrupted()) {
                    var cur = ThreadLocalRandom.current().nextInt(10);
                    bf.put(cur);
                    System.out.println(tName + " puts " + cur);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                System.out.println(tName + " is interrupted");
                Thread.currentThread().interrupt();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        var es = Executors.newFixedThreadPool(5);
        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();
        Buffer<Integer> bf = new Buffer<>(10, new LinkedList<>());
        for (int i = 0; i < 3; i++) {
            producers.add(new Producer(bf));
        }

        for (int i = 0; i < 3; i++) {
            consumers.add(new Consumer(bf));
        }

        for (Producer prod: producers) {
            es.submit(prod);
        }

        for (Consumer consumer : consumers) {
            es.submit(consumer);
        }

        Thread.sleep(5000);
        producers.forEach(Producer::stop);
        consumers.forEach(Consumer::stop);

        es.shutdownNow();
        es.awaitTermination(3, TimeUnit.SECONDS);
    }

}
