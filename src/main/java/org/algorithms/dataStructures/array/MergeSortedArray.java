package org.algorithms.dataStructures.array;

/**
 * Given two sorted arrays, the task is to merge them in a sorted manner.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/merge-two-sorted-arrays/">Question</a>
 * <p>
 * Time Complexity: O(n+m).
 * <p>
 * Auxiliary Space: O(n+m).
 *
 * @author Ayush Singhal
 */
public class MergeSortedArray {

    /**
     * Merges two sorted arrays into a single sorted array.
     *
     * @param arr1: the first sorted array
     * @param arr2: the second sorted array
     * @return A new sorted array containing all elements from both input arrays
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        int n1 = arr1.length, i = 0;
        int n2 = arr2.length, j = 0;
        int[] result = new int[n1 + n2];
        int x = 0;

        // Merge elements from arr1 and arr2 in sorted order
        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                result[x] = arr1[i];
                i++;
            } else {
                result[x] = arr2[j];
                j++;
            }
            x++;
        }

        // Copy remaining elements from arr1, if any
        while (i < n1) {
            result[x] = arr1[i];
            i++;
            x++;
        }

        // Copy remaining elements from arr2, if any
        while (j < n2) {
            result[x] = arr2[j];
            j++;
            x++;
        }

        return result;
    }

    /**
     * Main function to demonstrate the merge sort.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        for (int i : merge(new int[] { 1, 3, 5, 6, 7, 10, 15 }, new int[] { 1, 2, 4, 12, 13 })) {
            System.out.println(i);
        }
    }
}
