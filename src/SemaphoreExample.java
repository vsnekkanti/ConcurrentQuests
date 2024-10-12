import java.util.concurrent.Semaphore;

class SemaphoreExample {
    private final Semaphore semaphore = new Semaphore(2); // Only 2 permits

    public void accessResource() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " acquired a permit and is accessing the resource.");
            Thread.sleep(5000); // Simulate resource usage
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " releasing the permit.");
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreExample example = new SemaphoreExample();

        // Create multiple threads to simulate resource access
        for (int i = 0; i < 5; i++) {
            new Thread(example::accessResource, "Thread-" + i).start();
        }
    }
}
