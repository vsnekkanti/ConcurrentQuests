import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Demonstrating Reentrancy in ReentrantLock
class ReentrancyDemo {
    private final Lock lock = new ReentrantLock();
    private int counter = 0;

    // Reentrant method
    public void doubleIncrement() {
        lock.lock(); // Lock acquired
        try {
            increment(); // Reentrant: calling another synchronized method
            increment();
        } finally {
            lock.unlock(); // Lock released
        }
    }

    // Method that increments counter
    public void increment() {
        lock.lock(); // Reentrant: lock acquired again
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented the counter. Counter: " + counter);
        } finally {
            lock.unlock(); // Lock released
        }
    }

    public static void main(String[] args) {
        ReentrancyDemo demo = new ReentrancyDemo();
        Thread t1 = new Thread(() -> demo.doubleIncrement(), "Thread-1");
        Thread t2 = new Thread(() -> demo.doubleIncrement(), "Thread-2");

        t1.start();
        t2.start();
    }
}
