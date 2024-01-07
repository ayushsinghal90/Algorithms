package org.algorithms.dataStructures.array;

/**
 * Given two sorted arrays array1 and array2 of size m and n respectively.
 * Find the median of the two sorted arrays.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/median-of-2-sorted-arrays-of-different-sizes/1">Question</a>
 * <p>
 * Time Complexity: O(nlogN).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
public class MedianTwoSorted {

    /**
     * Main function to demonstrate the medianOfArrays method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        int[] a = { 1, 5, 9 };
        int[] b = { 2, 3, 6, 7 };
        double res = medianOfArrays(a.length, b.length, a, b);
        if (res == (int) res) System.out.println((int) res);
        else System.out.println(res);
    }

    /**
     * Calculates the median of two sorted arrays.
     *
     * @param n: the size of the first array
     * @param m: the size of the second array
     * @param a: the first sorted array
     * @param b: the second sorted array
     * @return The median of the merged arrays
     */
    static double medianOfArrays(int n, int m, int[] a, int[] b) {
        return Median(a, b);
    }

    /**
     * Helper method to calculate the median of two sorted arrays using binary search.
     *
     * @param A: the first sorted array
     * @param B: the second sorted array
     * @return The median of the merged arrays
     */
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

            // Check if the correct partition is done
            if (leftA <= rightB && leftB <= rightA) {
                // Calculate and return the median based on the partitions
                if ((m + n) % 2 == 0) return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                return Math.max(leftA, leftB);
            } else if (leftA > rightB) {
                // Adjust the partition to the left side
                end = mid - 1;
            } else {
                // Adjust the partition to the right side
                start = mid + 1;
            }
        }
        // Return 0.0 if the arrays are not properly sorted
        return 0.0;
    }
}
