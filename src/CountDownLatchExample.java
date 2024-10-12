import java.util.concurrent.CountDownLatch;

class CountDownLatchExample {
    private final CountDownLatch latch = new CountDownLatch(3);

    public void performTask() {
        try {
            System.out.println(Thread.currentThread().getName() + " is performing a task.");
            Thread.sleep(5000); // Simulate task
            latch.countDown(); // Decrement the latch count
            System.out.println(Thread.currentThread().getName() + " completed the task. Latch count: " + latch.getCount());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void awaitCompletion() {
        try {
            latch.await(); // Wait for the latch count to reach 0
            System.out.println("All tasks are completed. Proceeding with further execution.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        CountDownLatchExample example = new CountDownLatchExample();

        // Create threads to perform tasks
        for (int i = 0; i < 3; i++) {
            new Thread(example::performTask, "Worker-" + i).start();
        }

        // Wait for all tasks to complete
        new Thread(example::awaitCompletion).start();
    }
}


//CountDownLatch: The main thread waits for all the worker threads
// to complete by calling await().
// Once all threads have called countDown(),
// the latch reaches 0 and the main thread proceeds.