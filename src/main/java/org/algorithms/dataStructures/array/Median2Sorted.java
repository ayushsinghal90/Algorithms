package org.algorithms.dataStructures.array;

import java.util.Scanner;

class Median2Sorted {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = sc.nextInt();

            int m = sc.nextInt();
            int[] b = new int[m];
            for (int i = 0; i < m; i++) b[i] = sc.nextInt();

            double res = medianOfArrays(n, m, a, b);

            if (res == (int) res) System.out.println((int) res);
            else System.out.println(res);
        }
    }

    static double medianOfArrays(int n, int m, int[] a, int[] b) {
        // Your Code Here
        return Median(a, b);
    }

    static double Median(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        if (n > m) return Median(B, A); // Swapping to make A smaller

        int start = 0;
        int end = n;
        int realMidInMergedArray = (n + m + 1) / 2;

        while (start <= end) {
            int mid = (start + end) / 2;
            int leftAsize = mid;
            int leftBsize = realMidInMergedArray - mid;
            int leftA = (leftAsize > 0) ? A[leftAsize - 1] : Integer.MIN_VALUE; // checking overflow of indices
            int leftB = (leftBsize > 0) ? B[leftBsize - 1] : Integer.MIN_VALUE;
            int rightA = (leftAsize < n) ? A[leftAsize] : Integer.MAX_VALUE;
            int rightB = (leftBsize < m) ? B[leftBsize] : Integer.MAX_VALUE;

            // if correct partition is done
            if (leftA <= rightB && leftB <= rightA) {
                if ((m + n) % 2 == 0) return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                return Math.max(leftA, leftB);
            } else if (leftA > rightB) {
                end = mid - 1;
            } else start = mid + 1;
        }
        return 0.0;
    }
}
