package org.algorithms.dataStructures.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [start, end], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * See: <a href="https://leetcode.com/problems/merge-intervals/">Question</a>
 * <p>
 * Time Complexity: O(nlogN).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class MergeIntervals {
    /**
     * Main function to demonstrate the merge interval.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] mergedIntervals = merge(intervals);

        System.out.print("{");
        for(int[] interval: mergedIntervals) {
            System.out.print("{" + interval[0] + "," + interval[1] + "}");
        }
        System.out.print("}");
    }

    /**
     * Merges overlapping intervals in a given 2D array of intervals.
     *
     * @param intervals a 2D array where each row represents an interval [start, end]
     * @return a new 2D array containing the merged intervals
     */
    public static int[][] merge(int[][] intervals) {
        // Sort the intervals based on the start values
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // List to store the merged intervals
        List<int[]> result = new ArrayList<>();

        // Iterate through the sorted intervals and merge overlapping ones
        for (int i = 0; i < intervals.length; i++) {
            int size = result.size();

            // If the result list is empty or the current interval does not overlap with the last one
            if (size == 0 || result.get(size - 1)[1] < intervals[i][0]) {
                // Add the current interval to the result list
                result.add(intervals[i]);
            } else {
                // Merge the current interval with the last one in the result list
                result.get(size - 1)[1] = Math.max(result.get(size - 1)[1], intervals[i][1]);
            }
        }

        // Convert the List to a 2D array and return the merged intervals
        return result.toArray(new int[0][0]);
    }
}
