package wk6.discussion;

import java.util.Arrays;

/**
 * Implementation of QuickSort algorithm in Java.
 * This class provides a method to sort an array using QuickSort.
 */
public class QuickSort {

    /**
     * Sorts an array using the QuickSort algorithm.
     *
     * @param arr  The array to be sorted.
     * @param low  The starting index.
     * @param high The ending index.
     * @throws IllegalArgumentException if the array is null.
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        if (low < high) {
            // Find pivot position
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the subarrays
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the array into two halves based on the pivot.
     *
     * @param arr  The array to be partitioned.
     * @param low  The starting index.
     * @param high The ending index.
     * @return The index of the pivot after partitioning.
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Choosing the last element as pivot
        int i = low - 1;  // Pointer for smaller element

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // Place pivot in the correct position
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Swaps two elements in an array.
     *
     * @param arr The array where elements need to be swapped.
     * @param i   The first index.
     * @param j   The second index.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Main method to test QuickSort with a sample list of numbers.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] numbers = {34, 7, 23, 32, 5, 62, 32, 74, 2};

        System.out.println("Before sorting: " + Arrays.toString(numbers));

        quickSort(numbers, 0, numbers.length - 1);

        System.out.println("After sorting: " + Arrays.toString(numbers));
    }
}
