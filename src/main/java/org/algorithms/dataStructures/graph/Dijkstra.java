package org.algorithms.dataStructures.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.algorithms.util.Pair;

class Dijkstra {
    public static void main(String[] args) throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String[] str = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<ArrayList<Integer>>());
            }

            int i = 0;
            while (i++ < E) {
                String[] S = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                ArrayList<Integer> t1 = new ArrayList<Integer>();
                ArrayList<Integer> t2 = new ArrayList<Integer>();
                t1.add(v);
                t1.add(w);
                t2.add(u);
                t2.add(w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }

            int S = Integer.parseInt(read.readLine());

            int[] ptr = dijkstra(V, adj, S);

            for (i = 0; i < V; i++) System.out.print(ptr[i] + " ");
            System.out.println();
        }
    }

    // Function to find the shortest distance of all the vertices
    // from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // Write your code here
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(V, Comparator.comparingInt(Pair::getKey));

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        pq.add(new Pair<>(0, S));

        while (!pq.isEmpty()) {
            int curr = pq.poll().getValue();

            for (ArrayList<Integer> v : adj.get(curr)) {
                int vertx = v.get(0);
                int vDist = v.get(1);
                if (dist[vertx] > (dist[curr] + vDist)) {
                    dist[vertx] = dist[curr] + vDist;
                    pq.add(new Pair<>(dist[vertx], vertx));
                }
            }
        }

        return dist;
    }
}
