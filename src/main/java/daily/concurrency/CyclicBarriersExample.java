package daily.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarriersExample {

    private static class Worker implements Runnable{

        private final CyclicBarrier cyclicBarrier;

        Worker(CyclicBarrier cb) {
            this.cyclicBarrier = cb;
        }
        @Override
        public void run() {
            try{
                System.out.println(Thread.currentThread().getName() + ":" + "doing 1st part of the work");
                Thread.sleep(500);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + ":" + "doing 2nd part of the work");
                Thread.sleep(500);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + ":" + "We are done!");
            } catch (BrokenBarrierException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
//        var esPool = Executors.newFixedThreadPool(10);
        var cb = new CyclicBarrier(2, () -> System.out.println("老司机发车！"));
        var worker = new Worker(cb);
        var thread1 = new Thread(worker);
        var thread2 = new Thread(worker);
        thread1.start();
        thread2.start();

    }
}
