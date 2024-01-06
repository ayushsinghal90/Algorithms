package org.algorithms.dataStructures.graph;

import java.util.Stack;

public class TopologicalSort {
    Graph graph;

    TopologicalSort(Graph graph) {
        this.graph = graph;
    }

    public void topSortu(int i, boolean[] vis, Stack<Integer> mem) {
        vis[i] = true;

        for (int j : graph.abj[i]) {
            if (!vis[j]) {
                topSortu(j, vis, mem);
            }
        }
        mem.push(i);
    }

    public String topSort() {
        boolean[] vis = new boolean[graph.glength()];

        Stack<Integer> mem = new Stack<>();

        for (int i = 0; i < graph.glength(); i++) {
            if (!vis[i]) {
                topSortu(i, vis, mem);
            }
        }

        String res = "";
        while (!mem.isEmpty()) {
            res += (char) ('a' + mem.pop());
        }
        return res;
    }
}
