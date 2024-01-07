package org.algorithms.dataStructures.matrix;

/**
 * Given a boolean 2D array of n x m dimensions where each row is sorted.
 * Find the 0-based index of the first row that has the maximum number of 1's.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/row-with-max-1s0023/1">Question</a>
 * <p>
 * Time Complexity: O(n+m).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
class RowWithMax1s {
    /**
     * Driver program to test the above functions.
     */
    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] arr = {
            { 0, 1, 1, 1 }, { 0, 0, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } };
        System.out.println(rowWithMax1s(arr, n, m));
    }

    /**
     * Finds the row in a binary matrix with the maximum number of 1s.
     *
     * @param arr: Binary matrix
     * @param n:   Number of rows in the matrix
     * @param m:   Number of columns in the matrix
     * @return Row index with the maximum number of 1s, -1 if no 1 is found
     */
    static int rowWithMax1s(int[][] arr, int n, int m) {
        // Initialize variables to track the current column and identified row
        int col = m - 1, row = -1;

        // Find the first occurrence of 1 in the first row
        for (int i = 0; i < m; i++) {
            if (arr[0][i] == 1) {
                col = i;
                row = 0;
                break;
            }
        }

        // Traverse the matrix to find the row with the maximum number of 1s
        for (int i = 1; i < n; i++) {
            // Update the identified row if the current element is 1
            if (row == -1 && arr[i][col] == 1) {
                row = i;
            }

            // Move left in the matrix to find the leftmost 1 in the current row
            while (col > 0 && arr[i][col - 1] == 1) {
                row = i;
                col--;
            }
        }

        // Return the index of the row with the maximum number of 1s, -1 if no 1 is found
        return row;
    }
}
