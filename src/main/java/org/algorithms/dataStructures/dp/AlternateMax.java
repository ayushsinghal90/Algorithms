package org.algorithms.dataStructures.dp;

/**
 * Given an array Arr of size N containing positive integers.
 * Find the maximum sum of a any possible subsequence such that no
 * two numbers in the subsequence should be adjacent in Arr.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/max-sum-without-adjacents2430/1">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
class AlternateMax {
    /**
     * Main method to findMaxSum functionality.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        int[] arr = { 5, 5, 10, 100, 10, 5 };
        System.out.println(findMaxSum(arr, arr.length));
    }

    /**
     * Finds the maximum sum of a non-adjacent sub-array using dynamic programming.
     *
     * @param arr: The input array.
     * @param n:   The size of the array.
     * @return The maximum sum of a non-adjacent subarray.
     */
    public static int findMaxSum(int[] arr, int n) {
        int incl = arr[0], exl = 0, exln;

        for (int i = 1; i < n; i++) {
            exln = Math.max(incl, exl);
            incl = exl + arr[i];
            exl = exln;
        }

        return Math.max(incl, exl);
    }
}
