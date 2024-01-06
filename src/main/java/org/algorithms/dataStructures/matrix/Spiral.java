package org.algorithms.dataStructures.matrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Spiral {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int r = sc.nextInt();
            int c = sc.nextInt();

            int[][] matrix = new int[r][c];

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) matrix[i][j] = sc.nextInt();
            }
            ArrayList<Integer> ans = spirallyTraverse(matrix, r, c);
            for (Integer val : ans) System.out.print(val + " ");
            System.out.println();
        }
    }

    // Function to return a list of integers denoting spiral traversal of matrix.
    static ArrayList<Integer> spirallyTraverse(int[][] matrix, int r, int c) {
        // code here
        int ri = 0, ci = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        while (ri < r && ci < c) {
            for (int i = ci; i < c; i++) {
                res.add(matrix[ri][i]);
            }
            ri++;

            for (int j = ri; j < r; j++) {
                res.add(matrix[j][c - 1]);
            }
            c--;

            if (ri < r) {
                for (int i = c - 1; i >= ci; i--) {
                    res.add(matrix[r - 1][i]);
                }
                r--;
            }

            if (ci < c) {

                for (int j = r - 1; j >= ri; j--) {
                    res.add(matrix[j][ci]);
                }
                ci++;
            }
        }
        return res;
    }
}
