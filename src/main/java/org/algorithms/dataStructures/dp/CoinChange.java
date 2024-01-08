package org.algorithms.dataStructures.dp;

/**
 * Given an integer array coins[ ] of size N representing different denominations of currency and an integer sum,
 * find the number of ways you can make sum by using different combinations from coins[ ].
 * <p>
 * Note: Assume that you have an infinite supply of each type of coin.
 * And you can use any coin as many times as you want.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/coin-change2448/1">Question</a>
 * <p>
 * Time Complexity: O(S*N). N days.
 * <p>
 * Auxiliary Space: O(S).
 *
 * @author Ayush Singhal
 */
public class CoinChange {
    /**
     * The main function demonstrates the usage of the 'count' method to find the
     * number of ways to make a sum using a given set of coins.
     *
     * @param args: Command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // Example usage: Find the number of ways to make a sum of 10 using coins {2, 5, 3, 6}
        int sum = 10;
        int[] coins = { 2, 5, 3, 6 };
        System.out.println(count(coins, coins.length, sum));
    }

    /**
     * Calculates the number of ways to make a given sum using a set of coins.
     *
     * @param coins: Array representing the available coins
     * @param N:     Number of coins available
     * @param sum:   Target sum to be achieved
     * @return Number of ways to make the sum using the given coins
     */
    public static long count(int[] coins, int N, int sum) {
        // Dynamic Programming array to store the count of ways for each sum
        long[] dp = new long[sum + 1];
        dp[0] = 1;  // There is one way to make a sum of 0 (using no coins)

        // Iterate through each coin and update the count of ways for each possible sum
        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= sum; j++) {
                if (coins[i] <= j) {
                    // Update the count by adding ways to make the current sum without and with the current coin
                    dp[j] += dp[j - coins[i]];
                }
            }
        }

        // The final value in dp array represents the total number of ways to make the target sum
        return dp[sum];
    }
}
