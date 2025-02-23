// package wk6.cta;

import LinkedList.LinkedList;
import LinkedList.LinkedListNode;
// import wk6.cta.LinkedList.LinkedList;
// import wk6.cta.LinkedList.LinkedListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom linked list that extends LinkedList and includes additional functionalities.
 */
class CustomLinkedList extends LinkedList implements Iterable<Integer> {

    /**
     * Deletes the first occurrence of the given value in the linked list.
     * @param data Value to delete.
     */
    public void delete(int data) {
        if (head == null) return;

        if (head.data == data) {
            head = head.next;
            return;
        }

        LinkedListNode current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    /**
     * Returns an iterator to traverse the linked list.
     * @return an instance of LinkedListIterator.
     */
    @Override
    public Iterator<Integer> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Inner class to implement an iterator for the linked list.
     */
    private class LinkedListIterator implements Iterator<Integer> {
        private LinkedListNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int data = current.data;
            current = current.next;
            return data;
        }
    }
}
