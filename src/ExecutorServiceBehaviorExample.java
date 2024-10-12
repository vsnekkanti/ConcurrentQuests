import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class ExecutorServiceBehaviorExample {

    public static void main(String[] args) throws InterruptedException {
        // 1. Example with Unbounded Queue (LinkedBlockingQueue)
        System.out.println("Example 1: FixedThreadPool with Unbounded Queue (LinkedBlockingQueue)");
        ExecutorService unboundedThreadPool = new ThreadPoolExecutor(
                2, // core pool size
                2, // maximum pool size (ignored with unbounded queue)
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()); // Unbounded queue
        
        submitTasks(unboundedThreadPool, 10); // Submitting more tasks than the number of threads
        unboundedThreadPool.shutdown();
        
        // 2. Example with Bounded Queue (ArrayBlockingQueue)
        System.out.println("\nExample 2: FixedThreadPool with Bounded Queue (ArrayBlockingQueue)");
        ExecutorService boundedThreadPool = new ThreadPoolExecutor(
                2, // core pool size
                2, // maximum pool size
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2)); // Bounded queue of size 2
        
        submitTasksWithBlocking(boundedThreadPool, 10); // Submitting more tasks than the capacity of threads and queue
        boundedThreadPool.shutdown();
        
        // 3. Example with CachedThreadPool (dynamic thread pool)
        System.out.println("\nExample 3: CachedThreadPool (dynamic thread pool)");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        submitTasks(cachedThreadPool, 10); // Submitting a large number of tasks, dynamic thread creation
        cachedThreadPool.shutdown();

        // 4. Example with WorkStealingPool (dynamic thread pool)
        System.out.println("\nExample 4: WorkStealingPool (dynamic thread pool)");
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        submitTasks(workStealingPool, 10); // Submitting a large number of tasks, work-stealing mechanism
        workStealingPool.shutdown();
    }

    // Helper method to submit tasks without blocking (for unbounded queues or dynamic thread pools)
    private static void submitTasks(ExecutorService executorService, int numberOfTasks) throws InterruptedException {
        for (int i = 0; i < numberOfTasks; i++) {
            int taskId = i;
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
                try {
                    Thread.sleep(500); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    // Helper method to submit tasks and handle blocking (for bounded queues)
    private static void submitTasksWithBlocking(ExecutorService executorService, int numberOfTasks) throws InterruptedException {
        for (int i = 0; i < numberOfTasks; i++) {
            int taskId = i;
            try {
                System.out.println("Submitting task " + taskId);
                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
                    try {
                        Thread.sleep(500); // Simulate some work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("Task " + taskId + " was rejected (queue is full)");
            }
        }
    }
}
