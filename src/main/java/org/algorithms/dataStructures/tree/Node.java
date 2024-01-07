package org.algorithms.dataStructures.tree;

/**
 * A simple representation of a binary tree node.
 *
 * @author Ayush Singhal
 */
public class Node {

    /** The data stored in the node. */
    int data;

    /** Reference to the left child node. */
    Node left;

    /** Reference to the right child node. */
    Node right;

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to be stored in the node
     */
    public Node(int data) {
        this.data = data;
        left = null; // Initially, there is no left child
        right = null; // Initially, there is no right child
    }
}

