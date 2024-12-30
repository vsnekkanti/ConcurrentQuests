import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class PrimeSum {
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static class PrimeTask implements Callable<Long> {
        private int start, end;

        public PrimeTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() {
            long sum = 0;  // Use long to avoid overflow
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    sum += i;
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        int workers = 10;
        int rangeStart = 2;
        int rangeEnd = 90000000;
        int chunkSize = (rangeEnd - rangeStart + 1) / workers;

        ExecutorService executor = Executors.newFixedThreadPool(workers);
        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < workers; i++) {
            int start = rangeStart + i * chunkSize;
            int end = start + chunkSize - 1;
            if (i == workers - 1) {
                end = rangeEnd; // Last worker handles remaining range
            }
            futures.add(executor.submit(new PrimeTask(start, end)));
        }

        long result = 0;
        for (Future<Long> future : futures) {
            result += future.get();
        }

        executor.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println("Sum of primes: " + result);
        System.out.println("Execution time: " + (endTime - startTime) / 1000.0 + " seconds");
    }
}
