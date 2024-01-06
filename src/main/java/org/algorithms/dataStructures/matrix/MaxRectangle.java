package org.algorithms.dataStructures.matrix;

import java.util.Scanner;
import java.util.Stack;

class MaxRectangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] arr = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            System.out.println(maxArea(arr, n, m));
            t--;
        }
    }

    public static int maxArea(int[][] M, int m, int n) {
        // add code here.
        int res = max(m, n, M[0]);

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    M[i][j] += M[i - 1][j];
                }
            }
            res = Math.max(res, max(m, n, M[i]));
        }

        return res;
    }

    public static int max(int m, int n, int[] a) {
        Stack<Integer> mem = new Stack<>();

        int max_area = 0, area = 0, top_value;
        int i = 0;

        while (i < n) {
            if (mem.empty() || a[mem.peek()] <= a[i]) {
                mem.push(i++);
            } else {
                top_value = mem.peek();
                mem.pop();

                area = a[top_value] * i;

                if (!mem.empty()) {
                    area = a[top_value] * (i - mem.peek() - 1);
                }
                max_area = Math.max(area, max_area);
            }
        }

        while (!mem.empty()) {
            top_value = mem.peek();
            mem.pop();

            area = a[top_value] * i;

            if (!mem.isEmpty()) {
                area = a[top_value] * (i - mem.peek() - 1);
            }
            max_area = Math.max(area, max_area);
        }

        return max_area;
    }
}
