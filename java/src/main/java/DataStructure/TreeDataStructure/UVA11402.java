package DataStructure.TreeDataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * Created by Tom on 16/5/2016.
 *
 * TLE. What is lazy update?
 */
public class UVA11402 {

    static class SegmentTree { // the segment tree is stored like a heap array
        private int[] st = new int[1024001*4];
        private boolean[] flip = new boolean[1024001*4];
        private Integer[] set = new Integer[1024001*4];
        private BitSet A;
        private int n;

        private int left(int p) {
            return p << 1;
        } // same as binary heap operations

        private int right(int p) {
            return (p << 1) + 1;
        }

        private void build(int p, int L, int R) { // O(n)
            if (L == R) // as L == R, either one is fine
                st[p] = A.get(L) ? 1 : 0;
            else { // recursively compute the values
                build(left(p), L, (L + R) / 2);
                build(right(p), (L + R) / 2 + 1, R);
                int p1 = st[left(p)], p2 = st[right(p)];
                st[p]= p1 + p2;
            }
        }

        public void updateRange(int i, int j, int val){
            updateRange(1, 0, n-1, i, j, val, true);
        }

        public void flipRange(int i, int j){
            flipRange(1, 0, n-1, i, j, true);
        }

        private void updateRange(int p, int L, int R, int i, int j, int val, boolean lazy){
            if (L == R) // as L == R, either one is fine
                st[p] = val;

            else { // recursively compute the values
                if(L == i && R == j && lazy) {
                    set[p] = val;
                    flip[p] = false;
                }
                else {
                    if(lazy && hasPendingChange(p))
                        populateUpdate(p, L, R, false);

                    if (L <= i && i <= (L + R) / 2)
                        updateRange(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2), val, lazy);
                    if ((L + R) / 2 + 1 <= j && j <= R)
                        updateRange(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j, val, lazy);
                    int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
                    st[p] = p1 + p2;
                }
            }
        }

        private void flipRange(int p, int L, int R, int i, int j, boolean lazy){
            if (L == R) // as L == R, either one is fine
                st[p] = 1-st[p];
            else { // recursively compute the values
                if(L == i && R == j && lazy) {
                    if(set[p] == null)
                        flip[p] = !flip[p];
                    else {
                        set[p] = 1 - set[p];
                    }
                }
                else {
                    if(lazy && hasPendingChange(p))
                        populateUpdate(p, L, R, false);

                    if (L <= i && i <= (L + R) / 2)
                        flipRange(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2), lazy);
                    if ((L + R) / 2 + 1 <= j && j <= R)
                        flipRange(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j, lazy);
                    int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
                    st[p] = p1 + p2;
                }
            }
        }

        private void populateUpdate(int p, int L, int R, boolean recursive){
            //System.out.println("Trigger update: " + L + " " + R);
            boolean flush = false;
            if(set[p] != null) {
                updateRange(p, L, R, L, R, set[p], false);
                flush = true;
            }
            else if(flip[p]) {
                flipRange(p, L, R, L, R, false);
                flush = true;
            }

            set[p] = null;
            flip[p] = false;

            if(recursive && !flush){
                if(L == R) return;
                populateUpdate(left(p), L, (L + R) / 2, recursive);
                populateUpdate(right(p), (L + R) / 2 + 1, R, recursive);
                int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
                st[p] = p1 + p2;
            }
        }

        private boolean hasPendingChange(int p){
            return set[p] != null || flip[p];
        }

        private int rmq(int p, int L, int R, int i, int j, boolean flushed) { // O(log n)
            if (i > R || j < L) return -1; // current segment outside query range
            if(hasPendingChange(p)) {
                populateUpdate(p, L, R, true);
                flushed = true;
            }
            if (L >= i && R <= j) {
                if(!flushed) populateUpdate(p, L, R, true);
                return st[p]; // inside query range
            }
            // compute the min position in the left and right part of the interval
            int p1 = rmq(left(p), L, (L + R) / 2, i, j, flushed);
            int p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j, flushed);
            if (p1 == -1) return p2; // if we try to access segment outside query
            if (p2 == -1) return p1; // same as above
            return p1 + p2; // as in build routine
        }

        public SegmentTree(BitSet A, int size) {
            this.A = A;
            n = size;
            Arrays.fill(flip, false);
            Arrays.fill(set, null);
            build(1, 0, n - 1); // recursive build
        }

        public int rmq(int i, int j) {
            return rmq(1, 0, n - 1, i, j, false);
        }

        public void debugTree(int p, int L, int R){
            if (L == R) // as L == R, either one is fine
                System.out.print(st[p]);
            else { // recursively compute the values
                System.out.print("(");
                debugTree(left(p), L, (L+R)/2);
                System.out.print(st[p]);
                debugTree(right(p), (L+R)/2+1, R);
                System.out.print(")");
            }
        }

        public void debugTree2(int cnt){
            for(int i=0; i<cnt*4; ++i){
                System.out.print(st[i]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        int nc = Integer.parseInt(sc.readLine());

        for(int ci=0; ci<nc; ++ci){

            BitSet bs = new BitSet();

            int pcnt = 0;
            int m = Integer.parseInt(sc.readLine());
            for(int i=0; i<m; ++i){
                int t = Integer.parseInt(sc.readLine());
                String s = sc.readLine();

                for(int j=0; j<t; ++j){
                    for(int k=0; k<s.length(); ++k)
                        bs.set(pcnt++, s.charAt(k) == '1' ? true : false);
                }
            }

            int q = Integer.parseInt(sc.readLine());
            StringTokenizer st;

            System.out.format("Case %d:\n", ci+1);

            SegmentTree stree = new SegmentTree(bs, pcnt);
            //stree.debugTree2(pcnt);

            int scnt = 0;
            for(int i=0; i<q; ++i){
                String s = sc.readLine();
                st = new StringTokenizer(s);

                String op = st.nextToken();
                int op1 = Integer.parseInt(st.nextToken());
                int op2 = Integer.parseInt(st.nextToken());

                if(op.compareTo("F") == 0){
                    stree.updateRange(op1, op2, 1);
                    //stree.debugTree2(pcnt); System.out.println();
                } else if(op.compareTo("E") == 0){
                    stree.updateRange(op1, op2, 0);
                    //stree.debugTree2(pcnt); System.out.println();
                } else if(op.compareTo("I") == 0){
                    stree.flipRange(op1, op2);
                    //stree.debugTree2(pcnt);System.out.println();
                } else if(op.compareTo("S") == 0){
                    int r = stree.rmq(op1, op2);
                    System.out.format("Q%d: %d\n", ++scnt, r);
                }
            }

        }
    }
}