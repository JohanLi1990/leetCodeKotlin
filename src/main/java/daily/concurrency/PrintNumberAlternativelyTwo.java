package daily.concurrency;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintNumberAlternativelyTwo implements Runnable {
    private static final AtomicInteger count = new AtomicInteger(0);

    private final Semaphore cur;
    private final Semaphore next;
    public PrintNumberAlternativelyTwo(Semaphore cur, Semaphore next) {
        this.cur = cur;
        this.next = next;
    }

    @Override
    public void run() {
        String tName = Thread.currentThread().getName();
        try {
            for (; ; ) {
                cur.acquire();
                int curVal = count.getAndIncrement();
                if (curVal >= 10) {
                    System.out.println(tName + ": finished!!");
                    next.release();
                    break;
                }
                System.out.println(tName + ": " + count.get());
                next.release();
            }
        } catch (InterruptedException e) {
            System.out.println(tName + ": is interrupted");
            Thread.currentThread().interrupt();
        }
    }
    // use semaphore to do it

    public static void main(String[] args) throws InterruptedException {
        Semaphore ones = new Semaphore(1);
        Semaphore twos = new Semaphore(0);
        Semaphore three = new Semaphore(0);

        var t1 = new PrintNumberAlternativelyTwo(ones, twos);
        var t2 = new PrintNumberAlternativelyTwo(twos, three);
        var t3 = new PrintNumberAlternativelyTwo(three, ones);
        var named = new ThreadFactory(){
            private final AtomicInteger idx = new AtomicInteger(1);
            @Override
            public Thread newThread(@NotNull Runnable r) {
                return new Thread(r, "T-"+idx.getAndIncrement());
            }
        };
        try (ExecutorService es = Executors.newFixedThreadPool(3, named)) {
            es.submit(t1);
            es.submit(t2);
            es.submit(t3);
        }


    }

}
