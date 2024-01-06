package org.algorithms.dataStructures.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class LowestCommonAncestor {
    static Node buildTree(String str) {

        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        String[] ip = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        int i = 1;
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= ip.length) break;

            currVal = ip[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            String s = br.readLine().trim();
            Node root = buildTree(s);
            String[] ab = br.readLine().trim().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);

            System.out.println(findDist(root, a, b));
        }
    }

    /* Should return minimum distance between a and b
    in a tree with given root*/
    static Node lca(Node root, int a, int b) {
        if (root == null) {
            return root;
        }

        if ((root.data == a) || (root.data == b)) {
            return root;
        }

        Node left = lca(root.left, a, b);
        Node right = lca(root.right, a, b);

        if ((left != null) && (right != null)) {
            return root;
        }
        if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    static int findDist(Node root, int a, int b) {

        Node lca = lca(root, a, b);
        int d1 = distanceFromRoot(lca, a, 0);
        int d2 = distanceFromRoot(lca, b, 0);
        return d1 + d2;
        // Your code here

    }

    static int distanceFromRoot(Node root, int a, int level) {
        if (root == null) {
            return -1;
        }

        if (root.data == a) {
            return level;
        }

        int count = distanceFromRoot(root.left, a, level + 1);

        if (count == -1) {
            return distanceFromRoot(root.right, a, level + 1);
        }

        return count;
    }
}
