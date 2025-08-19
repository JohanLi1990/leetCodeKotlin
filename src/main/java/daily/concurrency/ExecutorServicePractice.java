package daily.concurrency;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class ExecutorServicePractice {

    public static void main(String[] args) {
        // This is a placeholder for the main method.
        // You can implement your logic here using ExecutorService.
        System.out.println("ExecutorService Practice");

        List<Callable<String>> downloadTasks = getCallables();
        ExecutorService es = newFixedThreadPool(4);
        try {
            System.out.println("=== invokeAll with Timeout ====");
            List<Future<String>> allResults = es.invokeAll(downloadTasks, 4, TimeUnit.SECONDS);
            for (int i = 0; i < allResults.size(); i++) {
                Future<String> future = allResults.get(i);
                try {
                    if (!future.isCancelled()) {
                        System.out.println(future.get());
                    } else {
                        System.out.println("Task #" + (i + 1) + " is cancelled.");
                    }
                } catch (ExecutionException e) {
                    System.out.println("Task #" + (i + 1) + " failed: " + e.getCause().getMessage());
                }
            }
            System.out.println("\n=========== Invoke Any with Timeout ============");
            try {
                String result = es.invokeAny(downloadTasks, 4, TimeUnit.SECONDS);
                System.out.println("First successful result: " + result);
            } catch (ExecutionException e) {
                System.out.println("invokeAny failed: " + e.getCause().getMessage());
            } catch (TimeoutException e) {
                System.out.println("InvokeAny imed out without a successful result.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            es.shutdownNow();
            try {
                if (!es.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Executor did not terminate in time. ");
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    @NotNull
    private static List<Callable<String>> getCallables() {
        List<Callable<String>> downloadTasks = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            int taskId = i;
            downloadTasks.add(() -> {
                long sleep = (long) (Math.random() * 3000);
                Thread.sleep(sleep);
                if (Math.random() < 0.2) {
                    throw new RuntimeException("Download failed for task " + taskId);
                }
                return "Download file #" + taskId + " in " + sleep + " ms by " + Thread.currentThread();
            });
        }
        return downloadTasks;
    }
}
