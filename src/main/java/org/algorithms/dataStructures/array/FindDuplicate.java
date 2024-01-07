package org.algorithms.dataStructures.array;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * <p>
 * See: <a href="https://leetcode.com/problems/find-the-duplicate-number/">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
public class FindDuplicate {

    /**
     * Main function to demonstrate the usage of the findDuplicate method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        FindDuplicate finder = new FindDuplicate();
        int[] nums = { 1, 3, 4, 2, 2 }; // Example array with a duplicate
        int duplicate = finder.findDuplicate(nums);
        System.out.println("Duplicate number in the array: " + duplicate);
    }

    /**
     * Finds and returns a duplicate number in the given array using Floyd's Tortoise and Hare algorithm.
     *
     * @param nums: Array containing integers from 1 to n where n is the size of the array
     * @return Duplicate number in the array.
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: Detect the intersection point of the two pointers
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Move one pointer to the beginning and find the entrance to the cycle
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        // Return the duplicate number found at the entrance to the cycle
        return fast;
    }
}

