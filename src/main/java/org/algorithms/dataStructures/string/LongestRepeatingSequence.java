package org.algorithms.dataStructures.string;

import java.io.IOException;

/**
 * Given string str, find the length of the longest repeating subsequence such that it can be found twice in the given string.
 * <p>
 * The two identified subsequences A and B can use the same ith character from string str if and
 * only if that ith character has different indices in A and B.
 * For example, A = "xax" and B = "xax" then the index of first "x" must be different in the original string for A and B.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1">Question</a>
 * <p>
 * Time Complexity: O(N^2).
 * <p>
 * Auxiliary Space: O(N^2).
 *
 * @author Ayush Singhal
 */
class LongestRepeatingSequence {
    /**
     * Main function to demonstrate the medianOfArrays method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException {
        String str = "axxzxy";
        int ans = LongestRepeatingSubsequence(str);
        System.out.println(ans);
    }

    /**
     * Finds the length of the longest repeating subsequence in a given string.
     *
     * @param str the input string
     * @return the length of the longest repeating subsequence
     */
    public static int LongestRepeatingSubsequence(String str) {
        int n = str.length();

        // Create a 2D matrix to store the lengths of longest repeating subsequences
        int[][] mat = new int[n + 1][n + 1];

        // Fill the matrix using dynamic programming
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                // Base case: If either of the indices is 0, set the value to 0
                if (i == 0 || j == 0) {
                    mat[i][j] = 0;
                } else if (i != j && str.charAt(i - 1) == str.charAt(j - 1)) {
                    // If characters at current indices are equal and indices are different, extend the subsequence
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                } else {
                    // If characters are not equal, take the maximum length from the previous cells
                    mat[i][j] = Math.max(mat[i - 1][j - 1], Math.max(mat[i - 1][j], mat[i][j - 1]));
                }
            }
        }

        // The length of the longest repeating subsequence is stored in the bottom-right cell of the matrix
        return mat[n][n];
    }
}
