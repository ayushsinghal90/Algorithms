package org.algorithms.dataStructures.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.algorithms.util.Pair;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 * <p>
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence
 * 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * <p>
 * You will start at the bus stop source (You are not on any bus initially),
 * and you want to go to the bus stop target. You can travel between bus stops by buses only.
 * <p>
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 * <p>
 * See: <a href="https://leetcode.com/problems/bus-routes/">Question</a>
 * <p>
 * Time Complexity: O(R + B + E), where R is the number of routes, B is the total number of stops in all routes,
 * and E is the total number of edges in the graph.
 * <p>
 * Auxiliary Space: O(R + B + E).
 *
 * @author Ayush Singhal
 */
public class BusRoutes {
    /**
     * Calculates the minimum number of buses required to reach the destination from the source.
     *
     * @param routes 2D array representing bus routes, where each row is a bus route with stops
     * @param source Source bus stop
     * @param target Target bus stop
     * @return Minimum number of buses required, or -1 if it is not possible
     */
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        // If source and target are the same, no buses are needed
        if (source == target) return 0;

        // Create a graph to represent the buses and their intersections
        List<List<Integer>> graph = new ArrayList<>();
        for (int[] r : routes) {
            Arrays.sort(r);
            graph.add(new ArrayList<>());
        }

        // Build the graph based on intersections between bus routes
        for (int i = 0; i < routes.length; i++) {
            for (int j = i + 1; j < routes.length; j++) {
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // Set to keep track of visited buses for the source and target stops
        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> seent = new HashSet<>();

        // Queue for BFS traversal with pairs of bus index and steps taken
        Queue<Pair<Integer, Integer>> bfs = new LinkedList<>();

        // Initialize BFS with buses that contain the source stop
        for (int i = 0; i < routes.length; i++) {
            if (Arrays.binarySearch(routes[i], source) >= 0) {
                seen.add(i);
                bfs.add(new Pair<>(i, 0));
            }

            // Check if any route contains the target stop
            if (Arrays.binarySearch(routes[i], target) >= 0) {
                seent.add(i);
            }
        }

        // Perform BFS traversal to find the minimum number of buses required
        while (!bfs.isEmpty()) {
            Pair<Integer, Integer> cur = bfs.poll();

            // If the current bus route reaches the target stop, return the steps taken
            if (seent.contains(cur.getKey())) {
                return cur.getValue() + 1;
            }

            // Explore buses connected to the current bus route in the graph
            for (int r : graph.get(cur.getKey())) {
                if (!seen.contains(r)) {
                    seen.add(r);
                    bfs.add(new Pair<>(r, cur.getValue() + 1));
                }
            }
        }

        // If no valid path is found, return -1
        return -1;
    }

    /**
     * Checks if two bus routes intersect, i.e., they have common stops.
     *
     * @param a Bus route A
     * @param b Bus route B
     * @return True if routes intersect, False otherwise
     */
    public static boolean intersect(int[] a, int[] b) {
        int i = 0, j = 0;

        // Iterate through stops in both routes
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                return true;  // Stops are common, routes intersect
            } else if (a[i] > b[j]) {
                j++;
            } else {
                i++;
            }
        }

        // No common stops found, routes do not intersect
        return false;
    }

    public static void main(String[] args) {
        // Example bus routes, source stop, and target stop
        int[][] routes = {
            { 1, 2, 7 }, { 3, 6, 7 } };

        int source = 1;
        int target = 6;

        // Calculate the minimum number of buses required using the numBusesToDestination method
        int result = numBusesToDestination(routes, source, target);

        // Print the result
        System.out.println("Minimum number of buses required: " + result);
    }
}
