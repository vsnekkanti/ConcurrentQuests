import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {
    
    // A task to sum part of an array
    static class SumTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start;
        private final int end;
        private static final int THRESHOLD = 10;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= THRESHOLD) {  // Base case: small enough to compute directly
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {  // Split task into smaller tasks
                int middle = (start + end) / 2;
                SumTask leftTask = new SumTask(array, start, middle);
                SumTask rightTask = new SumTask(array, middle, end);
                leftTask.fork();  // Execute left task asynchronously
                int rightResult = rightTask.compute();  // Execute right task in the current thread
                int leftResult = leftTask.join();  // Wait for left task to complete
                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        // Create an array to sum
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());

        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        // Create a ForkJoinPool and a SumTask
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length);

        // Invoke the task and get the result
        int result = pool.invoke(task);
        System.out.println("Sum of array: " + result);
    }
}
