import java.util.concurrent.*;
import java.util.LinkedList;
import java.util.List;

public class ConcurrentQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. ConcurrentLinkedQueue
        // ConcurrentLinkedQueue: A thread-safe, non-blocking FIFO queue with no capacity limit.
        System.out.println("ConcurrentLinkedQueue Example:");
        ConcurrentLinkedQueue<Integer> concurrentQueue = new ConcurrentLinkedQueue<>();
        concurrentQueue.offer(1);  // Inserts element, never blocks or throws exceptions.
        concurrentQueue.offer(2);
        System.out.println("Peek: " + concurrentQueue.peek()); // Retrieves but does not remove the head (null if empty).
        System.out.println("Poll: " + concurrentQueue.poll()); // Retrieves and removes the head (null if empty).
        System.out.println("IsEmpty: " + concurrentQueue.isEmpty()); // Returns true if empty.
        System.out.println("Size: " + concurrentQueue.size()); // Returns the size of the queue.
        System.out.println();

        // 2. LinkedBlockingQueue
        // LinkedBlockingQueue: A bounded (optional) blocking FIFO queue with optional capacity limits.
        System.out.println("LinkedBlockingQueue Example:");
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(5); // Bounded queue
        linkedBlockingQueue.put(1);  // Inserts element, blocks if full.
        linkedBlockingQueue.put(2);
        linkedBlockingQueue.put(3);

        // Demonstrating drainTo() - moving elements to another collection
        List<Integer> drainedList1 = new LinkedList<>();
        linkedBlockingQueue.drainTo(drainedList1);  // Drains all elements to another collection
        System.out.println("Drained List from LinkedBlockingQueue: " + drainedList1); // Should contain [1, 2, 3]
        System.out.println("LinkedBlockingQueue after drainTo: " + linkedBlockingQueue.size()); // Should be 0 since all elements are drained

        // Adding more elements to demonstrate the second version of drainTo()
        linkedBlockingQueue.put(4);
        linkedBlockingQueue.put(5);
        linkedBlockingQueue.put(6);

        // Demonstrating drainTo(collection, maxElements)
        List<Integer> drainedList2 = new LinkedList<>();
        linkedBlockingQueue.drainTo(drainedList2, 2);  // Drains only 2 elements to another collection
        System.out.println("Drained List with maxElements from LinkedBlockingQueue: " + drainedList2); // Should contain [4, 5]
        System.out.println("LinkedBlockingQueue after partial drainTo: " + linkedBlockingQueue.size()); // Should be 1 since 2 elements were drained

        System.out.println();

        // 3. ArrayBlockingQueue
        // ArrayBlockingQueue: A bounded, blocking FIFO queue backed by an array with fixed capacity.
        System.out.println("ArrayBlockingQueue Example:");
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(5); // Bounded queue
        arrayBlockingQueue.put(1);  // Inserts the specified element.
        arrayBlockingQueue.put(2);
        arrayBlockingQueue.put(3);
        arrayBlockingQueue.put(4);

        // Demonstrating drainTo()
        List<Integer> drainedList3 = new LinkedList<>();
        arrayBlockingQueue.drainTo(drainedList3);  // Drains all elements to another collection
        System.out.println("Drained List from ArrayBlockingQueue: " + drainedList3); // Should contain [1, 2, 3, 4]
        System.out.println("ArrayBlockingQueue after drainTo: " + arrayBlockingQueue.size()); // Should be 0 since all elements are drained

        System.out.println();

        // 4. PriorityBlockingQueue
        // PriorityBlockingQueue: An unbounded, thread-safe priority queue where elements are ordered by natural order or comparator.
        System.out.println("PriorityBlockingQueue Example:");
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        priorityBlockingQueue.put(10);  // Insert elements with natural order.
        priorityBlockingQueue.put(5);
        priorityBlockingQueue.put(20);
        System.out.println("Poll: " + priorityBlockingQueue.poll()); // Retrieves and removes the smallest element.
        System.out.println("Take: " + priorityBlockingQueue.take()); // Blocks until an element is available.
        System.out.println("Peek: " + priorityBlockingQueue.peek()); // Retrieves but does not remove the smallest element.
        System.out.println("Contains (5): " + priorityBlockingQueue.contains(5)); // Checks if the queue contains a specific element.
        System.out.println("Size: " + priorityBlockingQueue.size()); // Size of the queue.
        System.out.println();

        // 5. SynchronousQueue
        // SynchronousQueue: A blocking queue with no capacity where each insert must wait for a corresponding remove.
        System.out.println("SynchronousQueue Example:");
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                synchronousQueue.put(1);  // Blocks until another thread takes the element.
                System.out.println("Element added to SynchronousQueue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("Take from SynchronousQueue: " + synchronousQueue.take());  // Blocks until the element is available.
        System.out.println();

        // 6. DelayQueue
        // DelayQueue: A blocking queue where elements are only available after a certain delay.
        System.out.println("DelayQueue Example:");
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayedElement("Element1", 2000));  // Element will be available after 2 seconds.
        System.out.println("Poll (immediate): " + delayQueue.poll()); // Returns null because no element is ready.
        System.out.println("Take (blocks until ready): " + delayQueue.take());  // Blocks until the element's delay has passed.
        System.out.println("Size: " + delayQueue.size()); // Size of the queue.
        System.out.println();

        // 7. LinkedTransferQueue
        // LinkedTransferQueue: An unbounded, thread-safe queue where producers can wait for consumers to take elements.
        System.out.println("LinkedTransferQueue Example:");
        LinkedTransferQueue<Integer> linkedTransferQueue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                System.out.println("Transfer element: 100");
                linkedTransferQueue.transfer(100);  // Blocks until another thread receives the element.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("Take from LinkedTransferQueue: " + linkedTransferQueue.take()); // Receives the element and unblocks the producer.
        linkedTransferQueue.add(200); // Add element to the queue.
        System.out.println("Poll: " + linkedTransferQueue.poll()); // Retrieves and removes the head.
        System.out.println("Remaining Capacity (unbounded so no limit): " + linkedTransferQueue.remainingCapacity());
        System.out.println("Has Waiting Consumer: " + linkedTransferQueue.hasWaitingConsumer()); // Check if any threads are waiting for the element.
        System.out.println("Size: " + linkedTransferQueue.size()); // Size of the queue.
        System.out.println();

        // 8. BlockingDeque
        // BlockingDeque: A thread-safe, blocking deque (double-ended queue) that allows insertions and removals from both ends.
        System.out.println("BlockingDeque Example:");
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>(3); // Bounded Deque

        // Insertion at both ends
        blockingDeque.putFirst(1);  // Inserts element at the front, blocks if full.
        blockingDeque.putLast(2);   // Inserts element at the rear, blocks if full.

        System.out.println("OfferFirst (will succeed): " + blockingDeque.offerFirst(3)); // Inserts at front, returns true if successful.
        System.out.println("OfferLast (will fail due to full): " + blockingDeque.offerLast(4)); // False if deque is full.

        // Retrieval from both ends
        System.out.println("PollFirst: " + blockingDeque.pollFirst()); // Retrieves and removes from front.
        System.out.println("PollLast: " + blockingDeque.pollLast());   // Retrieves and removes from rear.

        // Blocking retrieval from both ends
        blockingDeque.putFirst(10);
        blockingDeque.putLast(20);
        System.out.println("TakeFirst (blocks if empty): " + blockingDeque.takeFirst()); // Blocks until available from front.
        System.out.println("TakeLast (blocks if empty): " + blockingDeque.takeLast());   // Blocks until available from rear.

        System.out.println("Size of BlockingDeque: " + blockingDeque.size());
        System.out.println();
    }

    // A helper class for DelayQueue elements
    static class DelayedElement implements Delayed {
        private String element;
        private long delayTime;
        private long startTime;

        public DelayedElement(String element, long delayTime) {
            this.element = element;
            this.delayTime = delayTime;
            this.startTime = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = startTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return element;
        }
    }
}
