package org.algorithms.dataStructures.array;

/** https://leetcode.com/problems/next-permutation/ */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;

            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }

            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }

        int k = nums.length - 1;
        i = i + 1;

        while (i < k) {
            int temp = nums[k];
            nums[k] = nums[i];
            nums[i] = temp;
            i++;
            k--;
        }
    }
}
