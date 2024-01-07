package org.algorithms.dataStructures.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * {@link WordBreak}
 * This is a memorization solution.
 * <p>
 * Time Complexity: O(N^3).
 * <p>
 * Auxiliary Space: O(N^2).
 *
 * @author Ayush Singhal
 */
public class WorkBreakMemorization {
    // HashMap to store the combinations of words for each input string
    static HashMap<String, List<String>> m;

    /**
     * Combines the given string with each element in the list.
     *
     * @param v: List of strings to combine with
     * @param w: String to combine
     */
    static void combine(List<String> v, String w) {
        for (int i = 0; i < v.size(); i++) {
            v.set(i, v.get(i) + " " + w);
        }
    }

    /**
     * Calculates the combinations of words for the given string using backtracking and memoization.
     *
     * @param s:  The input string
     * @param mp: HashSet containing valid words
     * @return List of combinations of words for the input string
     */
    static List<String> cal(String s, HashSet<String> mp) {
        // If the combinations for the given string already exist in the HashMap, return them
        if (m.containsKey(s))
            return m.get(s);

        List<String> result = new ArrayList<>();

        // If the given string itself is a valid word, add it to the result
        if (mp.contains(s))
            result.add(s);

        // Iterating through all possible splits of the string
        for (int i = 1; i < s.length(); i++) {
            String rem = s.substring(i);

            // If the remaining string is a valid word, calculate the combinations of words for the prefix of the string
            if (mp.contains(rem)) {
                List<String> V = cal(s.substring(0, i), mp);
                List<String> v = new ArrayList<>();

                // Creating a new list by copying all elements from the existing list
                for (int j = 0; j < V.size(); j++) {
                    v.add(V.get(j));
                }

                // Combining the words from the prefix and the remaining string
                combine(v, rem);

                // Adding all the combinations to the result list
                for (int j = 0; j < v.size(); j++) {
                    result.add(v.get(j));
                }
            }
        }

        // Storing the result in the HashMap for future use
        m.put(s, result);

        // Returning the combinations of words for the given string
        return result;
    }

    /**
     * Solves the Word Break problem using memoization.
     *
     * @param n:    Number of words in the dictionary
     * @param dict: List of words in the dictionary
     * @param s:    Input string to be segmented
     * @return List of combinations of words forming the input string
     */
    static List<String> wordBreak(int n, List<String> dict, String s) {
        HashSet<String> mp = new HashSet<>();
        m = new HashMap<>();

        // Adding all the words from the dictionary to a HashSet for efficient lookup
        for (int i = 0; i < n; i++)
            mp.add(dict.get(i));

        // Calculating the combinations of words for the given string
        List<String> ans = cal(s, mp);

        // Returning the combinations of words
        return ans;
    }
}
