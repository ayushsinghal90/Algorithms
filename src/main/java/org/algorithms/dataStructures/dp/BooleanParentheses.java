package org.algorithms.dataStructures.dp;

import java.io.IOException;

/**
 * Given a boolean expression S of length N with following symbols.
 * Symbols
 *     'T' ---> true
 *     'F' ---> false
 * and following operators filled between symbols
 * Operators
 *     &   ---> boolean AND
 *     |   ---> boolean OR
 *     ^   ---> boolean XOR
 * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
 *<p>
 * Note: The answer can be large, so return it with modulo 1003
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/boolean-parenthesization5610/1">Question</a>
 * <p>
 * Time Complexity: O(N^3).
 * <p>
 * Auxiliary Space: O(N^2).
 *
 * @author Ayush Singhal
 */
class BooleanParentheses {
    /**
     * Main function to demonstrate the countWays method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException {
        String S = "T|T&F^T";
        System.out.println(countWays(S.length(), S));
    }

    /**
     * A method to count the number of ways to parenthesize a boolean expression
     * such that it evaluates to true.
     *
     * @param N:   The length of the input string.
     * @param str: The boolean expression as a string.
     * @return The number of ways to parenthesize the expression to get true.
     */
    static int countWays(int N, String str) {
        // Initialize variables to count operands and operators
        int o = 0, p = 0;

        // Arrays to store operands and operators separately
        char[] sumb = new char[N / 2 + 1];
        char[] opr = new char[N / 2];

        // Separate operands and operators from the input string
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

        // Length of operands array
        int l = sumb.length;

        // 2D arrays to store the count of true and false values
        int[][] dpfc = new int[l][l];
        int[][] dptc = new int[l][l];

        // Loop to fill the 2D arrays
        for (int g = 0; g < l; g++) {
            for (int i = 0, j = g; j < l; j++, i++) {
                if (g == 0) {
                    // Base case: Single character operands
                    dpfc[i][j] = sumb[i] == 'F' ? 1 : 0;
                    dptc[i][j] = sumb[i] == 'T' ? 1 : 0;
                } else {
                    // Iterating over operators and operands to calculate counts
                    for (int k = i; k < j; k++) {
                        char op = opr[k];

                        int lfc = dpfc[i][k];
                        int rfc = dpfc[k + 1][j];
                        int ltc = dptc[i][k];
                        int rtc = dptc[k + 1][j];

                        if (op == '|') {
                            // OR operator
                            dptc[i][j] += ((lfc * rtc) + (ltc * rfc) + (ltc * rtc)) % 1003;
                            dpfc[i][j] += (rfc * lfc) % 1003;
                        } else if (op == '&') {
                            // AND operator
                            dptc[i][j] += (ltc * rtc) % 1003;
                            dpfc[i][j] += ((lfc * rtc) + (ltc * rfc) + (rfc * lfc)) % 1003;
                        } else {
                            // XOR operator
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
