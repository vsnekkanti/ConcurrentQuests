import java.util.concurrent.*;

// Task that implements Runnable (Does not return a value)
class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable Task is being executed by: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulate a time-consuming task
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Runnable Task completed.");
    }
}

// Task that implements Callable (Returns a value)
class CallableTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Callable Task is being executed by: " + Thread.currentThread().getName());
        Thread.sleep(3000); // Simulate a time-consuming task
        return "Callable Task result";
    }
}

public class EnterpriseAppExample {
    public static void main(String[] args) {
        // Create an ExecutorService with a thread pool of 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit a Runnable task
        RunnableTask runnableTask = new RunnableTask();
        executorService.submit(runnableTask);  // Runnable doesn't return any result

        // Submit a Callable task and capture the Future
        CallableTask callableTask = new CallableTask();
        Future<String> future = executorService.submit(callableTask); // Callable returns a result

        // Demonstrate Thread usage directly (not recommended in large-scale enterprise apps)
        Thread thread = new Thread(new RunnableTask());
        thread.start();

        // Now let's handle the Future result
        try {
            // Get the result from Callable's Future (blocks until the result is available)
            String result = future.get();
            System.out.println("Result from CallableTask: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Always shut down the ExecutorService to release resources
        executorService.shutdown();
        try {
            // Wait until all tasks are finished
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();  // Force shutdown if tasks are taking too long
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        System.out.println("ExecutorService has been shut down.");
    }
}
