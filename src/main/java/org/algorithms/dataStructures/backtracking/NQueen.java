package org.algorithms.dataStructures.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class NQueen {
    static ArrayList<ArrayList<Integer>> ans;
    static boolean[] cols;
    static boolean[] leftDiagonal;
    static boolean[] rightDiagonal;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());

            ArrayList<ArrayList<Integer>> ans = nQueen(n);
            if (ans.size() == 0) System.out.println("-1");
            else {
                for (int i = 0; i < ans.size(); i++) {
                    System.out.print("[");
                    for (int j = 0; j < ans.get(i).size(); j++) System.out.print(ans.get(i).get(j) + " ");
                    System.out.print("] ");
                }
                System.out.println();
            }
        }
    }

    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        // code here
        ans = new ArrayList<>();
        cols = new boolean[n];
        leftDiagonal = new boolean[2 * n];
        rightDiagonal = new boolean[2 * n];

        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) temp.add(0);
        helper(temp, 0, n);

        return ans;
    }

    static void helper(ArrayList<Integer> list, int i, int n) {
        if (i == n) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int j = 0; j < n; j++) {
            if (cols[j] || leftDiagonal[i + j] || rightDiagonal[i - j + n]) {
                continue;
            }

            cols[j] = leftDiagonal[i + j] = rightDiagonal[i - j + n] = true;
            list.set(i, j + 1);
            helper(list, i + 1, n);
            cols[j] = leftDiagonal[i + j] = rightDiagonal[i - j + n] = false;
        }
    }
}
