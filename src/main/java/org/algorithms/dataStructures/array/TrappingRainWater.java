package org.algorithms.dataStructures.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TrappingRainWater {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); // Inputting the testcases
        while (t-- > 0) {

            // size of array
            int n = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[n];
            String[] inputLine = br.readLine().trim().split(" ");

            // adding elements to the array
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            // calling trappingWater() function
            System.out.println(trappingWater(arr, n));
        }
    }

    // arr: input array
    // n: size of array
    // Function to find the trapped water between the blocks.
    static long trappingWater(int[] arr, int n) {
        // Your code here
        int i = arr[0], j = arr[n - 1];
        int l = 1, r = n - 2;
        long res = 0;

        while (l <= r) {
            if (i < j) {
                if (arr[l] > i) {
                    i = arr[l];
                } else {
                    res += i - arr[l];
                }
                l++;
            } else {
                if (arr[r] > j) {
                    j = arr[r];
                } else {
                    res += j - arr[r];
                }
                r--;
            }
        }
        return res;
    }
}
