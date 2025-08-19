package daily.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExecise {
    /**
     *          开始
     *            |
     *    ----------------
     *    |              |
     *   CF1           CF2
     *    | \          / \
     *    |  \        /   \
     *   CF3  CF4   CF4   CF5
     *     \    \    /     /
     *       \    \ /     /
     *            CF6 (结束)
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 1. 发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("%s is running cf1", Thread.currentThread().getName()));
            return "result1";
        }, executorService);

        // 2.
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("result2");
        // 3. initialize an incomplete CompletableFuture, then use complete(), completeExceptionally(), to enrich
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("succcess");

        CompletableFuture<String> cf3 = cf1.thenApplyAsync(rs -> {
            System.out.println(String.format("%s, %s is running cf3", rs, Thread.currentThread().getName()));
            return "result3";
        }, executorService);

        CompletableFuture<String> cf5 = cf1.thenApplyAsync(rs -> {
            System.out.println(String.format("%s, %s is running cf5", rs, Thread.currentThread().getName()));
            return "result5";
        }, executorService);

        CompletableFuture<String> cf4 = cf1.thenCombineAsync(cf2, (result1, result2) -> {
            //result1和result2分别为cf1和cf2的结果
            System.out.println(String.format("%s is running for cf4", Thread.currentThread().getName()));
            return "result4: " + result1 + ", " + result2;
        }, executorService);

        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf3, cf4, cf5);
        var res = cf6.thenApplyAsync(v -> {
            String result3 = cf3.join();
            String result4 = cf4.join();
            String result5 = cf5.join();
            return result3 + "|" + result4 + "|" + result5;
        }, executorService);
        System.out.println("Completed: " + res.get());

        executorService.shutdown();
    }


}
