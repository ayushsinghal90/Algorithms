package org.algorithms.dataStructures.dp;

import java.io.IOException;

/**
 * In daily share trading, a buyer buys shares in the morning and sells them on the same day.
 * If the trader is allowed to make at most 2 transactions in a day,
 * the second transaction can only start after the first one is complete (Buy->sell->Buy->sell).
 * The stock prices throughout the day are represented in the form of an array of prices.
 * Question:
 * Given an array price of size N, find out the maximum profit that a share trader could have made.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/buy-and-sell-a-share-at-most-twice/1">Question</a>
 * <p>
 * Time Complexity: O(N). N days.
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
class BuySellTwice {
    /**
     * Main function to demonstrate the maxProfit method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException {
        int[] price = { 2, 30, 15, 10, 8, 25, 80 };
        System.out.println(maxProfit(price.length, price));
    }

    /**
     * Calculates the maximum profit that can be obtained by buying and selling stocks.
     *
     * @param n:     The number of days (size of the price array).
     * @param price: The array containing stock prices for each day.
     * @return The maximum profit.
     */
    public static int maxProfit(int n, int[] price) {
        // Initializing the profit array
        int[] profit = new int[n];
        for (int i = 0; i < n; i++) {
            profit[i] = 0;
        }

        // Calculating the maximum price from the last day
        int maxPrice = price[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (price[i] > maxPrice) {
                maxPrice = price[i];
            }
            profit[i] = Math.max(profit[i + 1], maxPrice - price[i]);
        }

        // Calculating the minimum price from the first day and updating the profit
        int minPrice = price[0];
        for (int i = 1; i < n; i++) {
            if (price[i] < minPrice) {
                minPrice = price[i];
            }
            profit[i] = Math.max(profit[i - 1], profit[i] + (price[i] - minPrice));
        }

        // Returning the maximum profit
        return profit[n - 1];
    }
}
