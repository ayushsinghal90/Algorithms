package org.algorithms.dataStructures.dp;

/**
 * Given two array num1 and num2. Find max matches between the elements such that
 * num1[i] = num2[j] such that no two match overlap each other.
 * Ex: {1, 4, 2}, {1, 2, 4}
 * 1 4 2
 * |  \
 * 1 2 4
 * Output = 2
 * Here we can not match num1[2] (element 2) = num2[1] (element 2) since num1[1] (element 4) = num2[2] (element 4)
 * has already been considered and match 2 =2 will create a overlap.
 * <p>
 * Time Complexity: O(N*M).
 * <p>
 * Auxiliary Space: O(N*M).
 *
 * @author Ayush Singhal
 */
public class MaxMatches {
    /**
     * The main function demonstrates the usage of the 'getMatches' method to find
     * the maximum number of matched elements between two arrays.
     *
     * @param args: Command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // Test cases:
        System.out.println(getMatches(new int[] { 1, 2, 2, 3 }, new int[] { 1, 2, 3 }));

        System.out.println(getMatches(new int[] { 1, 4, 2 }, new int[] { 1, 2, 4 }));

        System.out.println(getMatches(new int[] { 1, 1, 1 }, new int[] { 1, 1, 1 }));
    }

    /**
     * Calculates the maximum number of matched elements between two arrays using recursion and memoization.
     *
     * @param num1: First array
     * @param num2: Second array
     * @return Maximum number of matched elements
     */
    public static int getMatches(int[] num1, int[] num2) {
        int n = num1.length, m = num2.length;

        // Dynamic Programming array to store calculated results
        int[][] dp = new int[n][m];

        // Call the recursive function to find the maximum number of matched elements
        return findMaxMatched(0, 0, dp, num1, num2);
    }

    /**
     * Recursive function to find the maximum number of matched elements between two arrays.
     *
     * @param i:   Index in the first array
     * @param j:   Index in the second array
     * @param dp:  Dynamic Programming array for memoization
     * @param num1: First array
     * @param num2: Second array
     * @return Maximum number of matched elements
     */
    public static int findMaxMatched(int i, int j, int[][] dp, int[] num1, int[] num2) {
        // Base case: if indices are out of bounds, return 0
        if (i < 0 || i >= num1.length || j < 0 || j >= num2.length) {
            return 0;
        }

        // If the result is not already memoized, calculate it
        if (dp[i][j] == 0) {
            int cur = num1[i];
            int maxMatch = 0;
            int k = j;

            // Iterate through the second array to find matching elements
            while (k < num2.length) {
                if (cur == num2[k]) {
                    maxMatch = 1 + findMaxMatched(i + 1, k + 1, dp, num1, num2);
                    break;
                }
                k++;
            }

            // Calculate the maximum match by considering or skipping the current element
            maxMatch = Math.max(maxMatch, findMaxMatched(i + 1, j, dp, num1, num2));

            // Memoize the result
            dp[i][j] = maxMatch;
        }

        // Return the memoized result
        return dp[i][j];
    }
}
