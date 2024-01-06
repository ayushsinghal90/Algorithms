package org.algorithms.dataStructures.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * matrix store max profit till j no of transaction for i day.
 *
 * <p>https://www.youtube.com/watch?v=3YILP-PdEJA
 */
class BuySellK {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int K = Integer.parseInt(in.readLine());
            int N = Integer.parseInt(in.readLine());
            String[] input_line = in.readLine().trim().split("\\s+");
            int[] A = new int[N];
            for (int i = 0; i < N; i++) A[i] = Integer.parseInt(input_line[i]);

            System.out.println(maxProfit(K, N, A));
        }
    }

    static int maxProfit(int K, int N, int[] A) {
        // code here
        int[][] dp = new int[K + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j < N; j++) {
                max = Math.max(max, dp[i - 1][j - 1] - A[j - 1]);
                dp[i][j] = Math.max(max + A[j], dp[i][j - 1]);
            }
        }
        return dp[K][N - 1];
    }
}
