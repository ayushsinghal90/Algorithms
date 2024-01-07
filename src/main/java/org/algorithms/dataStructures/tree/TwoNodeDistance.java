package org.algorithms.dataStructures.tree;

import java.io.IOException;

import static org.algorithms.dataStructures.tree.LowestCommonAncestor.lca;

/**
 * Given a binary tree and two node values your task is to find the minimum distance between them.
 * The given two nodes are guaranteed to be in the binary tree and nodes are numbered from 1 to N.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/quick-sort/1">Question</a>
 * <p>
 * Time Complexity: O(N). No. of nodes in tree.
 * <p>
 * Auxiliary Space: O(H). Height of the tree.
 *
 * @author Ayush Singhal
 */
public class TwoNodeDistance {
    /**
     * Main function to demonstrate the findDist method.
     *
     * @param args command line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException {
        // Level Order input.
        //              5
        //            /   \
        //          4      6
        //         /        \
        //        3          7
        //                    \
        //                     8
        String s = "5 4 6 3 N N 7 N N N 8";
        Node root = BuildTree.buildTree(s);
        int a = 3;
        int b = 8;

        System.out.println(findDist(root, a, b));
    }

    /**
     * Finds the distance between two nodes with values 'a' and 'b' in a Binary Tree.
     *
     * @param root: the root of the Binary Tree
     * @param a:    the value of the first node
     * @param b:    the value of the second node
     * @return The distance between the two nodes, or -1 if either node is not found
     */
    public static int findDist(Node root, int a, int b) {
        // Find the Lowest Common Ancestor (LCA) of nodes with values 'a' and 'b'
        Node lca = lca(root, a, b);

        // If LCA is not found, return -1 as either 'a' or 'b' is not present in the tree
        if (lca == null) {
            return -1;
        }

        // Calculate the distance from the LCA to node 'a'
        int d1 = distanceFromRoot(lca, a, 0);

        // Calculate the distance from the LCA to node 'b'
        int d2 = distanceFromRoot(lca, b, 0);

        // Return the sum of distances between nodes 'a' and 'b'
        return d1 + d2;
    }

    /**
     * Finds the distance of a node with value 'a' from the root of a Binary Tree.
     *
     * @param root:  the root of the Binary Tree
     * @param a:     the value of the target node
     * @param level: the current level (distance from the root) in the recursive call
     * @return The distance of the target node from the root, or -1 if the node is not found
     */
    public static int distanceFromRoot(Node root, int a, int level) {
        // If the current node is null, the target node is not found, return -1
        if (root == null) {
            return -1;
        }

        // If the current node's data matches the target value, return the current level
        if (root.data == a) {
            return level;
        }

        // Recursively search for the target node in the left subtree
        int count = distanceFromRoot(root.left, a, level + 1);

        // If the target node is not found in the left subtree, search in the right subtree
        if (count == -1) {
            return distanceFromRoot(root.right, a, level + 1);
        }

        // Return the distance of the target node from the root
        return count;
    }
}
