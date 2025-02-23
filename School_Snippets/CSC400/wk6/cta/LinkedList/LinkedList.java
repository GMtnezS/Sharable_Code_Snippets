// package wk6.cta.LinkedList; 

package LinkedList;

/**
 * A simple singly linked list implementation.
 */
public class LinkedList {
    public LinkedListNode head;

    /**
     * Inserts a new node at the end of the linked list.
     * @param data Value to insert.
     */
    public void insert(Integer data) { // Change int to Integer
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null into the list");
        }

        if (head == null) {
            head = new LinkedListNode(data);
            return;
        }

        LinkedListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new LinkedListNode(data);
    }

    /**
     * Prints the linked list.
     */
    public void printList() {
        LinkedListNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /**
     * Gets the head node of the linked list.
     * @return The head node.
     */
    public LinkedListNode getHead() {
        return head;
    }

    /**
     * Sets the head node of the linked list.
     * @param head The new head node.
     */
    public void setHead(LinkedListNode head) {
        this.head = head;
    }
}
