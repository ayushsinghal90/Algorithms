package org.algorithms.dataStructures.array;

import java.io.IOException;

/**
 * Given an array of N integers arr[] where each element represents the maximum length
 * of the jump that can be made forward from that element. This means if arr[i] = x,
 * then we can jump any distance y such that y ≤ x.
 * Find the minimum number of jumps to reach the end of the array (starting from the first element).
 * If an element is 0, then you cannot move through that element.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/minimum-number-of-jumps-1587115620/1">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
class MinJumps {

    /**
     * Main function to demonstrate the medianOfArrays method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException {
        System.out.println(minJumps(new int[] { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 }));
    }

    /**
     * Calculates the minimum number of jumps required to reach the end of the array.
     *
     * @param arr: an array of integers representing the maximum number of steps
     *            that can be taken from each position in the array
     * @return The minimum number of jumps, or -1 if it is not possible to reach the end
     */
    static int minJumps(int[] arr) {
        // If the array has only one element, no jumps are needed
        if (arr.length == 1) {
            return 0;
        }

        // If the first element is 0, it's not possible to make any jumps
        if (arr[0] == 0) {
            return -1;
        }

        // Initialize variables
        int s = arr[0];  // Remaining steps from the current position
        int i = 1;       // Current position
        int j = 1;       // Number of jumps made so far
        int k = s;       // Maximum reachable position in the current range

        // Traverse the array to calculate the minimum number of jumps
        while (i < j) {
            // If the end of the array is reached, return the number of jumps
            if (i == arr.length - 1) {
                return j;
            }

            // Update the maximum reachable position in the current range
            k = Math.max(k, i + arr[i]);

            // Decrement the remaining steps
            s--;

            // If no more steps are left in the current range
            if (s == 0) {
                // If the maximum reachable position is less than or equal to the current position,
                // it is not possible to make any further jumps
                if (k <= i) {
                    return -1;
                }

                // Update the remaining steps and increment the number of jumps
                s = k - i;
                j++;
            }
            i++;
        }

        // If it's not possible to reach the end of the array
        return -1;
    }
}
