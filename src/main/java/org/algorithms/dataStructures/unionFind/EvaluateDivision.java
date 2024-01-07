package org.algorithms.dataStructures.unionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.algorithms.util.Pair;

/**
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable.
 * <p>
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the
 * jth query where you must find the answer for Cj / Dj = ?.
 * <p>
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * <p>
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 * <p>
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 * <p>
 * See: <a href="https://leetcode.com/problems/evaluate-division/">Question</a>
 * <p>
 * Time Complexity: O((N + Q) * log(N)). where N is the number of equations, Q is the number of queries.
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class EvaluateDivision {
    public static void main(String[] args) {
        // Example equations and values
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));

        double[] values = { 2.0, 3.0 };

        // Example queries
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"),
            Arrays.asList("b", "a"),
            Arrays.asList("a", "e"),
            Arrays.asList("a", "a"),
            Arrays.asList("x", "x")
        );

        // Calculate the result of queries using the calcEquation method
        double[] results = calcEquation(equations, values, queries);

        // Print the results
        System.out.println("Results of queries:");
        for (int i = 0; i < queries.size(); i++) {
            System.out.println(queries.get(i).get(0) + " / " + queries.get(i).get(1) + " = " + results[i]);
        }
    }

    /**
     * Calculates the result of division operations based on given equations and values.
     *
     * @param equations List of equations, where each equation is represented by a list of two strings
     * @param values    Array of values corresponding to each equation
     * @param queries   List of queries, where each query is represented by a list of two strings
     * @return Array of results for each query
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // HashMap to represent the union-find data structure
        HashMap<String, Pair<String, Double>> union = new HashMap<>();

        // Process each equation and build the union-find structure
        for (int i = 0; i < values.length; i++) {
            addUnion(union, equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        // Array to store the results of queries
        double[] ans = new double[queries.size()];

        // Process each query and calculate the result
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);

            // If either variable in the query is not present in the union-find structure, set result to -1.0
            if (!union.containsKey(a) || !union.containsKey(b)) {
                ans[i] = -1.0;
            } else {
                // Find the representatives of the variables in the union-find structure
                Pair<String, Double> divident = find(union, a);
                Pair<String, Double> divisor = find(union, b);

                // If the representatives are the same, calculate the result; otherwise, set result to -1.0
                if (divident.getKey().equals(divisor.getKey())) {
                    ans[i] = divident.getValue() / divisor.getValue();
                } else {
                    ans[i] = -1.0;
                }
            }
        }

        return ans;
    }

    /**
     * Finds the representative and the accumulated value of a node in the union-find structure.
     *
     * @param union HashMap representing the union-find structure
     * @param node  Node to find the representative for
     * @return Pair containing the representative and the accumulated value
     */
    public static Pair<String, Double> find(HashMap<String, Pair<String, Double>> union, String node) {
        // If the node is not present in the union-find structure, add it with default values
        if (!union.containsKey(node)) {
            union.put(node, new Pair<>(node, 1.0));
        }

        // Get the current representative and accumulated value for the node
        Pair<String, Double> curr = union.get(node);

        // If the representative is not the node itself, recursively find the representative for the parent
        if (!curr.getKey().equals(node)) {
            Pair<String, Double> newNode = find(union, curr.getKey());
            // Update the representative and accumulated value for the current node
            union.put(node, new Pair<>(newNode.getKey(), curr.getValue() * newNode.getValue()));
        }

        // Return the representative and the accumulated value for the node
        return union.get(node);
    }

    /**
     * Adds a new union to the union-find structure based on given divident, divisor, and value.
     *
     * @param union  HashMap representing the union-find structure
     * @param a      Divident in the equation
     * @param b      Divisor in the equation
     * @param value  Value representing the result of the division
     */
    public static void addUnion(HashMap<String, Pair<String, Double>> union, String a, String b, double value) {
        // Find the representatives and accumulated values for the divident and divisor
        Pair<String, Double> divident = find(union, a);
        Pair<String, Double> divisor = find(union, b);

        // If the representatives are different, update the union with the new representative and accumulated value
        if (!divident.getKey().equals(divisor.getKey())) {
            union.put(divident.getKey(), new Pair<>(divisor.getKey(), divisor.getValue() * value / divident.getValue()));
        }
    }
}
