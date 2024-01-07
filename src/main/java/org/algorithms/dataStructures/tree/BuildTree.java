package org.algorithms.dataStructures.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Build tree from string.
 *
 * <p>
 * Time Complexity: O(NlogN).
 * <p>
 * Auxiliary Space: O(logN).
 * @author Ayush Singhal
 */
public class BuildTree {
    /**
     * Builds a binary tree from the given string representation.
     *
     * @param str a string representing the binary tree in level order traversal (with 'N' for null nodes)
     * @return the root of the constructed binary tree
     */
    public static Node buildTree(String str) {

        // If the input string is empty or starts with 'N', return null
        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        // Split the input string into an array of values
        String[] ip = str.split(" ");

        // Create the root of the tree using the first value in the array
        Node root = new Node(Integer.parseInt(ip[0]));

        // Initialize a queue for level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        // Iterate through the values in the array to construct the binary tree
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null, create and add it to the queue
            if (!currVal.equals("N")) {
                currNode.left = new Node(Integer.parseInt(currVal));
                queue.add(currNode.left);
            }

            // Move to the next value in the array
            i++;

            // Break if there are no more values in the array
            if (i >= ip.length) break;

            // Get the value for the right child
            currVal = ip[i];

            // If the right child is not null, create and add it to the queue
            if (!currVal.equals("N")) {
                currNode.right = new Node(Integer.parseInt(currVal));
                queue.add(currNode.right);
            }

            // Move to the next value in the array
            i++;
        }

        // Return the root of the constructed binary tree
        return root;
    }
}
