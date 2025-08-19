package daily.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter implements Runnable{

    private final AtomicInteger counter = new AtomicInteger(0);
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" +   counter.incrementAndGet());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) {
        var sharedCounter = new ThreadSafeCounter();
        Thread A = new Thread(sharedCounter);
        Thread B = new Thread(sharedCounter);
        A.start();
        B.start();
        try {
            A.join();
            B.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
