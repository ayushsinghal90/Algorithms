package org.algorithms.dataStructures.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Time Complexity: O(NLogN).
 * Auxiliary Space: O(N).
 */
class CountToSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();

        while (t-- > 0) {
            long n = sc.nextLong();
            long[] arr = new long[(int) n];

            for (long i = 0; i < n; i++) arr[(int) i] = sc.nextLong();

            System.out.println(inversionCount(arr, n));
        }
    }

    /**
     *
     * @param arr : Input Array
     * @param N : Size of the Array arr[]
     * @return Function to count inversions in the array.
     */
    static long inversionCount(long[] arr, long N) {
        return mergeAndCount(arr, 0, (int) N - 1);
    }

    static long mergeAndCount(long[] arr, int i, int j) {
        long count = 0;
        if (i < j) {
            int mid = (i + j) / 2;

            count += mergeAndCount(arr, i, mid);
            count += mergeAndCount(arr, mid + 1, j);
            count += merge(arr, i, mid, j);
        }
        return count;
    }

    static long merge(long[] arr, int i, int mid, int j) {
        long[] left = Arrays.copyOfRange(arr, i, mid + 1);
        long[] right = Arrays.copyOfRange(arr, mid + 1, j + 1);

        int l = 0, r = 0, k = i;
        long s = 0;

        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                arr[k++] = left[l++];
            } else {
                s += (long) (mid + 1) - ((long) (i + l));
                arr[k++] = right[r++];
            }
        }

        while (l < left.length) {
            arr[k++] = left[l++];
        }
        while (r < right.length) {
            arr[k++] = right[r++];
        }

        return s;
    }
}
