package org.algorithms.dataStructures.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * The groups must be created in order of their appearance in the original array.
 * Look at the sample case for clarification.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/print-anagrams-together/1">Question</a>
 * <p>
 * Time Complexity: O(N*|s|).
 * <p>
 * Auxiliary Space: O(N*|s|).
 *
 * @author Ayush Singhal
 */
public class AnagramTogether {

    public static void main(String[] args) {
        // Input array of strings
        String[] stringList = { "act", "god", "cat", "dog", "tac" };

        // Get the list of anagrams
        List<List<String>> result = anagrams(stringList);

        // Print the anagram groups
        for (List<String> group : result) {
            for (String word : group) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }

    /**
     * Groups anagrams together from the given array of strings.
     *
     * @param stringArray: Array of strings
     * @return List of lists containing anagram groups
     */
    public static List<List<String>> anagrams(String[] stringArray) {
        // HashMap to store sorted strings as keys and corresponding anagram groups as values
        HashMap<String, List<String>> anagramMap = new HashMap<>();

        // Iterate through the array of strings
        for (String str : stringArray) {
            // Sort the current string
            String sortedString = sort(str);

            // If the sorted string is not in the map, add it with an empty list
            if (!anagramMap.containsKey(sortedString)) {
                anagramMap.put(sortedString, new ArrayList<>());
            }

            // Add the original string to its anagram group
            anagramMap.get(sortedString).add(str);
        }

        return new ArrayList<>(anagramMap.values());
    }

    /**
     * Sorts the characters in a string.
     *
     * @param inputString Input string
     * @return Sorted string
     */
    public static String sort(String inputString) {
        // Array to store the count of each character
        int[] charCount = new int[26];

        // Count the occurrences of each character
        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            charCount[currentChar - 'a']++;
        }

        // Build the sorted string
        StringBuilder sortedString = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < charCount[i]; j++) {
                sortedString.append((char) (i + 'a'));
            }
        }

        return sortedString.toString();
    }
}
