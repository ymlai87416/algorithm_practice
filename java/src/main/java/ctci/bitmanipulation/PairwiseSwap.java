package ctci.bitmanipulation;

import DataStructure.JavaPriorityQueue.UVA1203;

public class PairwiseSwap {
    public int pairwiseSwap(int num){
        //4 bit even = 1010 = A, odd = 0101 5
        int oddmask = 0x55555555;
        int evenmask = 0xAAAAAAAA;

        int r = ((num & oddmask) << 1) | ( (num & evenmask) >>> 1);

        return r;
    }


    public static void main(String[] args) {
        //int N = 0x0d92ffa3;
        int N = 6;
        PairwiseSwap test = new PairwiseSwap();
        int M = test.pairwiseSwap(N);
        System.out.println(Integer.toString(N, 2));
        System.out.println(Integer.toString(M, 2));
    }
}
