package org.algorithms.dataStructures.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Given an array of integers. Find the Inversion Count in the array.
 * <p>
 * Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted.
 * If the array is already sorted then the inversion count is 0.
 * If an array is sorted in the reverse order then the inversion count is the maximum.
 * Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1">Question</a>
 * <p>
 * Time Complexity: O(NLogN).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
class CountInversions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();

        while (t-- > 0) {
            long n = sc.nextLong();
            long[] arr = new long[(int) n];

            for (long i = 0; i < n; i++) arr[(int) i] = sc.nextLong();

            System.out.println(inversionCount(arr, n));
        }
    }

    /**
     * Function to count inversions in the array.
     *
     * @param arr : Input Array
     * @param N : Size of the Array arr[]
     * @return Inversion count.
     */
    static long inversionCount(long[] arr, long N) {
        return mergeAndCount(arr, 0, (int) N - 1);
    }

    /**
     * Recursively sorts the sub-array arr[i...j] and counts the number of inversions
     * using the merge sort algorithm.
     *
     * @param arr: the array to be sorted and counted for inversions.
     * @param i: Starting index of sub-array.
     * @param j: Ending index of sub-array.
     * @return Number of inversions in the merged sub-array.
     */
    static long mergeAndCount(long[] arr, int i, int j) {
        long count = 0;

        // Check if the sub-array has more than one element.
        if (i < j) {
            // Calculate the middle index of the sub-array.
            int mid = (i + j) / 2;

            // Recursively sort and count inversions in the left and right halves.
            count += mergeAndCount(arr, i, mid);
            count += mergeAndCount(arr, mid + 1, j);

            // Merge the sorted halves and count inversions in the merged sub-array.
            count += merge(arr, i, mid, j);
        }

        // Return the total count of inversions in the sub-array.
        return count;
    }

    /**
     * Merges two sub-arrays of the given array arr[].
     * The first sub-array is arr[i..mid], and the second sub-array is arr[mid+1..j].
     * Counts and returns the number of inversions in the merged sub-arrays.
     *
     * @param arr: the array to be merged.
     * @param i: Start of the array.
     * @param mid: Mid of the array.
     * @param j: End of the array.
     * @return Number of inversions in the merged sub-array.
     */
    static long merge(long[] arr, int i, int mid, int j) {
        // Create copies of the left and right sub-arrays.
        long[] left = Arrays.copyOfRange(arr, i, mid + 1);
        long[] right = Arrays.copyOfRange(arr, mid + 1, j + 1);

        // Initialize pointers and a variable to count inversions.
        int l = 0, r = 0, k = i;
        long s = 0;

        // Merge the left and right sub-arrays while counting inversions.
        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                // If the element in the left sub-array is smaller or equal, no inversion.
                arr[k++] = left[l++];
            } else {
                // If the element in the right sub-array is smaller, count inversions.
                s += (long) (mid + 1) - ((long) (i + l));
                arr[k++] = right[r++];
            }
        }

        // Copy any remaining elements from the left and right sub-arrays.
        while (l < left.length) {
            arr[k++] = left[l++];
        }
        while (r < right.length) {
            arr[k++] = right[r++];
        }

        // Return the count of inversions in the merged sub-arrays.
        return s;
    }
}
