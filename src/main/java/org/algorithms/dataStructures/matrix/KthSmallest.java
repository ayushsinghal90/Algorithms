package org.algorithms.dataStructures.matrix;

/**
 * Given an N x N matrix, where every row and column is sorted in non-decreasing order.
 * Find the kth the smallest element in the matrix.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/kth-element-in-matrix/1">Question</a>
 * <p>
 * Time Complexity: O(K*log(N)).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class KthSmallest {

    /**
     * Finds the kth smallest element in the given sorted matrix.
     *
     * @param matrix The sorted matrix.
     * @param n      The size of the matrix (assuming it's a square matrix).
     * @param k      The value of k for finding the kth smallest element.
     * @return The kth smallest element in the matrix.
     */
    public static int kthSmallest(int[][] matrix, int n, int k) {
        // Initialize the low and high bounds for binary search
        int l = matrix[0][0];
        int h = matrix[matrix.length - 1][matrix[0].length - 1];
        // Initialize the variable to store the kth smallest element
        int ans = 0;

        // Perform binary search
        while (l <= h) {
            // Calculate the middle value
            int mid = l + (h - l) / 2;

            // Check the rank of the middle value in the matrix
            if (rank(matrix, mid) < k) {
                // If rank is less than k, update the lower bound
                l = mid + 1;
            } else {
                // If rank is greater than or equal to k, update the answer and upper bound
                ans = mid;
                h = mid - 1;
            }
        }

        // Return the kth smallest element
        return ans;
    }

    /**
     * Calculates the rank of a given value in the matrix.
     *
     * @param matrix The sorted matrix.
     * @param mid    The value for which the rank is calculated.
     * @return The rank of the given value in the matrix.
     */
    private static int rank(int[][] matrix, int mid) {
        // Initialize pointers for matrix traversal
        int i = 0;
        int r = 0;
        int j = matrix[0].length - 1;

        // Traverse the matrix to calculate rank
        while (i < matrix.length && j >= 0) {
            // If the current element is greater than mid, move left in the same row
            if (matrix[i][j] > mid) {
                j--;
            } else {
                // If the current element is less than or equal to mid, add the count of elements
                // in the current column to the rank and move to the next row
                r += j + 1;
                i++;
            }
        }

        // Return the calculated rank
        return r;
    }

    /**
     * A simple main method to test the kthSmallest function.
     *
     * @param args Command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Example usage
        int[][] matrix = {
            { 16, 28, 60, 64 }, { 22, 41, 63, 91 }, { 27, 50, 87, 93 }, { 36, 78, 87, 94 } };
        int n = 4;
        int k = 3;

        int result = kthSmallest(matrix, n, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }
}


