package org.algorithms.dataStructures.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s that contains parentheses and letters,
 * remove the minimum number of invalid parentheses to make the input string valid.
 * Return a list of unique strings that are valid with the minimum number of removals.
 * You may return the answer in any order.
 * <p>
 * See: <a href="https://leetcode.com/problems/remove-invalid-parentheses/">Question</a>
 * <p>
 * Time Complexity: O(2^N).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
class RemoveParenthesis {
    // Set to store unique valid expressions
    Set<String> res = new HashSet<>();

    /**
     * Removes invalid parentheses from the given string.
     *
     * @param s: The input string containing parentheses
     * @return List of unique valid expressions after removing invalid parentheses
     */
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;

        // Counting the number of left and right parentheses
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // Increment right only if there is a matching left parenthesis
                right = left == 0 ? right + 1 : right;
                left = left > 0 ? left - 1 : left;
            }
        }

        // Helper method to generate valid expressions
        helper(s, 0, 0, 0, left, right, new StringBuilder());

        return new ArrayList<>(res);
    }

    /**
     * Recursive helper method to generate valid expressions by removing invalid parentheses.
     *
     * @param s:           The input string containing parentheses
     * @param index:       Current index in the string
     * @param left:        Count of left parentheses so far
     * @param right:       Count of right parentheses so far
     * @param leftrem:     Remaining count of left parentheses to be removed
     * @param rightrem:    Remaining count of right parentheses to be removed
     * @param expression:  Current expression being formed
     */
    public void helper(String s, int index, int left, int right, int leftrem, int rightrem, StringBuilder expression) {
        if (index == s.length()) {
            // If the end of the string is reached, check if the current expression is valid
            if (leftrem == 0 && rightrem == 0) {
                res.add(expression.toString());
            }
        } else {
            char curr = s.charAt(index);
            int length = expression.length();

            // If the current character is a removable parenthesis, skip it and continue the recursion
            if ((curr == '(' && leftrem > 0) || (curr == ')' && rightrem > 0)) {
                helper(s, index + 1, left, right, leftrem - (curr == '(' ? 1 : 0), rightrem - (curr == ')' ? 1 : 0), expression);
            }

            // Append the current character to the expression
            expression.append(curr);

            // Recur without considering the current character (if not a parenthesis) or considering it based on the rules
            if (curr != '(' && curr != ')') {
                helper(s, index + 1, left, right, leftrem, rightrem, expression);
            } else if (curr == '(') {
                helper(s, index + 1, left + 1, right, leftrem, rightrem, expression);
            } else if (right < left) {
                helper(s, index + 1, left, right + 1, leftrem, rightrem, expression);
            }

            // Backtrack: Remove the last appended character to explore other possibilities
            expression.deleteCharAt(length);
        }
    }

    /**
     * Main method to execute the program.
     *
     * @param args Command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // Example usage
        RemoveParenthesis removeParenthesis = new RemoveParenthesis();

        // Input string with parentheses
        String inputString = "()())()";

        // Remove invalid parentheses
        List<String> validExpressions = removeParenthesis.removeInvalidParentheses(inputString);

        // Display the result
        System.out.println("Original String: " + inputString);
        System.out.println("Valid Expressions After Removing Invalid Parentheses: ");
        for (String expression : validExpressions) {
            System.out.println(expression);
        }
    }
}
