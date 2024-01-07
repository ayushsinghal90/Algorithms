package org.algorithms.dataStructures.array;

/**
 * Quick Sort is a Divide and Conquer algorithm. It picks an element as a pivot and
 * partitions the given array around the picked pivot.
 * Given an array arr[], its starting position is low (the index of the array) and
 * its ending position is high(the index of the array).
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/quick-sort/1">Question</a>
 * <p>
 * Time Complexity: O(NlogN).
 * <p>
 * Auxiliary Space: O(logN).
 *
 * @author Ayush Singhal
 */
public class QuickSort {
    /**
     * Main function to demonstrate the quickSort method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = { 10, 7, 8, 9, 1, 5 };
        int N = arr.length;

        // Function call
        quickSort.quickSort(arr, 0, N - 1);
        System.out.println("Sorted array:");

        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * Swaps elements at positions i and j in the given array.
     *
     * @param arr: the array in which elements need to be swapped
     * @param i:   the index of the first element to swap
     * @param j:   the index of the second element to swap
     */
    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    /**
     * Partitions the array around a pivot element and returns the index of the pivot.
     *
     * @param arr:  the array to be partitioned
     * @param low:  the starting index of the partition
     * @param high: the ending index of the partition
     * @return The index of the pivot element after partitioning
     */
    public int pivotSort(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        i++;
        swap(arr, i, high);
        return i;
    }

    /**
     * Recursively sorts the array using the QuickSort algorithm.
     *
     * @param arr:  the array to be sorted
     * @param low:  the starting index of the subarray to be sorted
     * @param high: the ending index of the subarray to be sorted
     */
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = pivotSort(arr, low, high);

            // Recursive calls for the left and right partitions
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
