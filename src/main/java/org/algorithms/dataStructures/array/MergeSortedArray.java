package org.algorithms.dataStructures.array;

public class MergeSortedArray {
    public static int[] merge(int[] arr1, int[] arr2) {
        int n1 = arr1.length, i = 0;
        int n2 = arr2.length, j = 0;
        int[] result = new int[n1 + n2];
        int x = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                result[x] = arr1[i];
                i++;
            } else {
                result[x] = arr2[j];
                j++;
            }
            x++;
        }

        while (i < n1) {
            result[x] = arr1[i];
            i++;
            x++;
        }

        while (j < n2) {
            result[x] = arr1[j];
            j++;
            x++;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i : merge(new int[] { 1, 3, 5, 6, 7, 10, 15 }, new int[] { 1, 2, 4, 12, 13 })) {
            System.out.println(i);
        }
    }
}
