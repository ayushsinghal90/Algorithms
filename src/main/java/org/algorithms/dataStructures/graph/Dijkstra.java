package org.algorithms.dataStructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.algorithms.util.Pair;

/**
 * Given a weighted, undirected and connected graph of V vertices and
 * an adjacency list adj where adj[i] is a list of lists containing two integers
 * where the first integer of each list j denotes there is edge between i and j ,
 * second integers corresponds to the weight of that  edge .
 * You are given the source vertex S and You to Find the
 * shortest distance of all the vertex's from the source vertex S.
 * You have to return a list of integers denoting shortest distance between each node and Source vertex S.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1">Question</a>
 * <p>
 * Time Complexity: O((V + E) * log(V)), where V is the number of vertices and E is the number of edges in the graph.
 * <p>
 * Auxiliary Space: O(V + E).
 *
 * @author Ayush Singhal
 */
class Dijkstra {

    /**
     * Dijkstra's algorithm for finding the shortest paths from a source vertex to all other vertices
     *
     * @param V:   Number of vertices in the graph
     * @param adj: Adjacency list representation of the weighted directed graph
     * @param S:   Source vertex
     * @return An array containing the shortest distances from the source vertex to all other vertices
     */
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // Priority queue to store vertices with their distances from the source
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(V, Comparator.comparingInt(Pair::getKey));

        // Array to store the shortest distances from the source vertex
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        // Add the source vertex to the priority queue
        pq.add(new Pair<>(0, S));

        // Dijkstra's algorithm
        while (!pq.isEmpty()) {
            int curr = pq.poll().getValue();

            // Traverse the adjacency list of the current vertex
            for (ArrayList<Integer> v : adj.get(curr)) {
                int vertx = v.get(0);  // Adjacent vertex
                int vDist = v.get(1);  // Distance from the current vertex to the adjacent vertex

                // Relaxation step: Update the distance if a shorter path is found
                if (dist[vertx] > (dist[curr] + vDist)) {
                    dist[vertx] = dist[curr] + vDist;
                    pq.add(new Pair<>(dist[vertx], vertx));
                }
            }
        }

        // Return the array of shortest distances
        return dist;
    }

    /**
     * Main method to execute the program.
     *
     * @param args Command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // Example usage
        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges and weights to the adjacency list
        adj.get(0).add(new ArrayList<>(Arrays.asList(1, 2)));  // Edge from 0 to 1 with weight 2
        adj.get(0).add(new ArrayList<>(Arrays.asList(2, 4)));  // Edge from 0 to 2 with weight 4
        adj.get(1).add(new ArrayList<>(Arrays.asList(2, 1)));  // Edge from 1 to 2 with weight 1
        adj.get(1).add(new ArrayList<>(Arrays.asList(3, 7)));  // Edge from 1 to 3 with weight 7
        adj.get(2).add(new ArrayList<>(Arrays.asList(3, 3)));  // Edge from 2 to 3 with weight 3

        int[] result = dijkstra(V, adj, 0);

        // Output the shortest distances from the source (0) to all other vertices
        System.out.println("Shortest Distances from Source (0): " + Arrays.toString(result));
    }
}
