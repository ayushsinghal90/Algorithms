package org.algorithms.dataStructures.dp;

/**
 * Given a gold mine called M of (n x m) dimensions. Each field in this mine
 * contains a positive integer which is the amount of gold in tons.
 * Initially the miner can start from any row in the first column.
 * From a given cell, the miner can move:
 * <li> to the cell diagonally up towards the right</li>
 * <li> to the right </li>
 * <li> to the cell diagonally down towards the right</li>
 * <p>
 * Find out maximum amount of gold which he can collect until he can no longer move.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/gold-mine-problem2608/1">Question</a>
 * <p>
 * Time Complexity: O(n * m).
 * <p>
 * Auxiliary Space: O(n * m).
 *
 * @author Ayush Singhal
 */
public class GoldMine {
    /**
     * Main method to test the function maxGold.
     * Initializes a 2D array representing a gold mine and prints the maximum gold collected.
     */
    public static void main(String[] args) {
        // Dimensions of the gold mine
        int n = 3;
        int m = 3;

        // Gold mine represented by a 2D array
        int[][] M = {
            { 1, 3, 3 }, { 2, 1, 4 }, { 0, 6, 4 } };

        // Print the maximum gold collected using the maxGold function
        System.out.println(maxGold(n, m, M));
    }

    /**
     * Calculates the maximum amount of gold that can be collected by a miner,
     * starting from any cell of the first column and moving only right, right-up, or right-down.
     *
     * @param n Number of rows in the gold mine
     * @param m Number of columns in the gold mine
     * @param M 2D array representing the gold mine
     * @return Maximum gold that can be collected
     */
    static int maxGold(int n, int m, int[][] M) {
        // Initialize the answer variable to the minimum integer value
        int ans = Integer.MIN_VALUE;

        // Iterate over each column starting from the second-to-last column
        for (int j = m - 2; j >= 0; j--) {
            // Iterate over each row
            for (int i = 0; i < n; i++) {
                // Calculate the maximum gold that can be collected moving to the right, right-up, or right-down
                int ur = (i == 0) ? 0 : M[i - 1][j + 1];
                int dr = (i == n - 1) ? 0 : M[i + 1][j + 1];
                int r = M[i][j + 1];

                // Update the current cell with the maximum gold that can be collected
                M[i][j] += Math.max(Math.max(ur, dr), r);

                // If the current column is the first column, update the answer variable with the maximum gold
                if (j == 0) {
                    ans = Math.max(ans, M[i][j]);
                }
            }
        }

        // Return the maximum gold that can be collected
        return ans;
    }
}
