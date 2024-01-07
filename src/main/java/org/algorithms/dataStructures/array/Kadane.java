package org.algorithms.dataStructures.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an array Arr[] of N integers. Find the contiguous sub-array(containing at least one number)
 * which has the maximum sum and return its sum.
 * <p>
 * See: <a href="https://practice.geeksforgeeks.org/problems/kadanes-algorithm/0">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
public class Kadane {
    /**
     * Finds and returns the maximum subarray sum using Kadane's algorithm.
     *
     * @param arr: an array of integers.
     * @param n:   the size of the array.
     * @return The maximum sub-array sum.
     */
    public static long maxSubarraySum(int[] arr, int n) {
        // Initialize variables for tracking maximum sum and current sum.
        long max = Integer.MIN_VALUE;
        long sum = Integer.MIN_VALUE;

        // Iterate through the array to find the maximum sub-array sum.
        for (int i = 0; i < n; i++) {
            sum = Math.max(sum + (long) arr[i], arr[i]);
            max = Math.max(sum, max);
        }

        // Return the maximum sub-array sum.
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Inputting the number of test cases
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            // Inputting the size of the array
            int n = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[n];
            String[] inputLine = br.readLine().trim().split(" ");

            // Adding elements to the array
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            // Output the maximum sub-array sum for each test case
            System.out.println(maxSubarraySum(arr, n));
        }
    }
}
