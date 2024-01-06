package org.algorithms.dataStructures.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RectangleSumZero {
    static final int MAX = 100;

    // This function basically finds largest 0
    // sum subarray in temp[0..n-1]. If 0 sum
    // does't exist, then it returns false. Else
    // it returns true and sets starting and
    // ending indexes as starti and endj.
    static boolean sumZero(int[] temp, int[] starti, int[] endj, int n) {

        // Map to store the previous sums
        Map<Integer, Integer> presum = new HashMap<>();
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
            if (presum.containsKey(sum)) {

                // store previous max_length so
                // that we can` check max_length
                // is updated or not
                int old = max_length;

                // If this sum is seen before,
                // then update max_len
                max_length = Math.max(max_length, i - presum.get(sum));

                if (old < max_length) {

                    // If max_length is updated then
                    // enter and update start and end
                    // point of array
                    endj[0] = i;
                    starti[0] = presum.get(sum) + 1;
                }
            } else {

                // Else insert this sum with
                // index in hash table
                presum.put(sum, i);
            }
        }

        // Return true if max_length is non-zero
        return (max_length != 0);
    }

    // The main function that finds Largest rectangle
    // sub-matrix in a[][] whose sum is 0.
    static void sumZeroMatrix(int[][] a, int row, int col) {
        int[] temp = new int[row];

        // Variables to store the final output
        int fup = 0, fdown = 0, fleft = 0, fright = 0;
        int maxl = Integer.MIN_VALUE;

        // Set the left column
        for (int left = 0; left < col; left++) {

            // Initialize all elements of temp as 0
            Arrays.fill(temp, 0);

            // Set the right column for the left column
            // set by outer loop
            for (int right = left; right < col; right++) {

                // Calculate sum between current left
                // and right for every row 'i'
                for (int i = 0; i < row; i++) {
                    temp[i] += a[i][right];
                }

                int[] up = new int[1];
                int[] down = new int[1];

                // Find largest subarray with 0 sum in
                // temp[]. The sumZero() function also
                // sets values of start and finish. So
                // 'sum' is sum of rectangle between (start,
                // left) and (finish, right) which is
                // boundary columns strictly as left and
                // right.
                boolean s = sumZero(temp, up, down, row);

                int ele = (down[0] - up[0] + 1) * (right - left + 1);

                // Compare no. of elements with previous
                // no. of elements in sub-Matrix.
                // If new sub-matrix has more elements
                // then update maxl and final boundaries
                // like fup, fdown, fleft, fright
                if (s && ele > maxl) {
                    fup = up[0];
                    fdown = down[0];
                    fleft = left;
                    fright = right;
                    maxl = ele;
                }
            }
        }

        // If there is no change in boundaries
        // than check if a[0][0] is 0
        // If it not zero then print
        // that no such zero-sum sub-matrix exists
        if (fup == 0 && fdown == 0 && fleft == 0 && fright == 0 && a[0][0] != 0) {
            System.out.println("No zero-sum sub-matrix exists");
            return;
        }

        // Print final values
        for (int j = fup; j <= fdown; j++) {
            for (int i = fleft; i <= fright; i++) {
                System.out.print(a[j][i] + " ");
            }
            System.out.println();
        }
    }

    // Driver program to test above functions
    public static void main(String[] args) {
        int[][] a = { { 9, 7, 16, 5 }, { 1, -6, -7, 3 }, { 1, 8, 7, 9 }, { 7, -2, 0, 10 } };
        int row = 4, col = 4;
        sumZeroMatrix(a, row, col);
    }
}

// This code is contributed by shiv1o43g
