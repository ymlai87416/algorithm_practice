package ProblemSolving.BinarySearch;

/**
 problem: https://onlinejudge.org/external/104/10474.pdf
 level:
 solution:

 #binarySearch #binarySearchLowerBound #binarySearchUpperBound #binarySearchEndExclusive

 **/

import java.io.*;
import java.util.Arrays;

public class UVA10474 {

    //uva 10474
    //parameter: [low, hi)
    static int lowerBound(int[] a, int low, int high, int element) {
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (element > a[middle]) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }

    static int upperBound(int[] a, int low, int high, int element) {
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (a[middle] > element) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return low;
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ncase = 1;
        while(true){
            int n, q;
            String line = br.readLine();
            String[] token = line.split(" ");
            n = Integer.parseInt(token[0]);
            q = Integer.parseInt(token[1]);

            if(n==0 && q==0) return;
            System.out.println("CASE# " + ncase+":");
            ncase++;

            int[] nums = new int[n];

            for(int i=0; i<n; ++i){
                nums[i] = Integer.parseInt(br.readLine().trim());
            }

            Arrays.sort(nums);

            for(int i=0; i<q; ++i){
                int qn =  Integer.parseInt(br.readLine().trim());
                int loc = lowerBound(nums, 0, nums.length, qn);
                if(loc < 0 || loc >= n || nums[loc] != qn)
                    System.out.println(qn + " not found");
                else
                    System.out.println(qn +" found at "+ (loc+1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        UVA10474 sol = new UVA10474();
        sol.run();
    }
}
