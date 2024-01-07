package org.algorithms.dataStructures.tree;

import java.io.IOException;

/**
 * Given a Binary Search Tree (with all values unique) and two node values n1 and n2 (n1!=n2).
 * Find the Lowest Common Ancestors of the two nodes in the BST.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1">Question</a>
 * <p>
 * Time Complexity: O(N). Where N is height of the tree.
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
class LowestCommonAncestor {
    /**
     * Main function to demonstrate the lca method.
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
        int a = 7;
        int b = 8;
        Node commonNode = lca(root, a, b);
        System.out.println(commonNode != null ? commonNode.data : "Common Node not found");
    }

    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes with values 'a' and 'b' in a Binary Search Tree (BST).
     *
     * @param root: the root of the BST
     * @param a:    the value of the first node
     * @param b:    the value of the second node
     * @return The Lowest Common Ancestor (LCA) node.
     */
    public static Node lca(Node root, int a, int b) {
        // Traverse the BST until the LCA is found
        while (root != null) {
            // If both values are smaller than the current node's data, move to the left subtree
            if (root.data > a && root.data > b) {
                root = root.left;
            }
            // If both values are larger than the current node's data, move to the right subtree
            else if (root.data < a && root.data < b) {
                root = root.right;
            }
            // If one value is smaller and the other is larger, the current node is the LCA
            else {
                break;
            }
        }
        // Return the LCA node
        return root;
    }
}
