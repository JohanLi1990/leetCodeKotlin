package daily.concurrency;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * This is the example implementiaton of Dining Philospher with Semaphore
 */
public class DiningPhilospher {

    private final int N;
    private final Semaphore[] sems;
    private final State[] states;

    public DiningPhilospher(int n) {
        this.N = n;
        this.sems = new Semaphore[N];
        this.states = new State[N];
        for (int i = 0; i < N; i++) {
            this.sems[i] = new Semaphore(0);
        }
    }

    private void test(int id) {
        if (states[id] == State.HUNGRY && states[(id + N - 1) % N] != State.EATING && states[(id + 1) % N] != State.EATING) {
            states[id] = State.EATING;
            sems[id].release(); // id philospher can eat now.
        }
    }

    public void takeForks(int id) throws InterruptedException {
        synchronized (this) {
            states[id] = State.HUNGRY;
            System.out.println("Philospher " + id + " is Hungry");
            test(id);
        }
        sems[id].acquire();
    }

    public synchronized void putForks(int id) {
        states[id] = State.THINKING;
        System.out.println("Philosopher " + id + " is THINKING");
        test((id + N - 1) % N);
        test((id + 1 ) % N); // simultaneouslly allow two neigbous to eat.
    }

    // implement Enum state
    private enum State {
        THINKING, HUNGRY, EATING
    }

    public static void main(String[] args) {
        var dp = new DiningPhilospher(5);
        for (int i = 0; i < 5; i++) {
            final int philo = i;
            var random = new Random(1);
            new Thread(() -> {
                try {
                    System.out.println("Philosopher " + philo + " is Thinking");
                    Thread.sleep(random.nextLong(1000));
                    dp.takeForks(philo);
                    System.out.println("Philosopher " + philo + " is Eating");
                    Thread.sleep(random.nextLong(1000));
                    dp.putForks(philo);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
