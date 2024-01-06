package org.algorithms.dataStructures.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class LongestRepeatingSeq {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String str = br.readLine().trim();
            int ans = LongestRepeatingSubsequence(str);
            System.out.println(ans);
        }
    }

    public static int LongestRepeatingSubsequence(String str) {
        // code here
        int n = str.length();
        int[][] mat = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    mat[i][j] = 0;
                } else if (i != j && str.charAt(i - 1) == str.charAt(j - 1)) {
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                } else {
                    mat[i][j] = Math.max(mat[i - 1][j - 1], Math.max(mat[i - 1][j], mat[i][j - 1]));
                }
            }
        }
        return mat[n][n];
    }
}
