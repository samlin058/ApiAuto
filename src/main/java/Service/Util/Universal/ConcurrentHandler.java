package Service.Util.Universal;

import java.util.List;
import java.util.concurrent.*;

public class ConcurrentHandler {

    /**
     * 并发执行指定数量的相同任务
     * @param task 需要执行的任务（Runnable）
     * @param threadCount 并发线程数
     */
    public static void runConcurrent(Runnable task, int threadCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.submit(task);
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 并发执行多个不同的任务
     * @param tasks 任务列表
     */
    public static void runConcurrent(List<Runnable> tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(tasks.size());
        for (Runnable task : tasks) {
            executor.submit(task);
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
} 