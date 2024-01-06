package org.algorithms.dataStructures.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** https://leetcode.com/problems/remove-invalid-parentheses/ */
class RemoveParenthesis {
    Set<String> res = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                right = left == 0 ? right + 1 : right;
                left = left > 0 ? left - 1 : left;
            }
        }

        helper(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<String>(res);
    }

    public void helper(
        String s, int index, int left, int right, int leftrem, int rightrem, StringBuilder expression
    ) {
        if (index == s.length()) {
            if (leftrem == 0 && rightrem == 0) {
                res.add(expression.toString());
            }
        } else {
            char curr = s.charAt(index);
            int length = expression.length();

            if ((curr == '(' && leftrem > 0) || (curr == ')' && rightrem > 0)) {
                helper(s, index + 1, left, right, leftrem - (curr == '(' ? 1 : 0), rightrem - (curr == ')' ? 1 : 0), expression);
            }

            expression.append(curr);

            if (curr != '(' && curr != ')') {
                helper(s, index + 1, left, right, leftrem, rightrem, expression);
            } else if (curr == '(') {
                helper(s, index + 1, left + 1, right, leftrem, rightrem, expression);
            } else if (right < left) {
                helper(s, index + 1, left, right + 1, leftrem, rightrem, expression);
            }

            expression.deleteCharAt(length);
        }
    }
}
