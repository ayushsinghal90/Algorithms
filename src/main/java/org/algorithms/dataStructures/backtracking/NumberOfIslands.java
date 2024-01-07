package org.algorithms.dataStructures.backtracking;

/**
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * See: <a href="https://leetcode.com/problems/number-of-islands/">Question</a>
 * <p>
 * Time Complexity: O(n * m).
 * <p>
 * Auxiliary Space: O(n * m).
 *
 * @author Ayush Singhal
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        // Example 2D grid representing islands and water
        char[][] grid = {
            { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '1' }, { '0', '0', '0', '1', '1' }, { '0', '0', '0', '1', '0' } };

        // Calculate the number of islands using the numIslands method
        int result = numIslands(grid);

        // Print the result
        System.out.println("Number of islands: " + result);
    }

    /**
     * Counts the number of islands in a 2D grid.
     *
     * @param grid: 2D array representing the grid with '1' indicating land and '0' indicating water
     * @return Number of islands in the grid
     */
    public static int numIslands(char[][] grid) {
        // Get the number of rows and columns in the grid
        int n = grid.length;
        int m = grid[0].length;

        // Initialize the count of islands
        int count = 0;

        // Iterate through each cell in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If the cell represents land ('1'), increment the count and visit the entire island
                if (grid[i][j] == '1') {
                    count++;
                    visitIsland(grid, n, m, i, j);
                }
            }
        }

        // Return the total count of islands
        return count;
    }

    /**
     * Recursively visits an entire island, marking all connected land cells as visited.
     *
     * @param grid: 2D array representing the grid
     * @param n:    Number of rows in the grid
     * @param m:    Number of columns in the grid
     * @param i:    Current row index
     * @param j:    Current column index
     */
    public static void visitIsland(char[][] grid, int n, int m, int i, int j) {
        // Base case: check if the current cell is out of bounds or represents water ('0')
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') {
            return;
        }

        // Mark the current cell as visited by changing it to water ('0')
        grid[i][j] = '0';

        // Recursively visit the adjacent cells in all four directions
        visitIsland(grid, n, m, i + 1, j);
        visitIsland(grid, n, m, i - 1, j);
        visitIsland(grid, n, m, i, j + 1);
        visitIsland(grid, n, m, i, j - 1);
    }
}
