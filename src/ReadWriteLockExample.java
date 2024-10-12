import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteLockExample {
    private int data = 0; // Shared resource
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    // Read operation
    public void readData() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read the data: " + data);
        } finally {
            lock.readLock().unlock();
        }
    }

    // Write operation
    public void writeData(int value) throws InterruptedException {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " wrote the data: " + value);
            this.data = value;
            Thread.sleep(2000);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();

        // Create threads that perform read and write operations
        Runnable readTask = () -> example.readData();
        Runnable writeTask = () -> {
            try {
                example.writeData((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(readTask, "ReadThread-" + 4).start();
        new Thread(readTask, "ReadThread-" + 5).start();

        new Thread(writeTask, "WriteThread").start();

        // Start multiple read threads and one write thread
        for (int i = 0; i < 3; i++) {
            new Thread(writeTask, "WriteThread").start();
            new Thread(readTask, "ReadThread-" + i).start();
        }
    }
}
