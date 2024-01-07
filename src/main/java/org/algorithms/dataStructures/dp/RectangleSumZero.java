package org.algorithms.dataStructures.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix mat[][] of size N x M. The task is to find the largest rectangular sub-matrix by area whose sum is 0.
 * If there are multiple solutions return the rectangle which starts from minimum column index.
 * If you still have multiple solutions return the one having the least width
 * (number of columns included in the sub-matrix) starting from the minimum row index.
 * If no such matrix is present return a zero (0) size matrix.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/largest-rectangular-sub-matrix-whose-sum-is-0/1">Question</a>
 * <p>
 * Time Complexity: O(N*M*M).
 * <p>
 * Auxiliary Space: O(N*M).
 *
 * @author Ayush Singhal
 */
public class RectangleSumZero {
    /**
     * Checks if there is a sub-matrix with a zero-sum in the given matrix.
     *
     * @param temp:   The temporary array for storing cumulative sum.
     * @param starti: The start index of the sub-matrix.
     * @param endj:   The end index of the sub-matrix.
     * @param n:      The size of the array.
     * @return True if a zero-sum sub-matrix exists, otherwise false.
     */
    static boolean sumZero(int[] temp, int[] starti, int[] endj, int n) {
        // Map to store the previous sums
        Map<Integer, Integer> previousSum = new HashMap<>();
        int sum = 0;

        // Initialize length of sub-array with sum 0
        int max_length = 0;

        // Traverse through the given array
        for (int i = 0; i < n; i++) {
            sum += temp[i];

            if (temp[i] == 0 && max_length == 0) {
                starti[0] = i;
                endj[0] = i;
                max_length = 1;
            }

            if (sum == 0) {
                if (max_length < i + 1) {
                    starti[0] = 0;
                    endj[0] = i;
                }
                max_length = i + 1;
            }

            // Look for this sum in Hash table
            if (previousSum.containsKey(sum)) {

                // store previous max_length so
                // that we can check max_length
                // is updated or not
                int old = max_length;

                // Update max_len if this sum is seen before,
                max_length = Math.max(max_length, i - previousSum.get(sum));

                if (old < max_length) {
                    // If max_length is updated then
                    // update enter and update start and end of array
                    endj[0] = i;
                    starti[0] = previousSum.get(sum) + 1;
                }
            } else {

                // Else insert this sum with
                // index in hash table
                previousSum.put(sum, i);
            }
        }

        // Return true if max_length is non-zero
        return (max_length != 0);
    }

    /**
     * Finds and prints the zero-sum sub-matrix with the maximum area.
     *
     * @param a:   The input matrix.
     * @param row: The number of rows in the matrix.
     * @param col: The number of columns in the matrix.
     */
    static void sumZeroMatrix(int[][] a, int row, int col) {
        int[] temp = new int[row];

        int fup = 0, fdown = 0, fleft = 0, fright = 0;
        int maxl = Integer.MIN_VALUE;

        for (int left = 0; left < col; left++) {
            Arrays.fill(temp, 0);

            for (int right = left; right < col; right++) {
                for (int i = 0; i < row; i++) {
                    temp[i] += a[i][right];
                }

                int[] up = new int[1];
                int[] down = new int[1];
                boolean s = sumZero(temp, up, down, row);
                int ele = (down[0] - up[0] + 1) * (right - left + 1);

                if (s && ele > maxl) {
                    fup = up[0];
                    fdown = down[0];
                    fleft = left;
                    fright = right;
                    maxl = ele;
                }
            }
        }

        if (fup == 0 && fdown == 0 && fleft == 0 && fright == 0 && a[0][0] != 0) {
            System.out.println("No zero-sum sub-matrix exists");
            return;
        }

        for (int j = fup; j <= fdown; j++) {
            for (int i = fleft; i <= fright; i++) {
                System.out.print(a[j][i] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Driver program to test the above functions.
     */
    public static void main(String[] args) {
        int[][] a = { { 9, 7, 16, 5 }, { 1, -6, -7, 3 }, { 1, 8, 7, 9 }, { 7, -2, 0, 10 } };
        int row = 4, col = 4;
        sumZeroMatrix(a, row, col);
    }
}
