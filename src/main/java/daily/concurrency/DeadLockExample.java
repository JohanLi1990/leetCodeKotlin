package daily.concurrency;

public class DeadLockExample {

    public static void main(String[] args) throws InterruptedException {
        Object A = new Object();
        Object B = new Object();
        Runnable runnable1 = () ->{
            while(true) {
                synchronized (A) {
                    System.out.println(Thread.currentThread().getName() + "locked A!");
                    synchronized (B) {
                        System.out.println(Thread.currentThread().getName() + "locked B!");
                    }
                }
            }
        };

        Runnable runnable2 = () ->{
            while (true) {
                synchronized (B) {
                    System.out.println(Thread.currentThread().getName() + "locked B!");
                    synchronized (A) {
                        System.out.println(Thread.currentThread().getName() + "locked A!");
                    }
                }
            }
        };
        var ta = new Thread(runnable1);

        var tb = new Thread(runnable2);
        ta.start();
        tb.start();
        ta.interrupt(); // interrupt cannot stop a BLOCKED thread, only THREAD that is waiting(), join(), sleep()
        tb.interrupt();
        System.out.println("Terminated");

    }
}
