package wk8.discussion;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A generic Queue ADT implementation using LinkedList.
 * This class provides basic queue operations such as enqueue, dequeue, and peek.
 *
 * @param <T> the type of elements stored in the queue
 */
public class QueueADT<T> {
    private Queue<T> queue;

    /**
     * Constructs an empty queue.
     */
    public QueueADT() {
        this.queue = new LinkedList<>();
    }

    /**
     * Adds an item to the rear of the queue.
     *
     * @param item the item to be added to the queue
     * @throws IllegalArgumentException if item is null
     */
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        queue.add(item);
    }

    /**
     * Removes and returns the item from the front of the queue.
     *
     * @return the item removed from the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T dequeue() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.poll();
    }

    /**
     * Returns the item at the front of the queue without removing it.
     *
     * @return the item at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T peek() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.peek();
    }

    /**
     * Returns the size of the queue.
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Merges another queue into this queue by enqueuing all its elements.
     *
     * @param otherQueue the queue to be merged
     * @throws IllegalArgumentException if the other queue is null
     */
    public void merge(QueueADT<T> otherQueue) {
        if (otherQueue == null) {
            throw new IllegalArgumentException("Other queue cannot be null");
        }
        while (!otherQueue.isEmpty()) {
            this.enqueue(otherQueue.dequeue());
        }
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        return queue.toString();
    }

    /**
     * Main method to test the queue implementation.
     */
    public static void main(String[] args) {
        QueueADT<Integer> queue = new QueueADT<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Queue after enqueuing 1, 2, 3: " + queue);
        System.out.println("Dequeued item: " + queue.dequeue());
        System.out.println("Queue after dequeue: " + queue);
        System.out.println("Peek item: " + queue.peek());
    }
}
