package org.algorithms.dataStructures.linkedList;

/**
 * A class representing a node in a singly linked list.
 *
 * @author Ayush Singhal
 */
public class Node {
    int data;   // Data stored in the node
    Node next;  // Reference to the next node in the linked list

    /**
     * Constructs a new node with the given data.
     *
     * @param key the data to be stored in the node
     */
    Node(int key) {
        data = key;
        next = null;
    }
}

