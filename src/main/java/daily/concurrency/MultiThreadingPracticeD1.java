package daily.concurrency;

public class MultiThreadingPracticeD1 implements Runnable {
    /**
     * Practice:
     *
     * Create threads manually
     *
     * Implement a thread that prints numbers 1–10 with delay
     *
     * Simulate concurrent greetings from 5 users
     */

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("%s : %d", "Hello from " + Thread.currentThread().getName(), i + 1));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }



    public static void main(String[] args) {
        Runnable task = new MultiThreadingPracticeD1();
        Thread a = new Thread(task);
        Thread b = new Thread(task);
        Thread c = new Thread(task);
        a.start();
        b.start();
        c.start();
        try {
            a.join();
            b.join();
            c.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }


    }
}
