package org.algorithms.dataStructures.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MinJumps {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int size = Integer.parseInt(br.readLine());
            String[] arrStr = br.readLine().split("\\s+");
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(arrStr[i]);
            }
            System.out.println(minJumps(arr));
        }
    }

    static int minJumps(int[] arr) {
        // your code here
        if (arr.length == 1) {
            return 0;
        }

        if (arr[0] == 0) {
            return -1;
        }

        int s = arr[0], i = 1, j = 1;
        int k = s;

        while (i < j) {
            if (i == arr.length - 1) {
                return j;
            }

            k = Math.max(k, i + arr[i]);
            s--;
            if (s == 0) {
                if (k <= i) {
                    return -1;
                }

                s = k - i;
                j++;
            }
            i++;
        }
        return -1;
    }
}
