package org.algorithms.dataStructures.graph;

import java.util.LinkedList;

public class Graph {
    public final LinkedList<Integer>[] abj;

    Graph(int n) {
        abj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            abj[i] = new LinkedList<>();
        }
    }

    void addEdge(int s, int e) {
        abj[s].add(e);
    }

    public int glength() {
        return abj.length;
    }
}
