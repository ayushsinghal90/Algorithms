package org.algorithms.dataStructures.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class WordWrap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] nums = new int[n];
            String[] S = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(S[i]);
            int k = Integer.parseInt(br.readLine().trim());
            System.out.println(solveWordWrap(nums, k));
        }
    }

    public static int solveWordWrap(int[] nums, int k) {
        // Code here
        int n = nums.length, cost;
        int[] dp = new int[n];
        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            int currentLen = -1;
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                currentLen += nums[j] + 1;

                if (currentLen > k) break;

                if (j == n - 1) {
                    cost = 0;
                } else {
                    cost = ((k - currentLen) * (k - currentLen)) + dp[j + 1];
                }

                if (cost < dp[i]) {
                    dp[i] = cost;
                }
            }
        }
        return dp[0];
    }
}
