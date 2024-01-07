package org.algorithms.dataStructures.backtracking;

import java.util.Arrays;

/**
 * Given a path in the form of a rectangular matrix having few landmines arbitrarily placed (marked as 0),
 * calculate length of the shortest safe route possible from any cell in the first column to any cell in
 * the last column of the matrix. We have to avoid landmines and their four adjacent cells
 * (left, right, above and below) as they are also unsafe.
 * We are allowed to move to only adjacent cells which are not landmines. i.e.
 * the route cannot contains any diagonal moves.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/find-shortest-safe-route-in-a-path-with-landmines/">Question</a>
 * <p>
 * Time Complexity: O(R * C).
 * <p>
 * Auxiliary Space: O(R * C).
 *
 * @author Ayush Singhal
 */
class MineSweeper {

    static final int R = 12;
    static final int C = 10;

    static int[] rowNum = { -1, 0, 0, 1 };
    static int[] colNum = { 0, -1, 1, 0 };

    static int min_dist;

    /**
     * Checks if the cell at coordinates (x, y) is safe to visit.
     *
     * @param mat:     the minefield matrix
     * @param visited: a matrix to keep track of visited cells
     * @param x:       the row index
     * @param y:       the column index
     * @return true if the cell is safe, false otherwise
     */
    static boolean isSafe(int[][] mat, boolean[][] visited, int x, int y) {
        return mat[x][y] != 0 && !visited[x][y];
    }

    /**
     * Checks if the coordinates (x, y) are valid indices in the minefield matrix.
     *
     * @param x: the row index
     * @param y: the column index
     * @return true if the coordinates are valid, false otherwise
     */
    static boolean isValid(int x, int y) {
        return x < R && y < C && x >= 0 && y >= 0;
    }

    /**
     * Marks adjacent cells of landmines as unsafe (0).
     *
     * @param mat: the minefield matrix
     */
    static void markUnsafeCells(int[][] mat) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j] == 0) {
                    for (int k = 0; k < 4; k++)
                        if (isValid(i + rowNum[k], j + colNum[k])) mat[i + rowNum[k]][j + colNum[k]] = -1;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j] == -1) mat[i][j] = 0;
            }
        }
    }

    /**
     * Utility function to find the shortest path recursively.
     *
     * @param mat:     the minefield matrix
     * @param visited: a matrix to keep track of visited cells
     * @param i:       the current row index
     * @param j:       the current column index
     * @param dist:    the current distance
     */
    static void findShortestPathUtil(int[][] mat, boolean[][] visited, int i, int j, int dist) {
        // If destination (last column) is reached
        if (j == C - 1) {
            // Update the minimum distance found so far
            min_dist = Math.min(dist, min_dist);
            return;
        }

        // If the current path cost exceeds the minimum distance so far, prune the path
        if (dist > min_dist) return;

        // Mark the current cell as visited
        visited[i][j] = true;

        // Recurse for all safe adjacent neighbors
        for (int k = 0; k < 4; k++) {
            int newRow = i + rowNum[k];
            int newCol = j + colNum[k];

            // Check if the neighbor cell is valid and safe
            if (isValid(newRow, newCol) && isSafe(mat, visited, newRow, newCol)) {
                findShortestPathUtil(mat, visited, newRow, newCol, dist + 1);
            }
        }

        // Backtrack: Mark the current cell as not visited
        visited[i][j] = false;
    }

    /**
     * Finds the shortest safe route in the MineSweeper grid from the leftmost column to the rightmost column.
     *
     * @param mat The MineSweeper grid with 1 representing safe cells and 0 representing mines.
     */
    static void findShortestPath(int[][] mat) {
        // Initialize the minimum distance to the maximum possible value
        min_dist = Integer.MAX_VALUE;

        // Create a boolean matrix to store information about cells already visited in the current route
        boolean[][] visited = new boolean[R][C];

        // Mark adjacent cells of landmines as unsafe
        markUnsafeCells(mat);

        // Start from the first column and explore all possible paths
        for (int i = 0; i < R; i++) {
            // If the path is safe from the current cell in the first column
            if (mat[i][0] == 1) {
                // Initialize the visited matrix to false
                for (int k = 0; k < R; k++) {
                    Arrays.fill(visited[k], false);
                }

                // Find the shortest path from the current cell in the first column
                findShortestPathUtil(mat, visited, i, 0, 0);

                // If the minimum distance is already found, exit the loop
                if (min_dist == C - 1) break;
            }
        }

        // If the destination can be reached, print the length of the shortest safe route
        if (min_dist != Integer.MAX_VALUE) {
            System.out.println("Length of the shortest safe route is " + min_dist);
        } else {
            System.out.println("Destination not reachable from the given source");
        }
    }

    /**
     * Main method to test the MineSweeper class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int[][] mat = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1}};
        findShortestPath(mat);
    }
}
