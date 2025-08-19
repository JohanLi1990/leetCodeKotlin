package daily.concurrency;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

public class CountDownLatchExample implements Runnable {
    private final CountDownLatch readyLatch;
    private final CountDownLatch callingThreadBlocker;
    private final CountDownLatch completedLatch;

    public CountDownLatchExample(CountDownLatch latch, CountDownLatch callingBlocker, CountDownLatch completedLatch) {
        this.readyLatch = latch;
        this.callingThreadBlocker = callingBlocker;
        this.completedLatch = completedLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + ": is ready!");
        readyLatch.countDown();
        try {
            callingThreadBlocker.await();
            System.out.println(Thread.currentThread() + ": " + "is going to work");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread() + ": is completed!");
            completedLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var readyLatch = new CountDownLatch(5);
        var completeLatch = new CountDownLatch(5);
        var callingBlocker = new CountDownLatch(1);
        List<Thread> workers = Stream.generate(() -> new Thread(new CountDownLatchExample(readyLatch, callingBlocker, completeLatch))).limit(5).toList();
        workers.forEach(Thread::start);
        readyLatch.await();
        callingBlocker.countDown();
        completeLatch.await();
        System.out.println("All completed!");
    }
}
