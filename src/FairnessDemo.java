import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FairnessDemo {
    private final Lock lock;

    public FairnessDemo(boolean isFair) {
        lock = new ReentrantLock(isFair); // Fair lock if true
    }

    public void increment() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            Thread.sleep(100); // Simulating work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairnessDemo demo = new FairnessDemo(true); // Fair lock
        for (int i = 1; i <= 5; i++) {
            final int threadNum = i;
            new Thread(() -> demo.increment(), "Thread-" + threadNum).start();
        }
    }
}
