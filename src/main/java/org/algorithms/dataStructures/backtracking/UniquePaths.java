package org.algorithms.dataStructures.backtracking;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 * <p>
 * Given the two integers m and n, return the number of possible unique paths that
 * the robot can take to reach the bottom-right corner.
 * <p>
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 * <p>
 * See: <a href="https://leetcode.com/problems/unique-paths/">Question</a>
 * <p>
 * Time Complexity: O(m*n).
 * <p>
 * Auxiliary Space: O(m*n).
 *
 * @author Ayush Singhal
 */
public class UniquePaths {
    // This 2D array is used for memoization to store the number of paths to reach each cell.
    static int[][] mem;

    /**
     * Computes the number of unique paths from the top-left corner to the bottom-right corner
     * of an m x n grid.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths.
     */
    public static int uniquePaths(int m, int n) {
        mem = new int[m][n];
        return paths(0, 0);
    }

    /**
     * Helper method to calculate the number of paths recursively.
     * Uses memoization to improve performance.
     *
     * @param i The current row position.
     * @param j The current column position.
     * @return The number of unique paths from the current cell to the destination.
     */
    public static int paths(int i, int j) {
        // Check for invalid indices or out of bound positions
        if (i < 0 || j < 0 || i >= mem.length || j >= mem[0].length) {
            return 0;
        }

        // If destination is reached, return 1
        if (i == mem.length - 1 && j == mem[0].length - 1) {
            return 1;
        }

        // If this cell's value is already computed, return it
        if (mem[i][j] != 0) {
            return mem[i][j];
        }

        // Compute the sum of paths from the right and down cells
        mem[i][j] += paths(i + 1, j);
        mem[i][j] += paths(i, j + 1);

        return mem[i][j];
    }

    /**
     * Main method to demonstrate the usage of UniquePaths class.
     */
    public static void main(String[] args) {
        int m = 3; // Example number of rows
        int n = 7; // Example number of columns
        int result = uniquePaths(m, n);
        System.out.println("Number of unique paths for a " + m + "x" + n + " grid is: " + result);
    }
}
