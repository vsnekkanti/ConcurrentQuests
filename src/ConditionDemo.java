import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConditionDemo {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int counter = 0;

    public void increment() {
        lock.lock();
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented counter: " + counter);
            if (counter == 5) {
                condition.signalAll(); // Signal waiting threads when counter reaches 5
            }
        } finally {
            lock.unlock();
        }
    }

    public void waitForCondition() throws InterruptedException {
        lock.lock();
        try {
            while (counter < 5) {
                System.out.println(Thread.currentThread().getName() + " waiting for counter to reach 5.");
                condition.await(); // Wait until signal is received
            }
            System.out.println(Thread.currentThread().getName() + " condition met. Proceeding.");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();

        // Threads waiting for the condition
        new Thread(() -> {
            try {
                demo.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "WaitingThread-1").start();

        new Thread(() -> {
            try {
                demo.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "WaitingThread-2").start();

        // Incrementing the counter in a separate thread
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.increment();
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "IncrementingThread").start();
    }
}
