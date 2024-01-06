package org.algorithms.dataStructures.dp;

import java.util.Scanner;

class AlternateMax {
    public static void main(String[] args) {

        // taking input using Scanner class
        Scanner sc = new Scanner(System.in);

        // taking count of testcases
        int t = sc.nextInt();
        while (t-- > 0) {

            // taking count of houses
            int n = sc.nextInt();
            int[] arr = new int[n];

            // inserting money present in
            // each house in the array
            for (int i = 0; i < n; ++i) arr[i] = sc.nextInt();

            // calling method FindMaxSum() of class solve
            System.out.println(FindMaxSum(arr, n));
        }
    }

    // Function to find the maximum money the thief can get.
    public static int FindMaxSum(int[] arr, int n) {
        // Your code here
        // Your code here
        int incl = arr[0], exl = 0, exln;
        for (int i = 1; i < n; i++) {
            exln = Math.max(incl, exl);
            incl = exl + arr[i];
            exl = exln;
        }
        return Math.max(incl, exl);
    }
}
