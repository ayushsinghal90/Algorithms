package org.algorithms.dataStructures.graph;

/**
 * Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary.
 * Find the order of characters in the alien language.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/alien-dictionary/1">Question</a>
 * <p>
 * Time Complexity: O(V + E), where V is the number of character(vertices) and E is the number of words.
 * <p>
 * Auxiliary Space: O(V).
 *
 * @author Ayush Singhal
 */
class AlienDictionary {
    /**
     * Finds the order of characters in the alien language.
     *
     * @param dict An array of words in the alien language.
     * @param N    The number of words in the array.
     * @param K    The size of the alphabet (number of characters).
     * @return The order of characters in the alien language.
     */
    public static String findOrder(String[] dict, int N, int K) {
        Graph graph = new Graph(26);

        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    graph.addEdge(s1.charAt(j) - 'a', s2.charAt(j) - 'a');
                    break;
                }
            }
        }
        return new TopologicalSort(graph).topSort();
    }

    /**
     * Main method for testing the findOrder function.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Example usage
        String[] dict = { "wrt", "wrf", "er", "ett", "rftt" };
        int N = dict.length;
        int K = 5;
        String result = findOrder(dict, N, K);
        System.out.println("Order of characters: " + result);
    }
}
