import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureAdvancedExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // First async task
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executing task 1...");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });

        // Second async task
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executing task 2...");
            return 20;
        });

        System.out.println(future2.get());

        // Combine both results from future1 and future2
        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1, result2) -> {
            System.out.println("Combining results...");
            return result1 + result2;
        });

        // Handle the final result
        combinedFuture.thenAccept(finalResult -> {
            System.out.println("Final combined result: " + finalResult);
        });

        // Handle exceptions
        CompletableFuture<Integer> errorHandlingFuture = combinedFuture
                .exceptionally(ex -> {
                    System.out.println("An error occurred: " + ex.getMessage());
                    return null;
                });

        // Wait for everything to finish
        errorHandlingFuture.get();
    }
}
