package org.algorithms.dataStructures.dp;

import java.io.IOException;

/**
 * In the stock market, a person buys a stock and sells it on some future date.
 * Given the stock prices of N days in an array A[ ] and a positive integer K,
 * find out the maximum profit a person can make in at-most K transactions.
 * A transaction is equivalent to (buying + selling) of a stock and new
 * transaction can start only when the previous transaction has been completed.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/maximum-profit4657/1">Question</a>
 * <p>
 * Time Complexity: O(N^K). N days and K transactions.
 * <p>
 * Auxiliary Space: O(N^K).
 *
 * @author Ayush Singhal
 */
class BuySellK {
    /**
     * Main function to demonstrate the maxProfit method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException {
        int[] A = { 10, 22, 5, 75, 65, 80 };
        System.out.println(maxProfit(2, A.length, A));
    }

    /**
     * Calculates the maximum profit for buying and selling stocks with a maximum of K transactions.
     *
     * @param K: The maximum number of transactions allowed.
     * @param N: The number of days (size of the array A).
     * @param A: The array containing stock prices for each day.
     * @return The maximum profit.
     */
    static int maxProfit(int K, int N, int[] A) {
        // Initializing the dynamic programming table
        int[][] dp = new int[K + 1][N + 1];

        // Looping through transactions
        for (int i = 1; i <= K; i++) {
            int max = Integer.MIN_VALUE;
            // Looping through days
            for (int j = 1; j < N; j++) {
                max = Math.max(max, dp[i - 1][j - 1] - A[j - 1]);
                dp[i][j] = Math.max(max + A[j], dp[i][j - 1]);
            }
        }
        // Returning the maximum profit after K transactions on N days
        return dp[K][N - 1];
    }
}
