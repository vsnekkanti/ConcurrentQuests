import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class CyclicBarrierExample {

    // CyclicBarrier for 3 workers
    private final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        System.out.println("All workers have completed this phase. Moving to the next phase.\n");
    });

    // Simulating the task for each worker
    public void workerTask(String workerName, int sleepTime) {
        try {
            for (int phase = 1; phase <= 3; phase++) { // Each worker completes 3 phases
                System.out.println(workerName + " is working on phase " + phase);

                // Simulate time taken to complete work for this phase
                Thread.sleep(sleepTime);

                // Worker waits at the barrier for others to finish the current phase
                System.out.println(workerName + " is waiting at the barrier to start phase " + (int)(phase+1));
                barrier.await();  // Wait for all workers to reach this point
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            System.out.println(workerName + " was interrupted.");
        }
    }

    public static void main(String[] args) {
        CyclicBarrierExample example = new CyclicBarrierExample();

        // Create three worker threads, each with a different work duration per phase
        Thread worker1 = new Thread(() -> example.workerTask("Worker 1", 1000));
        Thread worker2 = new Thread(() -> example.workerTask("Worker 2", 2000));
        Thread worker3 = new Thread(() -> example.workerTask("Worker 3", 3000));

        // Start all workers
        worker1.start();
        worker2.start();
        worker3.start();
    }
}
