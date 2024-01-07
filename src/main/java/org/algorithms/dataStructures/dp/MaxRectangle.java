package org.algorithms.dataStructures.dp;

import java.io.IOException;

/**
 * Given a 2D matrix M of dimensions RxC. Find the maximum sum submatrix in it.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/maximum-sum-rectangle2948/1">Question</a>
 * <p>
 * Time Complexity: O(R*R*C).
 * <p>
 * Auxiliary Space: O(R*C)).
 *
 * @author Ayush Singhal
 */
class MaxRectangle {
    /**
     * Main function to demonstrate the maximumSumRectangle method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException {
        int N = 4;
        int M = 5;
        int[][] a = {
            { 1, 2, -1, -4, -20 }, { -8, -3, 4, 2, 1 }, { 3, 8, 10, 1, 3 }, { -4, -1, 1, 7, -6 } };
        System.out.println(maximumSumRectangle(N, M, a));
    }

    /**
     * Calculates the maximum sum rectangle in a 2D matrix.
     *
     * @param R: The number of rows in the matrix.
     * @param C: The number of columns in the matrix.
     * @param M: The 2D matrix.
     * @return The maximum sum rectangle.
     */
    static int maximumSumRectangle(int R, int C, int[][] M) {
        int[] dp = new int[R];
        int res = Integer.MIN_VALUE;

        // Iterate over all possible columns
        for (int i = 0; i < C; i++) {
            dp = new int[R];

            // Iterate over all possible sub-columns
            for (int j = i; j < C; j++) {
                int max = Integer.MIN_VALUE, sum = -1001;

                // Update the dp array with the cumulative sum of elements in the current sub-column
                for (int r = 0; r < R; r++) {
                    dp[r] += M[r][j];

                    sum = Math.max(sum + dp[r], dp[r]);
                    max = Math.max(sum, max);
                }

                // Update the result with the maximum sum in the current dp array
                res = Math.max(res, max);
            }
        }
        return res;
    }
}
