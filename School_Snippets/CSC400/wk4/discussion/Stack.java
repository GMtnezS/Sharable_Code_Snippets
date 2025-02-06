package wk4.discussion;

import java.util.EmptyStackException;

public class Stack<T> {
    private Node<T> top;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    // Push method (add item to top)
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop method (remove and return top item)
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // Peek method (return top item without removing)
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Get stack size
    public int size() {
        return size;
    }
}
