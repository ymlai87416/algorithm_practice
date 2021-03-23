package DataStructure.TreeDataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVA12086 {
    //this tree start from 1. rsq(a, b) a, b are inclusive
    static
    class FenwickTree {
        private int[] ft; // recall that vi is: typedef vector<int> vi;
        public FenwickTree(int n) { ft = new int[n+1]; } // init n + 1 zeroes
        private int LSOne(int S){ return S & -S;}
        int rsq(int b) { // returns RSQ(1, b)
            int sum = 0; for (; b != 0; b -= LSOne(b)) sum += ft[b];
            return sum; } // note: LSOne(S) (S & (-S))
        int rsq(int a, int b) { // returns RSQ(a, b)
            return rsq(b) - (a == 1 ? 0 : rsq(a - 1)); }
        // adjusts value of the k-th element by v (v can be +ve/inc or -ve/dec)
        void adjust(int k, int v) { // note: n = ft.size() - 1
            for (; k < ft.length; k += LSOne(k)) ft[k] += v; }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int nc = 1;
        while(true) {
            int N = Integer.parseInt(sc.readLine());
            if(N==0)
                return;

            if(nc != 1) System.out.println();
            System.out.println("Case " + nc++ + ":");

            int[] nums = new int[200001];

            FenwickTree ft = new FenwickTree(N);
            for (int i = 0; i < N; ++i) {
                nums[i] = Integer.parseInt(sc.readLine());
                ft.adjust(i+1, nums[i]);
            }

            while (true) {
                String com = sc.readLine();
                if (com.compareTo("END") == 0)
                    break;
                String[] coma = com.split(" ");
                if (coma[0].compareTo("M") == 0) {
                    int start=  Integer.parseInt(coma[1]);
                    int end = Integer.parseInt(coma[2]);
                    System.out.println(ft.rsq(start, end));
                } else if (coma[0].compareTo("S") == 0) {
                    int pos = Integer.parseInt(coma[1]);
                    int newVal = Integer.parseInt(coma[2]);
                    ft.adjust(pos, newVal-nums[pos-1]);
                    nums[pos-1] = newVal;
                }
            }
        }
    }
}
