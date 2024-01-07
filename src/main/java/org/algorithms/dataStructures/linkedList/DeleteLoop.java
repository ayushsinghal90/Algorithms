package org.algorithms.dataStructures.linkedList;

import java.util.Scanner;

import static org.algorithms.dataStructures.linkedList.DetectLoop.detectLoop;

/**
 * Given a linked list of N nodes such that it may contain a loop.
 * A loop here means that the last node of the link list is connected
 * to the node at position X(1-based index). If the link list does not have any loop, X=0.
 * Remove the loop from the linked list, if it is present, i.e. unlink the last node which is forming the loop.
 * <p>
 * See: <a href="https://www.geeksforgeeks.org/problems/remove-loop-in-linked-list/1">Question</a>
 * <p>
 * Time Complexity: O(n). n length of list.
 * <p>
 * Auxiliary Space: O(1).
 *
 * @author Ayush Singhal
 */
public class DeleteLoop {
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

            // Remove loop
            removeLoop(head);

            // Detect and print whether the linked list has a loop
            if (detectLoop(head) || length(head) != n) System.out.println("0");
            else System.out.println("1");
        }
    }

    /**
     * Calculates and returns the length of a linked list.
     *
     * @param head: Head of the linked list
     * @return Length of the linked list
     */
    public static int length(Node head) {
        int ret = 0;

        // Traverse the linked list and count the nodes
        while (head != null) {
            ret += 1;
            head = head.next;
        }

        return ret;
    }

    /**
     * Removes a loop in the linked list if present.
     *
     * @param head: Head of the linked list
     */
    public static void removeLoop(Node head) {
        // Initialize two pointers: slow moves one step at a time, fast moves two steps at a time
        Node fast = head, slow = head;

        // Detect the presence of a loop using Floyd's Tortoise and Hare algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If slow and fast pointers meet, a loop is detected
            if (slow == fast) {
                break;
            }
        }

        // If no loop is detected, return
        if (fast == null || fast.next == null) {
            return;
        }

        // Move one pointer back to the head and continue until the pointers meet again
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Move the slow pointer to the node just before the start of the loop
        while (slow.next != fast) {
            slow = slow.next;
        }

        // Remove the loop by breaking the link
        slow.next = null;
    }
}
