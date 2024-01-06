package org.algorithms.dataStructures.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** https://www.youtube.com/watch?v=JbRsM2X2_pg */
class BooleanParentheses {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int N = Integer.parseInt(in.readLine());
            String S = in.readLine();

            System.out.println(countWays(N, S));
        }
    }

    static int countWays(int N, String str) {
        // code here
        int o = 0, p = 0;
        char[] sumb = new char[N / 2 + 1];
        char[] opr = new char[N / 2];

        for (int i = 0; i < N; i++) {
            char cur = str.charAt(i);
            if (cur == 'T' || cur == 'F') {
                sumb[o] = cur;
                o++;
            } else {
                opr[p] = cur;
                p++;
            }
        }

        int l = sumb.length;
        int[][] dpfc = new int[l][l];
        int[][] dptc = new int[l][l];

        for (int g = 0; g < l; g++) {
            for (int i = 0, j = g; j < l; j++, i++) {
                if (g == 0) {
                    dpfc[i][j] = sumb[i] == 'F' ? 1 : 0;
                    dptc[i][j] = sumb[i] == 'T' ? 1 : 0;
                } else {
                    for (int k = i; k < j; k++) {
                        char op = opr[k];

                        int lfc = dpfc[i][k];
                        int rfc = dpfc[k + 1][j];
                        int ltc = dptc[i][k];
                        int rtc = dptc[k + 1][j];

                        if (op == '|') {
                            dptc[i][j] += ((lfc * rtc) + (ltc * rfc) + (ltc * rtc)) % 1003;
                            dpfc[i][j] += (rfc * lfc) % 1003;
                        } else if (op == '&') {
                            dptc[i][j] += (ltc * rtc) % 1003;
                            dpfc[i][j] += ((lfc * rtc) + (ltc * rfc) + (rfc * lfc)) % 1003;
                        } else {
                            dptc[i][j] += ((lfc * rtc) + (ltc * rfc)) % 1003;
                            dpfc[i][j] += ((ltc * rtc) + (rfc * lfc)) % 1003;
                        }
                    }
                }
            }
        }
        return dptc[0][l - 1] % 1003;
    }
}
