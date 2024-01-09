package org.algorithms.dataStructures.slidingWindow;

import java.io.IOException;

/**
 * Given an array arr[] of N non-negative integers representing the height of blocks.
 * If width of each block is 1, compute how much water can be trapped between the blocks during the rainy season.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/trapping-rain-water-1587115621/1">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
class TrappingRainWater {

    public static void main(String[] args) throws IOException {
        int[] arr = { 7, 4, 0, 9 };
        System.out.println(trappingWater(arr, arr.length));
    }

    /**
     * Calculates the total amount of water that can be trapped between the blocks.
     *
     * @param arr: an array representing the height of blocks
     * @param n:   the number of blocks in the array
     * @return The total amount of trapped water
     */
    static long trappingWater(int[] arr, int n) {
        // Initialize variables for the left and right boundaries
        int i = arr[0];
        int j = arr[n - 1];

        // Initialize pointers for the left and right ends of the array
        int l = 1;
        int r = n - 2;

        // Initialize the result variable to store the total trapped water
        long res = 0;

        // Traverse the array to calculate the trapped water
        while (l <= r) {
            // If the left boundary is smaller, process the left block
            if (i < j) {
                // If the current block is taller than the left boundary, update the left boundary
                if (arr[l] > i) {
                    i = arr[l];
                } else {
                    // Add the trapped water to the result
                    res += i - arr[l];
                }
                // Move to the next block on the left
                l++;
            } else {
                // If the right boundary is smaller, process the right block
                // If the current block is taller than the right boundary, update the right boundary
                if (arr[r] > j) {
                    j = arr[r];
                } else {
                    // Add the trapped water to the result
                    res += j - arr[r];
                }
                // Move to the next block on the right
                r--;
            }
        }

        // Return the total amount of trapped water
        return res;
    }

}
