package org.algorithms.dataStructures.linkedList;

import java.util.Scanner;

/**
 * Given K sorted linked lists of different sizes.
 * The task is to merge them in such a way that after merging they will be a single sorted linked list.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/merge-k-sorted-linked-lists/1">Question</a>
 * <p>
 * Time Complexity: O(n*k*Logk). n is node in a singel linked list and k is not of linked lists.
 * <p>
 * Auxiliary Space: O(k).
 *
 * @author Ayush Singhal
 */
class KMerge {
    /**
     * Prints the elements of a linked list.
     *
     * @param node the head of the linked list
     */
    public static void printList(Node node) {
        // Traverse the linked list and print each element
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    /**
     * The main method to take input, create linked lists, merge them, and print the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of test cases
        int t = sc.nextInt();
        while (t-- > 0) {
            // Input the number of linked lists in the array
            int N = sc.nextInt();

            // Create an array to store the linked lists
            Node[] a = new Node[N];

            // Input and create each linked list
            for (int i = 0; i < N; i++) {
                int n = sc.nextInt();

                // Create the head of the linked list
                Node head = new Node(sc.nextInt());
                Node tail = head;

                // Add nodes to the linked list
                for (int j = 0; j < n - 1; j++) {
                    tail.next = new Node(sc.nextInt());
                    tail = tail.next;
                }

                // Store the head of the linked list in the array
                a[i] = head;
            }

            // Merge the linked lists and print the result
            Node res = mergeKList(a, N);
            if (res != null) printList(res);
            System.out.println();
        }
    }

    /**
     * Merges two sorted linked lists into a single sorted linked list.
     *
     * @param n1: the head of the first linked list
     * @param n2: the head of the second linked list
     * @return The head of the merged linked list
     */
    static Node merge(Node n1, Node n2) {
        // Base cases: If one of the linked lists is null, return the other
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }

        Node res = null;

        // Compare the data of the nodes and merge recursively
        if (n1.data < n2.data) {
            res = n1;
            res.next = merge(n1.next, n2);
        } else {
            res = n2;
            res.next = merge(n1, n2.next);
        }

        return res;
    }

    /**
     * Merges an array of linked lists using divide and conquer approach.
     *
     * @param a: the array of linked lists
     * @param i: the start index of the array
     * @param j: the end index of the array
     * @return The head of the merged linked list
     */
    static Node mergeMid(Node[] a, int i, int j) {
        // Base case: If only one linked list in the array, return it
        if (i == j) {
            return a[i];
        }

        // Calculate the middle index
        int mid = (i + j) / 2;

        // Merge the two halves recursively
        return merge(mergeMid(a, i, mid), mergeMid(a, mid + 1, j));
    }

    /**
     * Merges an array of linked lists into a single sorted linked list.
     *
     * @param a: the array of linked lists
     * @param N: the number of linked lists in the array
     * @return The head of the merged linked list
     */
    static Node mergeKList(Node[] a, int N) {
        // Merge the array of linked lists using divide and conquer approach
        return mergeMid(a, 0, N - 1);
    }
}
