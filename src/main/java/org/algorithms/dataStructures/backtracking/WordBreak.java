package org.algorithms.dataStructures.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given a string s and a dictionary of words dict of length n, add spaces in s to
 * construct a sentence where each word is a valid dictionary word.
 * Each dictionary word can be used more than once. Return all such possible sentences.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/word-break-part-23249/1">Question</a>
 * <p>
 * Time Complexity: O(2^N).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
class WordBreak {
    static List<String> res;

    /**
     * Main method to test the word break functionality.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        List<String> dict = Arrays.asList("this", "th", "is", "famous", "Word", "break", "pro", "b", "lem", "problem");
        String s = "Wordbreakproblem";
        List<String> result = wordBreak(dict.size(), dict, s);
        System.out.println(result);

        List<String> result2 = WorkBreakMemorization.wordBreak(dict.size(), dict, s);
        System.out.println(result2);
    }

    /**
     * Finds all possible word break combinations in the given string using the provided dictionary.
     *
     * @param n:     The length of the string.
     * @param dict:  The list of words in the dictionary.
     * @param s:     The input string.
     * @return A list of all possible word break combinations.
     */
    static List<String> wordBreak(int n, List<String> dict, String s) {
        // Create a HashSet for faster word lookup
        HashSet<String> dictSet = new HashSet<>(dict);

        // Initialize the result list
        res = new ArrayList<>();

        // Recursive function to find word break combinations
        make(dictSet, new ArrayList<String>(), 0, s);

        return res;
    }

    /**
     * Recursive helper function to find word break combinations.
     *
     * @param dict:  The HashSet representing the dictionary.
     * @param curr:  The current list of words in the combination.
     * @param i:     The current index in the input string.
     * @param s:     The input string.
     */
    static void make(HashSet<String> dict, List<String> curr, int i, String s) {
        // If the end of the string is reached, add the current combination to the result
        if (i == s.length()) {
            res.add(String.join(" ", curr));
            return;
        }

        // Explore all possible substrings starting from the current index
        for (int j = i; j < s.length(); j++) {
            String temp = s.substring(i, j + 1);
            // If the substring is in the dictionary, add it to the current combination
            if (dict.contains(temp)) {
                curr.add(temp);
                // Recursively explore the remaining string
                make(dict, curr, j + 1, s);
                // Backtrack to explore other possibilities
                curr.remove(curr.size() - 1);
            }
        }
    }
}
