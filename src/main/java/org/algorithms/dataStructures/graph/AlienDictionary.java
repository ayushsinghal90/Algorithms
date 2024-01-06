package org.algorithms.dataStructures.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class AlienDictionary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = Integer.parseInt(sc.next());
        while (t-- > 0) {
            int n = Integer.parseInt(sc.next());
            int k = Integer.parseInt(sc.next());

            String[] words = new String[n];

            for (int i = 0; i < n; i++) {
                words[i] = sc.next();
            }

            String order = findOrder(words, n, k);
            if (order.length() == 0) {
                System.out.println(0);
                continue;
            }
            String[] temp = new String[n];
            System.arraycopy(words, 0, temp, 0, n);

            Arrays.sort(temp, new Comparator<String>() {

                @Override
                public int compare(String a, String b) {
                    int index1 = 0;
                    int index2 = 0;
                    for (int i = 0; i < Math.min(a.length(), b.length()) && index1 == index2; i++) {
                        index1 = order.indexOf(a.charAt(i));
                        index2 = order.indexOf(b.charAt(i));
                    }

                    if (index1 == index2 && a.length() != b.length()) {
                        if (a.length() < b.length()) return -1;
                        else return 1;
                    }

                    if (index1 < index2) return -1;
                    else return 1;
                }
            });

            int flag = 1;
            for (int i = 0; i < n; i++) {
                if (!words[i].equals(temp[i])) {
                    flag = 0;
                    break;
                }
            }

            System.out.println(flag);
        }
    }

    public static String findOrder(String[] dict, int N, int K) {
        Graph graph = new Graph(K);

        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    graph.addEdge(s1.charAt(j) - 'a', s2.charAt(j) - 'a');
                    break;
                }
            }
        }
        return new TopologicalSort(graph).topSort();
    }
}
