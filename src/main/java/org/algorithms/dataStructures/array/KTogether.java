package org.algorithms.dataStructures.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class KTogether {

    // Driver code
    public static void main(String[] args) throws IOException {
        // Taking input using buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcases = Integer.parseInt(br.readLine());

        // looping through all testcases
        while (testcases-- > 0) {
            String line = br.readLine();
            String[] element = line.trim().split("\\s+");
            int sizeOfArray = Integer.parseInt(element[0]);

            int[] arr = new int[sizeOfArray];

            line = br.readLine();
            String[] elements = line.trim().split("\\s+");
            for (int i = 0; i < sizeOfArray; i++) {
                arr[i] = Integer.parseInt(elements[i]);
            }
            int K = Integer.parseInt(br.readLine());

            int ans = minSwap(arr, sizeOfArray, K);
            System.out.println(ans);
        }
    }

    // Function for finding maximum and value pair
    public static int minSwap(int[] arr, int n, int k) {
        // Complete the function
        int count = 0;
        for (int i : arr) {
            if (i <= k) {
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        int nCount = 0;
        for (int i = 0; i < count; i++) {
            if (arr[i] > k) {
                nCount++;
            }
        }

        int j = count, res = nCount, i = 0;
        while (j < n) {
            if (arr[i] > k) {
                nCount--;
            }

            if (arr[j] > k) {
                nCount++;
            }

            res = Math.min(res, nCount);
            i++;
            j++;
        }
        return res;
    }
}
