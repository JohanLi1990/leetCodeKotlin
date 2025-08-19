package daily.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RideHailingSystem {
    // producer
    private final ExecutorService drivers;
    private final BlockingQueue<String> waitingQueue;
    private final int driverCount;
    private static final String POISON = "__POISON__";

    public RideHailingSystem(int N, BlockingQueue<String> waitingQ) {
        drivers = Executors.newFixedThreadPool(N);
        this.waitingQueue = waitingQ;
        this.driverCount = N;

        for (int i = 0; i < N; i++) {
            final int driverId = i;
            drivers.execute(new DriverWorker(driverId, waitingQueue));
        }
    }

    // dispatch hailer
    public boolean addCustomerToQueue(String customerInfo) {
        return waitingQueue.add(customerInfo);
    }

    /**
     * Optional non-blocking variant with timeout (returns false if queue remains full).
     */
    public boolean tryAddCustomerToQueue(String customerInfo) throws InterruptedException {
        return waitingQueue.offer(customerInfo, 2, TimeUnit.SECONDS);
    }

    public void stop() throws InterruptedException {
        for (int i = 0; i < driverCount; i++) {
            waitingQueue.put(POISON); // ensure each driver exits its loop
        }
        drivers.shutdown();
        drivers.awaitTermination(30, TimeUnit.SECONDS);
    }


    private record DriverWorker(int driverId, BlockingQueue<String> queue) implements Runnable {
        private static final AtomicInteger ridesDone = new AtomicInteger();

        @Override
        public void run() {
            try {
                for (; ;) {
                    String customer = queue.take();
                    if (POISON.equals(customer)) {
                        System.out.printf("Driver-%d off duty%n", driverId);
                        break;
                    }
                    System.out.printf("Driver-%d picked up %s%n", driverId, customer);
                    Thread.sleep(500);
                    int count = ridesDone.incrementAndGet();
                    System.out.printf("Driver-%d completed ride for %s (total ride = %d) %n",
                            driverId, customer, count);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        RideHailingSystem system = new RideHailingSystem(3, queue);
        for (int i = 1; i <= 12; i++) {
            system.tryAddCustomerToQueue("user-" + i);
        }

        Thread.sleep(4000);
        system.stop();
        System.out.println("System stopped");
    }
}
