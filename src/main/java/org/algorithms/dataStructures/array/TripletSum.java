package org.algorithms.dataStructures.array;

import java.util.Arrays;

/**
 * Given an array arr of size n and an integer X.
 * Find if there's a triplet in the array which sums up to the given integer X.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/triplet-sum-in-array-1587115621/1">Question</a>
 * <p>
 * Time Complexity: O(N*N).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
public class TripletSum {

    /**
     * Checks if there exists a triplet in the array with the given sum.
     *
     * @param A   The input array of integers.
     * @param n   The length of the array.
     * @param X   The target sum to check for.
     * @return True if a triplet with the given sum exists, otherwise false.
     */
    public static boolean tripletSumPresent(int[] A, int n, int X) {
        // Sorting the array for efficient traversal
        Arrays.sort(A);

        // Fix the first element and find the other two using two-pointer approach
        for (int i = 0; i < n - 2; i++) {
            int sum = X - A[i];

            // Pointers for the remaining two elements
            int j = i + 1, k = n - 1;

            // Check for a pair with the given sum
            while (j < k) {
                int temp = A[j] + A[k];
                if (temp == sum) {
                    return true; // Triplet found
                } else if (temp < sum) {
                    j++; // Increment the left pointer for a larger sum
                } else {
                    k--; // Decrement the right pointer for a smaller sum
                }
            }
        }

        return false; // No triplet found
    }

    /**
     * Main method to demonstrate the usage of the TripletSum class.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] A = { 1, 4, 45, 6, 10, 8 };
        boolean ans = tripletSumPresent(A, A.length, 13);
        System.out.println(ans ? 1 : 0);
    }
}
