package daily.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProducerConsumerPractice {
    private static final int POSION = Integer.MIN_VALUE;
    record Producer(BlockingQueue<Integer> q, AtomicBoolean running) implements Runnable {

        @Override
        public void run() {

            int next = ThreadLocalRandom.current().nextInt();
            try {
                for (; ; ) {
                    if (!running().get()) {
                        System.out.println("Producer-"+Thread.currentThread().getName() + " is DFD");
                        break;
                    }
                    q.offer(next, 5, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() + " puts " + next);
                    Thread.sleep(1200);
                }
            } catch (InterruptedException e) {
                System.out.println("Cannot add to Queue");
                throw new RuntimeException(e);
            } finally {
                System.out.println("Producer-" + Thread.currentThread().getName() + " is terminating");
            }
        }
    }

    record Consumer(BlockingQueue<Integer> q) implements Runnable {

        @Override
        public void run() {
            try {
                for (; ; ) {
                    Integer next = q.poll(5, TimeUnit.SECONDS);
                    if (next == null) {
                        continue;
                    }
                    if (next == POSION) {
                        System.out.println("Consumer-" + Thread.currentThread().getName() + ": is DFD");
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " takes " + next);
                    Thread.sleep(250);
                }
            } catch (InterruptedException e) {
                System.out.println("Queue is empty");
                throw new RuntimeException(e);
            } finally {
                System.out.println(Thread.currentThread().getName() + " is terminated" );
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var bq = new ArrayBlockingQueue<Integer>(10);
        var flag = new AtomicBoolean(true);
        var consumer = new Consumer(bq);
        var producer = new Producer(bq, flag);

        var es = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 3; i++) {
            es.submit(producer);
        }

        for (int i = 0; i < 3; i++) {
            es.submit(consumer);
        }
        Thread.sleep(3000);
        flag.set(false);
        for (int i = 0; i < 3; i++) {
            bq.put(POSION);
        }
        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("All done!");
    }
}





