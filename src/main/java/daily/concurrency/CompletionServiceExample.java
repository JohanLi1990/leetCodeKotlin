package daily.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample {

    static class LongRunningTask implements Callable<String> {
        private final long sleepSeconds;

        LongRunningTask(long sleepSeconds) {
            this.sleepSeconds = sleepSeconds;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(sleepSeconds);
            return String.format("Thread %s takes %d to complete", Thread.currentThread().getName(), sleepSeconds);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NormalExecutorService();
        CompletionSerivce();
    }

    private static void CompletionSerivce() throws ExecutionException, InterruptedException {
        var es = Executors.newFixedThreadPool(3);
        List<Callable<String>> callables = List.of(new LongRunningTask(6000), new LongRunningTask(4000), new LongRunningTask(5000));
        // 构建ExecutorCompletionService, 与线程池关联
        ExecutorCompletionService<String> cs = new ExecutorCompletionService<>(es);
        for (Callable<String> c : callables) {
            cs.submit(c);
        }
        for (int i = 0; i < callables.size(); i++) {
            Future<String> res = cs.take();
            System.out.println(res.get());
        }
        es.shutdown();

    }

    public static void NormalExecutorService() throws ExecutionException, InterruptedException {
        var es = Executors.newFixedThreadPool(3);
        List<Callable<String>> callables = List.of(new LongRunningTask(6000), new LongRunningTask(4000), new LongRunningTask(5000));
        List<Future<String>> res = new ArrayList<>();
        for (Callable<String> task : callables) {
            res.add(es.submit(task));
        }
        for (Future<String> cur : res) {
            System.out.println(cur.get());
        }
        es.shutdown();
    }
}
