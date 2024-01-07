package org.algorithms.dataStructures.linkedList;

import java.util.Scanner;

/**
 * Given a linked list of N nodes. The task is to check if the linked list has a loop.
 * Linked list can contain self loop.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/detect-loop-in-linked-list/1">Question</a>
 * <p>
 * Time Complexity: O(n). n length of list.
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
public class DetectLoop {
    /**
     * Creates a loop in a linked list by connecting the tail node to a node at a specific position.
     *
     * @param head Head of the linked list
     * @param tail Tail of the linked list
     * @param x    Position to create the loop (0-based index)
     */
    public static void makeLoop(Node head, Node tail, int x) {
        // If x is 0, no loop should be created
        if (x == 0) return;

        // Traverse the list to the node at position x
        Node curr = head;
        for (int i = 1; i < x; i++)
            curr = curr.next;

        // Connect the tail to the node at position x, creating a loop
        tail.next = curr;
    }

    /**
     * Main method to test loop detection in linked lists.
     * Reads input from the user and checks if the linked list has a loop.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        // Process each test case
        while (t-- > 0) {
            // Read the size of the linked list
            int n = sc.nextInt();

            // Create the linked list and obtain the head and tail
            int num = sc.nextInt();
            Node head = new Node(num);
            Node tail = head;

            // Populate the linked list
            for (int i = 0; i < n - 1; i++) {
                num = sc.nextInt();
                tail.next = new Node(num);
                tail = tail.next;
            }

            // Read the position to create a loop
            int pos = sc.nextInt();
            makeLoop(head, tail, pos);

            // Detect and print whether the linked list has a loop
            if (detectLoop(head)) System.out.println("True");
            else System.out.println("False");
        }
    }

    /**
     * Detects the presence of a loop in a linked list using Floyd's Tortoise and Hare algorithm.
     *
     * @param head Head of the linked list
     * @return True if a loop is detected, False otherwise
     */
    public static boolean detectLoop(Node head) {
        // Initialize two pointers: slow moves one step at a time, fast moves two steps at a time
        Node fast = head, slow = head;

        // Traverse the list until either the fast pointer reaches the end or they meet (loop detected)
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If slow and fast pointers meet, a loop is detected
            if (slow == fast) {
                return true;
            }
        }

        // No loop detected
        return false;
    }
}
