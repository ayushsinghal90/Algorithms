package org.algorithms.dataStructures.array;

/**
 * Please check the question link for information on the problem.
 * <p>
 * See: <a href="https://leetcode.com/problems/next-permutation/">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
public class NextPermutation {
    /**
     * Main function to demonstrate the nextPermutation method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 6, 7, 10, 15 };
        nextPermutation(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }

    /**
     * Modifies the array to represent the next lexicographically greater permutation.
     *
     * @param nums: an array of integers
     */
    public static void nextPermutation(int[] nums) {
        // Find the first element from the right that is smaller than its next element
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
            i--;
        }

        // If a smaller element is found, swap it with the smallest element to its right that is greater
        if (i >= 0) {
            int j = nums.length - 1;
            // Find the smallest element to the right that is greater than nums[i]
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }

            // Swap nums[i] with the smallest greater element to its right
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }

        // Reverse the sub-array to the right of the swapped element
        int k = nums.length - 1;
        i = i + 1;

        // Reverse the sub-array to ensure it becomes the smallest possible permutation
        while (i < k) {
            int temp = nums[k];
            nums[k] = nums[i];
            nums[i] = temp;
            i++;
            k--;
        }
    }
}
