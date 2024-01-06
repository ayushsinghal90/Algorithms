package org.algorithms.dataStructures.linkedList;

import java.util.Scanner;

class KMerge {
    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int N = sc.nextInt();

            Node[] a = new Node[N];

            for (int i = 0; i < N; i++) {
                int n = sc.nextInt();

                Node head = new Node(sc.nextInt());
                Node tail = head;

                for (int j = 0; j < n - 1; j++) {
                    tail.next = new Node(sc.nextInt());
                    tail = tail.next;
                }

                a[i] = head;
            }

            Node res = mergeKList(a, N);
            if (res != null) printList(res);
            System.out.println();
        }
    }

// a is an array of Nodes of the heads of linked lists
// and N is size of array a

    // Function to merge K sorted linked list.
    static Node merge(Node n1, Node n2) {
        if (n1 == null) {

            return n2;
        }
        if (n2 == null) {
            return n1;
        }

        Node res = null;

        if (n1.data < n2.data) {
            res = n1;
            res.next = merge(n1.next, n2);
        } else {
            res = n2;
            res.next = merge(n1, n2.next);
        }

        return res;
    }

    static Node mergeMid(Node[] a, int i, int j) {
        if (i == j) {
            return a[i];
        }
        int mid = (i + j) / 2;

        return merge(mergeMid(a, i, mid), mergeMid(a, mid + 1, j));
    }

    static Node mergeKList(Node[] a, int N) {

        return mergeMid(a, 0, N - 1);
    }
}
