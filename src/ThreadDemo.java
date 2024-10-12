// Thread creation by extending the Thread class
class ThreadExt extends Thread {
    private String threadName;

    ThreadExt(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        System.out.println(threadName + " is starting.");
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + " output: " + i);
            try {
                Thread.sleep(100);  // Introduce a small delay
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
            }
        }
        System.out.println(threadName + " is exiting.");
    }
}

// Thread creation by implementing the Runnable interface
class RunnableImpl implements Runnable {
    private String threadName;

    RunnableImpl(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        System.out.println(threadName + " is starting.");
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + " output: " + i);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
            }
        }
        System.out.println(threadName + " is exiting.");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        // Using Thread class
//        ThreadExt t1 = new ThreadExt("ThreadClass Thread");
//        t1.start();
//
//        // Using Runnable interface
//        Thread t2 = new Thread(new RunnableImpl("RunnableImpl Thread"));
//        t2.start();

        // Using Lambda expression (Java 8 and above)
        Runnable lambdaRunnable = () -> {
            String threadName = "Lambda Thread";
            System.out.println(threadName + " is starting.");
            for (int i = 0; i < 5; i++) {
                System.out.println(threadName + " output: " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " interrupted.");
                }
            }
            System.out.println(threadName + " is exiting.");
        };
        Thread t3 = new Thread(lambdaRunnable);
        t3.start();

        // Main thread will wait for other threads to finish
        try {
//            t1.join();
//            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread is exiting.");
    }
}
