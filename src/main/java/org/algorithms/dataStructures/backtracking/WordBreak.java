package org.algorithms.dataStructures.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class WorkBreak {
    static List<String> res;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String[] arr = in.readLine().trim().split("\\s+");
            List<String> dict = new ArrayList<String>();
            dict.addAll(Arrays.asList(arr).subList(0, n));
            String s = in.readLine();

            List<String> ans = new ArrayList<String>();
            ans = wordBreak(n, dict, s);
            if (ans.size() == 0) System.out.println("Empty");
            else {
                List<String> sol = ans.stream().sorted().collect(Collectors.toList());
                for (int i = 0; i < sol.size(); i++) System.out.print("(" + sol.get(i) + ")");
                System.out.println();
            }
        }
    }

    static List<String> wordBreak(int n, List<String> dict, String s) {
        // code here
        HashSet<String> dictSet = new HashSet<>(dict);
        res = new ArrayList<>();
        make(dictSet, new ArrayList<String>(), 0, s);
        return res;
    }

    static void make(HashSet<String> dict, List<String> curr, int i, String s) {
        if (i == s.length()) {
            res.add(String.join(" ", curr));
        }

        for (int j = i; j < s.length(); j++) {
            String temp = s.substring(i, j + 1);
            if (dict.contains(temp)) {
                curr.add(temp);
                make(dict, curr, j + 1, s);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
