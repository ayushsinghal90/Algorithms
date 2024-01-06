package org.algorithms.dataStructures.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** https://www.youtube.com/watch?v=yCQN096CwWM */
class MaxRectangle {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int N, M, x, y;
            String[] S = read.readLine().split(" ");
            N = Integer.parseInt(S[0]);
            M = Integer.parseInt(S[1]);
            int[][] a = new int[N][M];
            for (int i = 0; i < N; i++) {
                String[] s = read.readLine().split(" ");
                for (int j = 0; j < M; j++) a[i][j] = Integer.parseInt(s[j]);
            }
            System.out.println(maximumSumRectangle(N, M, a));
        }
    }

    static int maximumSumRectangle(int R, int C, int[][] M) {
        int[] dp;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < C; i++) {
            dp = new int[R];
            for (int j = i; j < C; j++) {
                for (int r = 0; r < R; r++) {
                    dp[r] += M[r][j];
                }

                res = Math.max(res, getMaxInArr(dp));
            }
        }
        return res;
    }

    static int getMaxInArr(int[] arr) {
        int max = Integer.MIN_VALUE, sum = -1001;

        for (int i : arr) {
            sum = Math.max(sum + i, i);
            max = Math.max(sum, max);
        }
        return max;
    }
}
