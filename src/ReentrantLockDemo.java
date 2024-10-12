import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Shared resource class demonstrating various ReentrantLock features
class SharedResource {
    private final Lock lock;
    private final Condition condition;
    private int counter = 0;

    // Constructor with an option for fairness
    public SharedResource(boolean isFair) {
        // ReentrantLock with fairness
        this.lock = new ReentrantLock(isFair);
        this.condition = lock.newCondition(); // Condition for wait/notify
    }

    // Reentrancy: A thread can acquire the lock multiple times
    public void increment() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock and incrementing.");
            counter++;
            if (counter == 5) {
                condition.signalAll(); // Signal waiting threads when condition is met
            }
            System.out.println("Counter: " + counter);
        } finally {
            lock.unlock();
        }
    }

    // Demonstrate reentrancy by calling increment from another method
    public void doubleIncrement() {
        lock.lock();
        try {
            increment(); // Reentrant lock: this method also locks the resource
            increment();
        } finally {
            lock.unlock();
        }
    }

    // Interruptible locking: waiting thread can be interrupted
    public void interruptibleLock() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired interruptible lock.");
            Thread.sleep(1000); // Simulate long operation
        } finally {
            lock.unlock();
        }
    }

    // Condition-based waiting: Threads wait until a specific condition is met
    public void waitForCondition() throws InterruptedException {
        lock.lock();
        try {
            while (counter < 5) {
                System.out.println(Thread.currentThread().getName() + " waiting for condition (counter = 5).");
                condition.await(); // Wait for condition signal
            }
            System.out.println(Thread.currentThread().getName() + " condition met. Proceeding.");
        } finally {
            lock.unlock();
        }
    }
}

// Main class demonstrating various real-world scenarios with ReentrantLock
public class ReentrantLockDemo {
    public static void main(String[] args) {
        // Fair lock to demonstrate fairness in lock acquisition
        SharedResource sharedResource = new SharedResource(true);

        // Thread demonstrating reentrancy (calling increment twice)
        Thread reentrantThread = new Thread(() -> {
            sharedResource.doubleIncrement();
        }, "ReentrantThread");

        // Thread demonstrating interruptibility
        Thread interruptibleThread = new Thread(() -> {
            try {
                sharedResource.interruptibleLock();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
        }, "InterruptibleThread");

        // Threads waiting for condition (counter to reach 5)
        Thread waitingThread1 = new Thread(() -> {
            try {
                sharedResource.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "WaitingThread-1");

        Thread waitingThread2 = new Thread(() -> {
            try {
                sharedResource.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "WaitingThread-2");

        // Demonstrating fairness in lock acquisition with multiple threads
        Thread fairnessThread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                sharedResource.increment();
            }
        }, "FairnessThread-1");

        Thread fairnessThread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                sharedResource.increment();
            }
        }, "FairnessThread-2");

        // Start threads demonstrating different scenarios
        reentrantThread.start();
        interruptibleThread.start();
        waitingThread1.start();
        waitingThread2.start();
        fairnessThread1.start();
        fairnessThread2.start();

        // Interrupt interruptible thread after a short delay
        new Thread(() -> {
            try {
                Thread.sleep(500); // Wait for a bit before interrupting
                interruptibleThread.interrupt();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
