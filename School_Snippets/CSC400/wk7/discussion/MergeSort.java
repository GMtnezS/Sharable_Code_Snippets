package wk7.discussion;

import java.util.Arrays;

/**
 * Implements Merge Sort for sorting linked lists.
 */
class MergeSort {

    /**
     * Sorts a linked list using Merge Sort.
     * @param head The head node of the linked list.
     * @return The new head node of the sorted list.
     */
    public static LinkedListNode mergeSort(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        LinkedListNode middle = getMiddle(head);
        LinkedListNode nextOfMiddle = middle.next;

        // Break the list into two halves
        middle.next = null; 

        // Recursively sort both halves
        LinkedListNode left = mergeSort(head);
        LinkedListNode right = mergeSort(nextOfMiddle);

        // Merge the sorted halves
        return merge(left, right);
    }

    /**
     * Merges two sorted linked lists into one sorted list.
     * @param left  The first sorted list.
     * @param right The second sorted list.
     * @return The merged sorted list.
     */
    private static LinkedListNode merge(LinkedListNode left, LinkedListNode right) {
        if (left == null) return right;
        if (right == null) return left;

        if (left.data < right.data) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    /**
     * Finds the middle node of a linked list using the slow-fast pointer approach.
     * @param head The head of the linked list.
     * @return The middle node.
     */
    private static LinkedListNode getMiddle(LinkedListNode head) {
        if (head == null) return null;

        LinkedListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        // Create a linked list
        LinkedList list = new LinkedList();
        list.insert(34);
        list.insert(7);
        list.insert(23);
        list.insert(32);
        list.insert(5);
        list.insert(62);
        list.insert(32);
        list.insert(74);
        list.insert(2);

        // Print the original list
        System.out.println("Before sorting:");
        list.printList();

        LinkedListNode sortedList = MergeSort.mergeSort(list.getHead());

        // Sort the linked list using Merge Sort
        list.setHead(sortedList);

        // Print the sorted list
        System.out.println("After sorting:");
        list.printList();
    }
}
