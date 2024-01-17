package org.algorithms.dataStructures.array;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown
 * pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ...,
 * nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be
 * rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * See: <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">Question</a>
 * <p>
 * Time Complexity: O(log(N)).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
public class SearchRotated {
    /**
     * Searches for a target value in a rotated sorted array.
     *
     * @param nums   The rotated sorted array.
     * @param target The target value to search for.
     * @return The index of the target value if found, otherwise -1.
     */
    public static int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1;

        while (i <= j) {
            int mid = i + ((j - i) / 2);

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[i]) {
                if (nums[mid] > target && nums[i] <= target) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[j] >= target) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * A main function to test the search method with example inputs.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] rotatedSortedArray = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 0;

        int result = search(rotatedSortedArray, target);

        if (result != -1) {
            System.out.println("Target " + target + " found at index " + result);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }
    }
}
