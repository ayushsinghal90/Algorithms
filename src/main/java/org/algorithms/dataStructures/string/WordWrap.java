package org.algorithms.dataStructures.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Please check the question link for details on the problem.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/word-wrap1646/1">Question</a>
 * <p>
 * Time Complexity: O(N^2).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
class WordWrap {
    /**
     * Main function to demonstrate the wordWrap method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] nums = new int[n];
            String[] S = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(S[i]);
            int k = Integer.parseInt(br.readLine().trim());
            System.out.println(solveWordWrap(nums, k));
        }
    }

    /**
     * Solves the Word Wrap problem using dynamic programming to minimize the cost
     * of formatting words in lines with a given maximum line width.
     *
     * @param nums: an array representing the lengths of words
     * @param k:    the maximum line width
     * @return The minimum cost of formatting words in lines
     */
    public static int solveWordWrap(int[] nums, int k) {
        int n = nums.length;
        int cost;

        // Array to store the minimum cost of formatting words from index i to the end
        int[] dp = new int[n];
        dp[n - 1] = 0;

        // Dynamic programming loop to fill the dp array
        for (int i = n - 2; i >= 0; i--) {
            int currentLen = -1;
            dp[i] = Integer.MAX_VALUE;

            // Iterate through words starting from index i
            for (int j = i; j < n; j++) {
                currentLen += nums[j] + 1;

                // If current line length exceeds the maximum line width, break the loop
                if (currentLen > k) break;

                // Calculate the cost of the current line
                if (j == n - 1) {
                    cost = 0; // No cost for the last line
                } else {
                    cost = ((k - currentLen) * (k - currentLen)) + dp[j + 1];
                }

                // Update the minimum cost for formatting words from index i
                if (cost < dp[i]) {
                    dp[i] = cost;
                }
            }
        }

        // Return the minimum cost for formatting words from the beginning
        return dp[0];
    }
}
