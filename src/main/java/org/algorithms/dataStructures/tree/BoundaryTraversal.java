package org.algorithms.dataStructures.tree;

import java.util.ArrayList;

import static org.algorithms.dataStructures.tree.BuildTree.buildTree;

/**
 * Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order:
 * <p>
 * 1. Left boundary nodes: defined as the path from the root to the left-most node ie-
 * the leaf node you could reach when you always travel preferring the left subtree over the right subtree.
 * <p>
 * 2. Leaf nodes: All the leaf nodes except for the ones that are part of left or right boundary.
 * <p>
 * 3. Reverse right boundary nodes: defined as the path from the right-most node to the root.
 * The right-most node is the leaf node you could reach when you always travel preferring the
 * right subtree over the left subtree. Exclude the root from this as it was already included in the
 * traversal of left boundary nodes.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1">Question</a>
 * <p>
 * Time Complexity: O(N).
 * <p>
 * Auxiliary Space: O(Height of Tree).
 * @author Ayush Singhal
 */
public class BoundaryTraversal {

    /**
     * Main method to demonstrate boundary traversal.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        String s = "1 2 3 4 5 6 7 N N 8 9 N N N N";
        Node root = buildTree(s);

        ArrayList<Integer> res = printBoundary(root);
        for (Integer num : res) {
            System.out.print(num + " ");
        }
    }

    /**
     * Computes the left view of the binary tree and appends it to the provided list.
     *
     * @param node The current node.
     * @param ans  The list to store the left view elements.
     */
    public static void leftView(Node node, ArrayList<Integer> ans) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            ans.add(node.data);
            leftView(node.left, ans);
        } else if (node.right != null) {
            ans.add(node.data);
            leftView(node.right, ans);
        }
    }

    /**
     * Computes the right view of the binary tree and appends it to the provided list.
     *
     * @param node The current node.
     * @param ans  The list to store the right view elements.
     */
    public static void rightView(Node node, ArrayList<Integer> ans) {
        if (node == null) {
            return;
        }

        if (node.right != null) {
            rightView(node.right, ans);
            ans.add(node.data);
        } else if (node.left != null) {
            rightView(node.left, ans);
            ans.add(node.data);
        }
    }

    /**
     * Computes the leaf view of the binary tree and appends it to the provided list.
     *
     * @param node The current node.
     * @param ans  The list to store the leaf view elements.
     */
    public static void leafView(Node node, ArrayList<Integer> ans) {
        if (node == null) {
            return;
        }
        leafView(node.left, ans);
        if (node.left == null && node.right == null) {
            ans.add(node.data);
        }
        leafView(node.right, ans);
    }

    /**
     * Computes the boundary traversal of the binary tree and returns it as a list.
     *
     * @param node The root of the binary tree.
     * @return The list containing the elements of the boundary traversal.
     */
    static ArrayList<Integer> printBoundary(Node node) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(node.data); // Add the root

        // Add the left boundary (left view excluding the leaf nodes)
        leftView(node.left, ans);

        // Add the leaf nodes
        leafView(node, ans);

        // Add the right boundary (right view excluding the leaf nodes)
        rightView(node.right, ans);

        return ans;
    }
}
