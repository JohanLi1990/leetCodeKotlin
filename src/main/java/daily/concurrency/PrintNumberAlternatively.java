package daily.concurrency;

public class PrintNumberAlternatively  {

    private int curNumber = 0;
    boolean t1Turn = true;
    private final Object monitor = new Object();
    public static void main(String[] args) throws InterruptedException {
        var task = new PrintNumberAlternatively();
        var t1 = new Thread(() -> {
            try {
                task.printNumber(true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var t2 = new Thread(() -> {
            try {
                task.printNumber(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }


    private void printNumber(boolean myturn) throws InterruptedException {
        synchronized (monitor) {
            while(curNumber < 200) {
                while(t1Turn != myturn) {
                    monitor.wait();
                }
                System.out.println(Thread.currentThread().getName() +":" + curNumber++);
                t1Turn = !t1Turn;
                monitor.notify();
            }
        }

    }

}
