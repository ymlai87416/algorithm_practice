package DataStructure.TreeDataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Tom on 9/4/2020.
 *
 * Solution to verify Segment tree
 */
public class UVA11235 {

    static class SegmentTree { // the segment tree is stored like a heap array
        private int[] st = new int[200001*4];
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

        public SegmentTree(int[] A) {
            this.A = A;
            n = A.length;
            Arrays.fill(st, 0);
            build(1, 0, n - 1); // recursive build
        }

        public int rmq(int i, int j) {
            return rmq(1, 0, n - 1, i, j);
        } // overloading
    }

    static int[] freq = new int[200001];

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = sc.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            if (a == 0)
                break;

            int b = Integer.parseInt(st.nextToken());

            Arrays.fill(freq, 0);
            s = sc.readLine();
            st = new StringTokenizer(s);
            int c=-1;
            ArrayList<int[]> startEnd = new ArrayList<>();
            int prevC = -1, start=0;
            for (int i = 0; i < a; ++i){
                c = Integer.parseInt(st.nextToken());
                freq[c+100000]++;

                if(c !=prevC){
                    startEnd.add(new int[]{prevC, start, i-1});
                    prevC = c;
                    start = i;
                }
            }
            startEnd.add(new int[]{c, start, a-1});

            SegmentTree stree = null;
            if(b > 0) {
                stree = new SegmentTree(freq);
            }

            int p, q, head, tail, mid, L, R;
            for(int i=0; i<b; ++i){
                //do the query, break it down to head query, middle query, and tail query
                s = sc.readLine();
                st = new StringTokenizer(s);
                p = Integer.parseInt(st.nextToken())-1;
                q = Integer.parseInt(st.nextToken())-1;

                int[] pFreq = searchPos(startEnd, p);
                int[] qFreq = searchPos(startEnd, q);
                int result;
                if(pFreq[0] != qFreq[0]) {
                    int headFreq = pFreq[2] - p + 1;
                    int tailFreq = q - qFreq[1] + 1;
                    int midFreq = 0;
                    if (pFreq[0] + 1 <= qFreq[0] - 1)
                        midFreq = freq[stree.rmq(pFreq[0] + 100000 + 1, qFreq[0] + 100000 - 1)];
                    result = Math.max(headFreq, Math.max(midFreq, tailFreq));
                }
                else
                    result = q-p+1;
                System.out.println(result);
            }
        }
    }

    public static int[] searchPos(ArrayList<int[]> arr, int pos){
        int l = 0, h= arr.size()-1;
        while(l < h){
            int mid = l+ (h-l)/2;
            if(pos >= arr.get(mid)[1] && pos <= arr.get(mid)[2]){
                return arr.get(mid);
            }
            else if(pos < arr.get(mid)[1]){
                h = mid-1;
            }
            else
                l = mid+1;
        }
        return arr.get(l); //cannot be here
    }


}
