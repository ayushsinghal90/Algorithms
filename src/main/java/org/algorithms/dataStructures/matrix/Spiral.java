package org.algorithms.dataStructures.matrix;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Given a matrix of size r*c. Traverse the matrix in spiral form.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/spirally-traversing-a-matrix-1587115621/1">Question</a>
 * <p>
 * Time Complexity: O(r*c).
 * <p>
 * Auxiliary Space: O(r*c).
 *
 * @author Ayush Singhal
 */
class Spiral {
    /**
     * Driver program to test the above functions.
     */
    public static void main(String[] args) throws IOException {
        int r = 4;
        int c = 4;
        int[][] matrix = {
            { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

        ArrayList<Integer> ans = spirallyTraverse(matrix, r, c);
        for (Integer val : ans) System.out.print(val + " ");
        System.out.println();
    }

    /**
     * Traverses a 2D matrix spirally and returns the elements in a list.
     *
     * @param matrix: 2D matrix to traverse
     * @param r:      Number of rows in the matrix
     * @param c:      Number of columns in the matrix
     * @return ArrayList containing the elements in spiral order
     */
    static ArrayList<Integer> spirallyTraverse(int[][] matrix, int r, int c) {
        // Initialize variables for the current row and column
        int ri = 0, ci = 0;

        // Initialize the result ArrayList to store the elements in spiral order
        ArrayList<Integer> res = new ArrayList<Integer>();

        // Iterate through the matrix in a spiral order
        while (ri < r && ci < c) {
            // Traverse the top row
            for (int i = ci; i < c; i++) {
                res.add(matrix[ri][i]);
            }
            ri++;

            // Traverse the rightmost column
            for (int j = ri; j < r; j++) {
                res.add(matrix[j][c - 1]);
            }
            c--;

            // Traverse the bottom row if there are more rows
            if (ri < r) {
                for (int i = c - 1; i >= ci; i--) {
                    res.add(matrix[r - 1][i]);
                }
                r--;
            }

            // Traverse the leftmost column if there are more columns
            if (ci < c) {
                for (int j = r - 1; j >= ri; j--) {
                    res.add(matrix[j][ci]);
                }
                ci++;
            }
        }

        // Return the result ArrayList containing elements in spiral order
        return res;
    }
}
