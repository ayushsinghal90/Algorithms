package org.algorithms.dataStructures.array;

import java.io.IOException;

/**
 * Given an array arr of n positive integers and a number k.
 * One can apply a swap operation on the array any number of times,
 * i.e choose any two index i and j (i < j) and swap arr[i] , arr[j].
 * Find the minimum number of swaps required to bring all the numbers less than or equal to k together,
 * i.e. make them a contiguous subarray.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/minimum-swaps-required-to-bring-all-elements-less-than-or-equal-to-k-together4847/1">Question</a>
 * <p>
 * Time Complexity: O(N)
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
class KSmallestTogether {

    public static void main(String[] args) throws IOException {
        int[] arr = { 2, 7, 9, 5, 8, 7, 4 };
        int K = 6;
        int ans = minSwap(arr, arr.length, K);
        System.out.println(ans);
    }

    /**
     * Counts the minimum number of swaps required to bring all elements less than or equal to k
     * together in the array.
     *
     * @param arr: the array of integers
     * @param n:   the size of the array
     * @param k:   the target value
     * @return the minimum number of swaps
     */
    public static int minSwap(int[] arr, int n, int k) {
        // Count the number of elements less than or equal to k
        int count = 0;
        for (int i : arr) {
            if (i <= k) {
                count++;
            }
        }

        // If there are no elements less than or equal to k, no swaps are needed
        if (count == 0) {
            return 0;
        }

        // Count the number of elements greater than k in the initial count elements
        int nCount = 0;
        for (int i = 0; i < count; i++) {
            if (arr[i] > k) {
                nCount++;
            }
        }

        // Initialize pointers and the result
        int j = count, res = nCount, i = 0;

        // Slide the window through the array and update the count of elements greater than k
        while (j < n) {
            if (arr[i] > k) {
                nCount--;
            }

            if (arr[j] > k) {
                nCount++;
            }

            // Update the minimum number of swaps required
            res = Math.min(res, nCount);

            // Move the window
            i++;
            j++;
        }

        // Return the minimum number of swaps required
        return res;
    }
}
