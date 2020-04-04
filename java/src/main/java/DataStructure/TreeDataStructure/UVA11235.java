package DataStructure.TreeDataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Created by Tom on 15/5/2016.
 *
 * TLE solution.
 */
public class UVA11235 {

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
                st[p] = L;
            else { // recursively compute the values
                build(left(p), L, (L + R) / 2);
                build(right(p), (L + R) / 2 + 1, R);
                int p1 = st[left(p)], p2 = st[right(p)];
                st[p]= (A[p1] >= A[p2]) ? p1 : p2;
            }
        }

        private int rmq(int p, int L, int R, int i, int j) { // O(log n)
            if (i > R || j < L) return -1; // current segment outside query range
            if (L >= i && R <= j) return st[p]; // inside query range
// compute the min position in the left and right part of the interval
            int p1 = rmq(left(p), L, (L + R) / 2, i, j);
            int p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);
            if (p1 == -1) return p2; // if we try to access segment outside query
            if (p2 == -1) return p1; // same as above
            return (A[p1] >= A[p2]) ? p1 : p2; // as in build routine
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

    static int[] freq = new int[100001];
    static TreeMap<Integer, Integer> right = new TreeMap<>();
    static TreeMap<Integer, Integer> left = new TreeMap<>();
    static int[] num = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        SegmentTree stree = new SegmentTree(freq, 1);

        while (true) {
            String s = sc.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            if (a == 0)
                break;

            int b = Integer.parseInt(st.nextToken());

            s = sc.readLine();
            st = new StringTokenizer(s);
            for (int i = 0; i < a; ++i){
                num[i] = Integer.parseInt(st.nextToken());
            }

            right.clear();
            left.clear();

            int cnt = 1;
            int leftIdx = 0;
            for(int i=1; i<a; ++i){
                if(num[i] != num[i-1]){
                    Arrays.fill(freq, leftIdx, i, cnt);
                    left.put(num[i-1], leftIdx);
                    right.put(num[i-1], i-1);

                    leftIdx = i;
                    cnt = 1;
                } else
                    cnt++;
            }
            Arrays.fill(freq, leftIdx, a, cnt);
            left.put(num[a-1], leftIdx);
            right.put(num[a-1], a-1);

            stree.n = a;
            if(b > 0) stree.build(1, 0, a - 1);

            int p, q, head, tail, mid, L, R;
            for(int i=0; i<b; ++i){
                //do the query, break it down to head query, middle query, and tail query
                s = sc.readLine();
                st = new StringTokenizer(s);
                p = Integer.parseInt(st.nextToken());
                q = Integer.parseInt(st.nextToken());

                p--; q--;

                head = Math.min(right.get(num[p]), q) - p+1;
                tail = q - Math.max(left.get(num[q]), p) + 1;

                L = right.get(num[p])+1;
                R = left.get(num[q]) -1;
                if(L < R)
                    mid = freq[stree.rmq(L, R)];   //Segment tree return only index.
                else mid = 0;

                System.out.println(Math.max(head, Math.max(mid, tail)));
            }
        }

    }
}
