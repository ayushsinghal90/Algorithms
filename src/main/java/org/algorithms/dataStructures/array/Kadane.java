package org.algorithms.dataStructures.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Kadane {
    public static long maxSubarraySum(int[] arr, int n) {

        // Your code here
        long max = Integer.MIN_VALUE;
        long sum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            sum = Math.max(sum + (long) arr[i], arr[i]);
            max = Math.max(sum, max);
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); // Inputting the testcases
        while (t-- > 0) {
            // size of array
            int n = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[n];
            String[] inputLine = br.readLine().trim().split(" ");

            // adding elements
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            System.out.println(maxSubarraySum(arr, n));
        }
    }
}
