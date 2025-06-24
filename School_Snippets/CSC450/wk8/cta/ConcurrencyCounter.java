package wk8.cta;

public class ConcurrencyCounter {

    /*
        This program manages two threaded counters: one that counts up and another that counts down.
        It demonstrates basic thread control and synchronization using an object lock.
    */
    static class CounterManager {
        private final int count;
        private final Object lock = new Object();

        public CounterManager(int max) {
            this.count = max;
        }

        public void countUp() {
            for (int i = 1; i <= count; i++) {
                synchronized (lock) {
                    System.out.println("Counting Up: " + i);
                }
            }
        }

        public void countDown() {
            for (int i = count; i >= 0; i--) {
                synchronized (lock) {
                    System.out.println("Counting Down: " + i);
                }
            }
        }
    }

    public static void main(String[] args) {
        final int COUNT = 20;
        CounterManager manager = new CounterManager(COUNT);

        // Thread 1: Count up
        Thread upThread = new Thread(manager::countUp);
        upThread.start();

        try {
            upThread.join(); // Wait for counting up to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Thread 2: Count down
        Thread downThread = new Thread(manager::countDown);
        downThread.start();

        try {
            downThread.join(); // Wait for counting down to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counting complete.");
    }
}
