package org.algorithms.dataStructures.dp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence
 * of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j],
 * where i < j, the condition j - i <= k is satisfied.
 * <p>
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array,
 * leaving the remaining elements in their original order.
 * <p>
 * See: <a href="https://leetcode.com/problems/constrained-subsequence-sum/">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class ConstrainedSubsequenceSum {

    /**
     * Finds the maximum sum of a non-empty subsequence of the array such that
     * the distance between any two consecutive elements of the subsequence
     * is at most k.
     *
     * @param nums The input array.
     * @param k The maximum allowed distance between consecutive elements of the subsequence.
     * @return The maximum sum of the constrained subsequence.
     */
    public static int constrainedSubsetSum(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] dp = new int[nums.length];

        // Loop through each element in the array
        for (int i = 0; i < nums.length; i++) {
            // Remove the front of the queue if it's outside the current window
            if (!queue.isEmpty() && i - queue.peek() > k) {
                queue.poll();
            }

            // Calculate the maximum sum up to the current index
            dp[i] = Math.max((!queue.isEmpty() ? dp[queue.peek()] : 0) + nums[i], nums[i]);

            // Remove elements from the end of the queue if they are smaller than the current sum
            while (!queue.isEmpty() && dp[queue.peekLast()] < dp[i]) {
                queue.pollLast();
            }

            // Only add the index to the queue if the sum is greater than 0
            if (dp[i] > 0) {
                queue.offer(i);
            }
        }

        // Find the maximum sum in dp array
        int ans = Integer.MIN_VALUE;
        for (int num : dp) {
            ans = Math.max(ans, num);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10,2,-10,5,20}; // Example array
        int k = 2; // Example constraint value
        int result = constrainedSubsetSum(nums, k);
        System.out.println("The maximum sum is: " + result);
    }
}
