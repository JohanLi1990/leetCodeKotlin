package daily.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class LeaderBoardExample {

    static class LeaderBoard {
        private final AtomicInteger idSeq = new AtomicInteger(1);
        private final ConcurrentHashMap<Integer, AtomicInteger> scores = new ConcurrentHashMap<>();

        int registerUser() {
            int id = idSeq.getAndIncrement();
            scores.putIfAbsent(id, new AtomicInteger(0));
            return id;
        }

        int addScore(int userId, int delta) {
            return scores.computeIfAbsent(userId, k -> new AtomicInteger(0)).addAndGet(delta);
        }

        int getScore(int userId) {
            return scores.getOrDefault(userId, new AtomicInteger(0)).get();
        }

        int submitHighScore(int userId, int candidate) {
            var cell = scores.computeIfAbsent(userId, k -> new AtomicInteger(0));
            for (;;) {
                int cur = cell.get();
                if (candidate <= cur) return cur;                  // no change
                if (cell.compareAndSet(cur, candidate)) return candidate; // CAS win
                // else lost the race → retry
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        runTest();
        runCompareAndSetTest();
    }

    private static void runCompareAndSetTest() throws InterruptedException {
        var lb = new LeaderBoard();
        int id = lb.registerUser();

        int threads = 16, ops = 5_000;
        var pool = Executors.newFixedThreadPool(threads);
        var latch = new CountDownLatch(threads);

        AtomicInteger maxSeen = new AtomicInteger(Integer.MIN_VALUE);
        for (int t = 0; t < threads; t++) {
            pool.execute(() -> {
                for (int i = 0; i < ops; i++) {
                    int cand = ThreadLocalRandom.current().nextInt(1_000_000);
                    lb.submitHighScore(id, cand);
                    maxSeen.accumulateAndGet(cand, Math::max);
                }
                latch.countDown();
            });
        }
        latch.await(); pool.shutdown();

        if (maxSeen.get() == lb.getScore(id)){
            // must match the max candidate
            System.out.println("Correct! " + maxSeen.get() + "|" + lb.getScore(id));
        }

    }

    private static void runTest() throws InterruptedException {
        var lb = new LeaderBoard();
        int id = lb.registerUser();
        int threads = 20, opsPerThread = 1000;
        var pool = Executors.newFixedThreadPool(threads);
        var latch = new CountDownLatch(threads);

        for (int t = 0; t < threads; t++) {
            pool.execute(() -> {
                for (int i = 0; i < opsPerThread; i++) {
                    lb.addScore(id, 1);
                }
                latch.countDown();
            });
        }
        latch.await();
        pool.shutdown();

        System.out.println("Naive total = " + lb.addScore(id, 0));
    }

}
