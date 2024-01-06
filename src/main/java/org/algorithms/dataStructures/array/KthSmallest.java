package org.algorithms.dataStructures.array;

import java.io.PrintWriter;
import java.util.Scanner;

class KthSmallest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

            int k = sc.nextInt();
            out.println(kthSmallest(arr, 0, n - 1, k));
        }
        out.flush();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static int pivotSort(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        i++;
        swap(arr, i, high);
        return i;
    }

    public static int kthSmallest(int[] arr, int l, int r, int k) {
        // Your code here
        if (k > 0 && k <= r - l + 1) {
            int pivot = pivotSort(arr, l, r);

            if (pivot - l == k - 1) {
                return arr[pivot];
            }

            if (pivot - l > k - 1) {
                return kthSmallest(arr, l, pivot - 1, k);
            }
            return kthSmallest(arr, pivot + 1, r, k - pivot + l - 1);
        }
        return -1;
    }
}
