import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class InterruptibleLockDemo {
    private final Lock lock = new ReentrantLock();

    public void interruptibleTask() throws InterruptedException {
        lock.lockInterruptibly(); // Acquire lock, but can be interrupted
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the interruptible lock.");
            Thread.sleep(5000); // Simulate work
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        InterruptibleLockDemo demo = new InterruptibleLockDemo();

        Thread t1 = new Thread(() -> {
            try {
                demo.interruptibleTask();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
        }, "Thread-1");

        t1.start();

        // Interrupt the thread while it is waiting to acquire the lock
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Delay before interrupting
                t1.interrupt();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
