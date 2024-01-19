package org.algorithms.dataStructures.dp;

/**
 * Given an array of n positive integers. Find the sum of the maximum sum subsequence
 * of the given array such that the integers in the subsequence are sorted in strictly increasing order
 * i.e. a strictly increasing subsequence.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1">Question</a>
 * <p>
 * Time Complexity: O(N*N).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class MaxSumIncSeq {

    /**
     * The main function initializes an array, calculates the maximum sum of increasing subsequence,
     * and prints the result.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] Arr = { 1, 101, 2, 3, 100 };
        System.out.println(maxSumIS(Arr, Arr.length));
    }

    /**
     * Calculates the maximum sum of an increasing subsequence in the given array.
     *
     * @param arr The input array.
     * @param n   The length of the array.
     * @return The maximum sum of increasing subsequence.
     */
    public static int maxSumIS(int[] arr, int n) {
        int[] dp = new int[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}
