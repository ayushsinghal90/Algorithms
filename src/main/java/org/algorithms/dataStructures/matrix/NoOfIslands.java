package org.algorithms.dataStructures.matrix;

/**
 * Given a grid of size n*m (n is the number of rows and m is the number of
 * columns in the grid) consisting of '0's (Water) and '1's(Land).
 * Find the number of islands.
 * <p>
 * Note: An island is either surrounded by water or boundary of grid and is formed
 * by connecting adjacent lands horizontally or vertically or diagonally i.e., in all 8 directions.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1">Question</a>
 * <p>
 * Time Complexity: O(n*m).
 * <p>
 * Auxiliary Space: O(n*m).
 *
 * @author Ayush Singhal
 */
public class NoOfIslands {
    // Directions for navigating neighbors: down, right, up, left, diagonal directions
    private static final int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 } };

    /**
     * Finds the number of islands in the given 2D grid.
     *
     * @param grid The 2D grid where '1' represents land and '0' represents water.
     * @return The number of islands in the grid.
     */
    public static int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        // Iterate through each cell in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If the cell contains land, perform DFS to mark the connected land cells
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, n, m);
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Performs depth-first search (DFS) to mark connected land cells as visited.
     *
     * @param grid The 2D grid.
     * @param i    The current row index.
     * @param j    The current column index.
     * @param n    The number of rows in the grid.
     * @param m    The number of columns in the grid.
     */
    public static void dfs(char[][] grid, int i, int j, int n, int m) {
        // Base cases for recursion
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') {
            return;
        }

        // Mark the current cell as visited
        grid[i][j] = '0';

        // Explore neighbors in all directions
        for (int g = 0; g < 8; g++) {
            dfs(grid, i + directions[g][0], j + directions[g][1], n, m);
        }
    }

    /**
     * A simple main method to test the numIslands function.
     *
     * @param args Command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Example usage
        char[][] grid = {
            { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } };

        int result = numIslands(grid);
        System.out.println("The number of islands is: " + result);
    }
}
