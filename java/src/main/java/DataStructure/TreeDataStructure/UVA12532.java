package DataStructure.TreeDataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 url: https://onlinejudge.org/external/125/12532.pdf
 level:
 solution: no need to multiply the actual value +1/-1/0 will do.

 #segmentTree #rmq

 **/

public class UVA12532 {
    static int[] N = new int[100001];

    static class SegmentTree { // the segment tree is stored like a heap array
        private int[] st = new int[100001*4];
        private int[] A;
        private int n;

        private int left(int p) {
            return p << 1;
        } // same as binary heap operations

        private int right(int p) {
            return (p << 1) + 1;
        }

        private void build(int p, int L, int R) { // O(n)
            if (L == R) // as L == R, either one is fine
                st[p] = A[L];
            else { // recursively compute the values
                build(left(p), L, (L + R) / 2);
                build(right(p), (L + R) / 2 + 1, R);
                int p1 = st[left(p)], p2 = st[right(p)];
                st[p]= p1 * p2;
            }
        }

        public void update(int p, int val){
            update(1, p, val, 0, n-1);
        }

        public void update(int p, int idx, int val, int L, int R){
            if (L == R) // as L == R, either one is fine
                st[p] = val;
            else { // recursively compute the values
                if(L <= idx && idx <= (L+R)/2) update(left(p), idx, val, L, (L+R)/2);
                else if ((L+R)/2+1 <= idx && idx<= R)update(right(p), idx, val, (L+R)/2+1, R);
                int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
                st[p]= p1 * p2;
            }
        }

        private int rmq(int p, int L, int R, int i, int j) { // O(log n)
            if (i > R || j < L) return Integer.MIN_VALUE; // current segment outside query range
            if (L >= i && R <= j) return st[p]; // inside query range
            // compute the min position in the left and right part of the interval
            int p1 = rmq(left(p), L, (L + R) / 2, i, j);
            int p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);
            if (p1 == Integer.MIN_VALUE) return p2; // if we try to access segment outside query
            if (p2 == Integer.MIN_VALUE) return p1; // same as above
            return p1 * p2; // as in build routine
        }

        public SegmentTree(int[] A, int size) {
            this.A = A;
            n = size;
            //Arrays.fill(st, 0);
            build(1, 0, n - 1); // recursive build
        }

        public int rmq(int i, int j) {
            return rmq(1, 0, n - 1, i, j);
        } // overloading
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

        String s;
        StringTokenizer st;
        while(true){
            s = bw.readLine();
            if(s == null) break;
            st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            s = bw.readLine();
            st = new StringTokenizer(s);
            for(int i=0; i<a; ++i){
                int n = Integer.parseInt(st.nextToken());
                N[i] = n < 0 ? -1 : n== 0? 0: 1;
            }

            SegmentTree stree = new SegmentTree(N, a);

            for(int i=0; i<b; ++i){
                s = bw.readLine();
                st = new StringTokenizer(s);

                String op = st.nextToken();
                int op1 = Integer.parseInt(st.nextToken());
                int op2 = Integer.parseInt(st.nextToken());

                if(op.compareTo("C") == 0){
                    stree.update(op1-1, op2 < 0 ? -1 : op2== 0? 0: 1);
                } else if(op.compareTo("P") == 0){
                    int rr = stree.rmq(op1-1, op2-1);
                    System.out.print(rr >0 ? "+" : rr ==0 ? "0" : "-");
                }
            }
            System.out.println();
        }
    }
}
