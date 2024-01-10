package org.algorithms.dataStructures.dp;

import java.util.Arrays;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i],
 * obtaining a profit of profit[i].
 * <p>
 * You're given the startTime, endTime and profit arrays,
 * return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 * <p>
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 * <p>
 * See: <a href="https://leetcode.com/problems/maximum-profit-in-job-scheduling/">Question</a>
 * <p>
 * Time Complexity: O(N*logN).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class MaxProfitJobScheduling {
    public static void main(String[] args) {
        // Example job data
        int[] startTime = { 1, 2, 3, 3 };
        int[] endTime = { 3, 4, 5, 6 };
        int[] profit = { 50, 10, 40, 70 };

        // Calculate the maximum profit for job scheduling
        int maxProfit = jobScheduling(startTime, endTime, profit);

        // Display the result
        System.out.println("Maximum Profit: " + maxProfit);
    }

    /**
     * Retrieves the index of the next job in the sorted array.
     *
     * @param jobs The array of jobs.
     * @param job  The current job.
     * @param from The starting index for the search.
     * @return The index of the next job in the sorted array.
     */
    private static int getNextJobIndex(Job[] jobs, Job job, int from) {
        Job key = new Job(job.endTime, 0, 0);
        int index = Arrays.binarySearch(jobs, from, jobs.length, key);
        while (index > 0 && jobs[index - 1].startTime == key.startTime) index--; // leftmost match
        if (index < 0) index = Math.abs(index) - 1; // no match - start from the insertion point
        return index;
    }

    /**
     * Recursively calculates the maximum profit for job scheduling.
     *
     * @param jobs  The array of jobs.
     * @param start The starting index of jobs.
     * @param cache Memoization cache to store calculated results.
     * @return The maximum profit achievable.
     */
    private static int jobScheduling(Job[] jobs, int start, int[] cache) {
        if (start >= jobs.length) return 0;
        if (cache[start] != Integer.MIN_VALUE) return cache[start];
        Job currentJob = jobs[start];
        int profitWithoutJob = jobScheduling(jobs, start + 1, cache);
        int nextJobIndex = getNextJobIndex(jobs, currentJob, start + 1);
        int profitWithJob = currentJob.profit + jobScheduling(jobs, nextJobIndex, cache);
        int ans = Math.max(profitWithoutJob, profitWithJob);
        return (cache[start] = ans);
    }

    /**
     * Calculates the maximum profit for job scheduling.
     *
     * @param startTime Array representing job start times.
     * @param endTime   Array representing job end times.
     * @param profit    Array representing job profits.
     * @return The maximum profit achievable.
     */
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < jobs.length; i++)
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        Arrays.sort(jobs);
        int[] cache = new int[jobs.length];
        Arrays.fill(cache, Integer.MIN_VALUE);
        return jobScheduling(jobs, 0, cache);
    }

    /**
     * Represents a job with start time, end time, and profit.
     */
    static class Job implements Comparable<Job> {
        int startTime;
        int endTime;
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        /**
         * Compares jobs based on their end times.
         *
         * @param otherJob The other job to compare.
         * @return Negative, zero, or positive integer if this job is less than, equal to, or greater than the other job.
         */
        @Override
        public int compareTo(Job otherJob) {
            return Integer.compare(this.endTime, otherJob.endTime);
        }
    }
}
