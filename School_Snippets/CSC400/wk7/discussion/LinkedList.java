package wk7.discussion;

/**
 * A simple singly linked list implementation.
 */
class LinkedList {
    private LinkedListNode head;

    /**
     * Inserts a new node at the end of the linked list.
     * @param data Value to insert.
     */
    public void insert(int data) {
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
