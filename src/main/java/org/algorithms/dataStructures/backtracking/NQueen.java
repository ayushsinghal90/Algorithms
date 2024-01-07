package org.algorithms.dataStructures.backtracking;

import java.util.ArrayList;

/**
 * The {@code NQueen} class provides a solution to the N-Queens problem.
 * Given an integer {@code n}, the task is to place N queens on an NxN chessboard
 * in such a way that no two queens threaten each other.
 *
 * <p>This class contains a method {@link #nQueen(int)} which returns a list of
 * all possible solutions to the N-Queens problem.
 *
 * <p>The implementation uses backtracking to explore all possible configurations
 * and checks for valid placements based on the constraints of the chessboard.
 *
 * <p>The solutions are represented as a list of lists, where each list corresponds
 * to a valid placement of queens. Each integer in the list represents the column
 * position of the queen in the corresponding row.
 *
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/find-shortest-safe-route-in-a-path-with-landmines/">Question</a>
 * <p>
 * Time Complexity: O(N!).
 * <p>
 * Auxiliary Space: O(N^2).
 *
 * @author Ayush Singhal
 */
public class NQueen {
    static ArrayList<ArrayList<Integer>> ans;
    static boolean[] cols;
    static boolean[] leftDiagonal;
    static boolean[] rightDiagonal;

    /**
     * Finds all possible solutions to the N-Queens problem for a given board size.
     *
     * @param n: The size of the chessboard and the number of queens to be placed.
     * @return A list of all possible solutions, where each solution is represented as a list of integers.
     */
    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        ans = new ArrayList<>();
        cols = new boolean[n];
        leftDiagonal = new boolean[2 * n];
        rightDiagonal = new boolean[2 * n];

        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) temp.add(0);
        helper(temp, 0, n);

        return ans;
    }

    /**
     * Helper method for backtracking and finding solutions to the N-Queens problem.
     *
     * @param list: The current state of the board represented as a list of integers.
     * @param i: The current row being considered.
     * @param n: The size of the chessboard.
     */
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

    /**
     * The main function demonstrates the usage of the NQueen class by finding and printing
     * all solutions for a given board size.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        int n = 4; // Change the board size as needed
        ArrayList<ArrayList<Integer>> solutions = nQueen(n);

        System.out.println("No of solution: " + solutions.size());
        // Print the solutions
        for (ArrayList<Integer> solution : solutions) {
            System.out.println(solution);
        }
    }
}
