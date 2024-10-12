import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class ExecutorFrameworkExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1. Single-thread Executor
        // Scenario: When tasks need to be executed sequentially, ensuring that only one task runs at a time.
        // This is useful for tasks that should not overlap or are dependent on each other.
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        System.out.println("Single-thread Executor:");
        singleThreadExecutor.execute(() -> {
            System.out.println("Task 1 executed by " + Thread.currentThread().getName());
        });
        singleThreadExecutor.shutdown(); // Shutdown after task completion

        // 2. Fixed-thread pool (size: 3)
        // Scenario: When you know the number of concurrent tasks and want to limit the number of active threads.
        // Useful for CPU-bound tasks where a fixed number of threads is optimal.
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        System.out.println("\nFixed-thread Pool:");
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final int taskID = i;
            tasks.add(() -> "Task " + taskID + " executed by " + Thread.currentThread().getName());
        }
        List<Future<String>> results = fixedThreadPool.invokeAll(tasks);
        for (Future<String> result : results) {
            System.out.println(result.get());
        }
        fixedThreadPool.shutdown();

        // 3. Cached-thread pool (Dynamic pool size)
        // Scenario: When there are many short-lived tasks that need to be executed dynamically.
        // CachedThreadPool creates new threads as needed and reuses previously created threads.
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("\nCached-thread Pool:");
        cachedThreadPool.submit(() -> {
            System.out.println("Task submitted to cached pool executed by " + Thread.currentThread().getName());
        });
        cachedThreadPool.shutdown();

        // 4. Scheduled-thread pool
        // Scenario: When you need to run tasks after a delay or periodically (e.g., for periodic background jobs).
        // ScheduledThreadPoolExecutor is useful for tasks that should run periodically or at a specific time.
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("\nScheduled-thread Pool:");
        Runnable scheduledTask = () -> System.out.println("Scheduled task executed at " + System.currentTimeMillis());
        scheduledThreadPool.schedule(scheduledTask, 2, TimeUnit.SECONDS); // Execute task after 2 seconds delay

        scheduledThreadPool.scheduleAtFixedRate(() -> {
            System.out.println("Repeated task executed by " + Thread.currentThread().getName());
        }, 1, 3, TimeUnit.SECONDS); // Execute repeatedly every 3 seconds, after an initial delay of 1 second

        // Allow the scheduled tasks to execute for a few seconds
        Thread.sleep(8000);
        scheduledThreadPool.shutdown();

        // 5. ForkJoinPool (for parallel processing)
        // Scenario: Useful for parallel processing in recursive tasks that can be split into smaller subtasks,
        // such as divide-and-conquer algorithms (e.g., sorting large datasets).
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.println("\nForkJoinPool Example:");
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(() -> IntStream.range(1, 101).parallel().sum());
        System.out.println("ForkJoinPool sum: " + forkJoinTask.get());

        // Ensure all thread pools have shut down
        if (!forkJoinPool.isShutdown()) {
            forkJoinPool.shutdown();
        }

        // 6. ThreadPoolExecutor (Custom thread pool configuration)
        // Scenario: When you need fine-grained control over thread pool behavior (e.g., core pool size,
        // max pool size, queue size, etc.). This is useful for advanced thread management.
        System.out.println("\nThreadPoolExecutor Example:");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, // core pool size
                4, // maximum pool size
                60L, // keep-alive time
                TimeUnit.SECONDS, // time unit for keep-alive time
                new LinkedBlockingQueue<>(2) // work queue with capacity 2
        );

        // Submit tasks to the ThreadPoolExecutor
        for (int i = 1; i <= 6; i++) {
            final int taskID = i;
            threadPoolExecutor.submit(() -> {
                System.out.println("Task " + taskID + " executed by " + Thread.currentThread().getName());
                try {
                    // Simulating some work
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS); // Wait for termination

        // 7. WorkStealingPool (for uneven task workloads)
        // Scenario: When you have a set of tasks that vary in execution time, and you want to ensure
        // efficient load balancing across threads. Useful for uneven workloads with varying execution times.
        System.out.println("\nWorkStealingPool Example:");
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        List<Callable<String>> stealingTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int taskID = i;
            stealingTasks.add(() -> {
                Thread.sleep((long) (Math.random() * 1000)); // Simulating variable-length tasks
                return "Task " + taskID + " executed by " + Thread.currentThread().getName();
            });
        }

        List<Future<String>> workStealingResults = workStealingPool.invokeAll(stealingTasks);
        for (Future<String> result : workStealingResults) {
            System.out.println(result.get());
        }
        workStealingPool.shutdown();

        // 8. ExecutorCompletionService (processing tasks as they complete)
        // Scenario: When you submit multiple tasks and want to process their results as they complete,
        // rather than waiting for all tasks to finish. Useful when some tasks take longer than others.
        System.out.println("\nExecutorCompletionService Example:");
        ExecutorService completionServiceExecutor = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService = new ExecutorCompletionService<>(completionServiceExecutor);

        for (int i = 0; i < 5; i++) {
            final int taskID = i;
            completionService.submit(() -> {
                Thread.sleep((long) (Math.random() * 2000)); // Simulate varying task durations
                return "Task " + taskID + " completed by " + Thread.currentThread().getName();
            });
        }

        // Processing tasks as they complete
        for (int i = 0; i < 5; i++) {
            Future<String> completedTask = completionService.take(); // Take the first completed task
            System.out.println(completedTask.get());
        }

        completionServiceExecutor.shutdown();
    }
}
