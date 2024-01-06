package org.algorithms.dataStructures.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int size = result.size();
            if (size == 0 || result.get(size - 1)[1] < intervals[i][0]) {
                result.add(intervals[i]);
            } else {
                result.get(size - 1)[1] = Math.max(result.get(size - 1)[1], intervals[i][1]);
            }
        }

        return result.toArray(int[][]::new);
    }
}
