package org.algorithms.dataStructures.array;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Given an array arr[] and an integer K where K is smaller than size of array,
 * the task is to find the Kth smallest element in the given array.
 * It is given that all array elements are distinct.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1">Question</a>
 * <p>
 * Time Complexity: O(nlogN). In worst can be O(n^2)
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class KthSmallest {

    /**
     * Main function to read input and demonstrate the kthSmallest method.
     *
     * @param args: command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

            int k = sc.nextInt();
            out.println(kthSmallest(arr, 0, n - 1, k));
        }
        out.flush();
    }

    /**
     * Swaps elements at two indices in the array.
     *
     * @param arr: The array in which elements are to be swapped.
     * @param i:   The index of the first element.
     * @param j:   The index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    /**
     * Partitions the array and returns the index of the pivot element.
     *
     * @param arr:  the array to be partitioned.
     * @param low:  the starting index of the partition.
     * @param high: the ending index of the partition.
     * @return The index of the pivot element after partitioning.
     */
    public static int pivotSort(int[] arr, int low, int high) {
        // Choose the pivot element (in this case, the element at the end).
        int pivot = arr[high];

        // Initialize the index of the smaller element.
        int i = low - 1;

        // Traverse the array and rearrange elements such that elements less than the pivot
        // are moved to the left side and elements greater than the pivot are moved to the right side.
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                // Increment the index of the smaller element.
                i++;
                // Swap arr[i] and arr[j].
                swap(arr, i, j);
            }
        }

        // Swap the pivot element with the element at the index next to the smaller elements.
        i++;
        swap(arr, i, high);

        // Return the index of the pivot element after partitioning.
        return i;
    }

    /**
     * Finds and returns the  kth smallest element in the array.
     *
     * @param arr: the array in which to find the kth smallest element.
     * @param l:   the starting index of the array.
     * @param r:   the ending index of the array.
     * @param k:   the kth position to find.
     * @return The kth smallest element in the array.
     */
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        // Check if k is within a valid range.
        if (k > 0 && k <= r - l + 1) {
            // Find the pivot index after partitioning.
            int pivot = pivotSort(arr, l, r);

            // If the pivot is at the desired position, return the element at the pivot.
            if (pivot - l == k - 1) {
                return arr[pivot];
            }

            // If the pivot is on the right side, recursively search the left sub-array.
            if (pivot - l > k - 1) {
                return kthSmallest(arr, l, pivot - 1, k);
            }

            // If the pivot is on the left side, recursively search the right sub-array.
            return kthSmallest(arr, pivot + 1, r, k - pivot + l - 1);
        }

        // Return -1 if k is out of range.
        return -1;
    }
}
