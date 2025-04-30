package classes;

import java.util.LinkedList;
import java.util.Comparator;

/**
 * A utility class for sorting a list of Student objects using the selection sort algorithm.
 */
public class SelectionSorter {
    /**
     * Sorts the specified list of students using the selection sort algorithm and the provided comparator.
     *
     * @param list      the list of students to sort
     * @param comparator the comparator to determine the order of the students
     * @throws IllegalArgumentException if the list or comparator is null
     */
    public static void selectionSort(LinkedList<Student> list, Comparator<Student> comparator) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            Student temp = list.get(i);
            list.set(i, list.get(minIdx));
            list.set(minIdx, temp);
        }
    }
}