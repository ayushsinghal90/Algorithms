package org.algorithms.dataStructures.graph;

import java.util.Stack;

/**
 * Given a Directed Acyclic Graph (DAG) with V vertices and E edges, Find any Topological Sorting of that Graph.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/topological-sort/1">Question</a>
 * <p>
 * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
 * <p>
 * Auxiliary Space: O(V).
 *
 * @author Ayush Singhal
 */
public class TopologicalSort {
    /**
     * The graph to be sorted.
     */
    Graph graph;

    /**
     * Initializes a new instance of the {@code TopologicalSort} class with the specified graph.
     *
     * @param graph The graph to be sorted.
     */
    TopologicalSort(Graph graph) {
        this.graph = graph;
    }

    /**
     * Utility method for topological sort.
     *
     * @param i   The current vertex.
     * @param vis Array to track visited vertices.
     * @param mem Stack to store the topological order.
     */
    public void topSortUtil(int i, boolean[] vis, Stack<Integer> mem) {
        if (graph.abj[i] == null) {
            return;
        }

        vis[i] = true;

        for (int j : graph.abj[i]) {
            if (!vis[j]) {
                topSortUtil(j, vis, mem);
            }
        }
        mem.push(i);
    }

    /**
     * Performs topological sort on the graph.
     *
     * @return A string representing the topological order.
     */
    public String topSort() {
        boolean[] vis = new boolean[graph.glength()];

        Stack<Integer> mem = new Stack<>();

        for (int i = 0; i < graph.glength(); i++) {
            if (!vis[i]) {
                topSortUtil(i, vis, mem);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!mem.isEmpty()) {
            res.append((char) ('a' + mem.pop()));
        }
        return res.toString();
    }
}
