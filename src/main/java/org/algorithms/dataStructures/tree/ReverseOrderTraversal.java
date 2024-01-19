package org.algorithms.dataStructures.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.algorithms.dataStructures.tree.BuildTree.buildTree;

/**
 * Given a binary tree of size N, find its reverse level order traversal.
 * ie- the traversal must begin from the last level.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/reverse-level-order-traversal/1">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class ReverseOrderTraversal {

    /**
     * The main function creates a binary tree, performs reverse level order traversal,
     * and prints the result.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        String s = "1 2 3 4 5 6 7 N N 8 9 N N N N";
        Node root = buildTree(s);
        ArrayList<Integer> ans = reverseLevelOrder(root);

        for (Integer val : ans)
            System.out.print(val + " ");
        System.out.println();
    }

    /**
     * Performs reverse level order traversal of a binary tree.
     *
     * @param root The root node of the binary tree.
     * @return ArrayList containing the elements in reverse level order.
     */
    public static ArrayList<Integer> reverseLevelOrder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> resStack = new Stack<>();
        Queue<Node> nodes = new LinkedList<>();

        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node cur = nodes.remove();

            if (cur.right != null) {
                nodes.add(cur.right);
            }
            if (cur.left != null) {
                nodes.add(cur.left);
            }
            resStack.push(cur.data);
        }

        while (!resStack.isEmpty()) {
            res.add(resStack.pop());
        }
        return res;
    }
}

