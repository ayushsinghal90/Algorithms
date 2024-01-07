package org.algorithms.dataStructures.graph;

import java.util.LinkedList;

/**
 * Class representing a directed graph.
 *
 * @author Ayush Singhal
 */
public class Graph {
    /**
     * Adjacency list for the graph.
     */
    public final LinkedList<Integer>[] abj;

    /**
     * Initializes a new instance of the {@code Graph} class with the specified number of vertices.
     *
     * @param n: The number of vertices in the graph.
     */
    Graph(int n) {
        abj = new LinkedList[n];
    }

    /**
     * Adds a directed edge from vertex {@code s} to vertex {@code e}.
     *
     * @param s: The source vertex.
     * @param e: The destination vertex.
     */
    void addEdge(int s, int e) {
        if (abj[s] == null) {
            abj[s] = new LinkedList<>();
        }
        abj[s].add(e);
    }

    /**
     * Gets the number of vertices in the graph.
     *
     * @return The number of vertices in the graph.
     */
    public int glength() {
        return abj.length;
    }
}
