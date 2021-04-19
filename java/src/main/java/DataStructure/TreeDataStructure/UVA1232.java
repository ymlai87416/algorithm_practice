package DataStructure.TreeDataStructure;

/**
 url: https://onlinejudge.org/external/2/297.pdf
 level: level 4
 solution: min max segment tree

 #segmentTree #segmentTreeLazyUpdate

 **/

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class UVA1232 {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\DataStructure\\TreeDataStructure\\UVA1232.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static boolean debugflag = false;
    private static void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    static class SegmentTree { // the segment tree is stored like a heap array
        //store the min value
        private int[] minValue =  new int[100001*4];
        private int[] maxValue = new int[100001*4];
        private boolean[] marked = new boolean[100001*4];
        //orignal value
        //private int[] A;
        private int n;

        private int left(int p) {
            return p << 1;
        } // same as binary heap operations

        private int right(int p) {
            return (p << 1) + 1;
        }

        private void build(int p, int L, int R) { // O(n)
            marked[p] = false;
            if (L == R) {// as L == R, either one is fine
                minValue[p] = 0;
                maxValue[p] = 0;
            }
            else { // recursively compute the values
                build(left(p), L, (L + R) / 2);
                build(right(p), (L + R) / 2 + 1, R);

                minValue[p]= Math.min(minValue[left(p)], minValue[right(p)]);
                maxValue[p]= Math.max(maxValue[left(p)], maxValue[right(p)]);
            }
        }

        private void push(int v){
            if(marked[v]){
                minValue[left(v)] = minValue[v];
                minValue[right(v)] = minValue[v];

                if(maxValue[left(v)] < minValue[left(v)])
                    maxValue[left(v)] = minValue[left(v)];
                if(maxValue[right(v)] < minValue[right(v)])
                    maxValue[right(v)] = minValue[right(v)];

                marked[left(v)] = true; marked[right(v)] =true;
                marked[v] = false;
            }
        }

        public void update(int i, int j, int val){
            update(1, 0, n-1, i, j, val);
        }

        private void update(int p, int L, int R, int i, int j, int val){
            if (L == R) {// as L == R, either one is fine

                if(val >= maxValue[p]){
                    minValue[p] = val;
                    maxValue[p] =val;
                    ans += 1;
                    debug("update from " + i + ": " + val);
                }
            }
            else { // recursively compute the values
                if(L == i && R == j ) {
                    //only update when the current value is bigger than  min
                    if(val >= maxValue[p]){
                        maxValue[p] = val;
                        minValue[p] = val;

                        marked[p]=true;
                        //this will contribute to answer.
                        ans += (j-i+1);
                        debug("update from " + i + " to " + j + " (total) " + (j-i+1) + ": " + val);
                    }
                    else if(minValue[p] < val && maxValue[p] > val) {
                        push(p);
                        update(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2), val);
                        update(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j, val);

                        maxValue[p] = Math.max(maxValue[left(p)], maxValue[right(p)]);
                        minValue[p] = Math.min(minValue[left(p)], minValue[right(p)]);
                    }
                }
                else {
                    push(p);

                    if (L <= i && i <= (L + R) / 2)
                        update(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2), val);
                    if ((L + R) / 2 + 1 <= j && j <= R)
                        update(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j, val);

                    maxValue[p] = Math.max(maxValue[left(p)], maxValue[right(p)]);
                    minValue[p] = Math.min(minValue[left(p)], minValue[right(p)]);
                }
            }
        }

        public SegmentTree(/*int[] A,*/ int size) {
            //this.A = A;
            n = size;
            Arrays.fill(marked, false);
            build(1, 0, n - 1); // recursive build
        }

        /*
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

         */
    }

    static int ans = 0;
    private int solve(int N, int[] l, int[] r, int[] h) {
        SegmentTree st = new SegmentTree(100001);
        ans = 0;
        for (int i = 0; i < N; i++) {
            st.update(l[i], r[i]-1, h[i]);
        }

        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            //out.print("Case #" + i + ": ");
            int n= sc.nextInt();
            int[] l = new int[n];
            int[] r = new int[n];
            int[] h = new int[n];
            for (int j = 0; j < n; j++) {
                l[j] = sc.nextInt();
                r[j] = sc.nextInt();
                h[j] = sc.nextInt();
            }
            System.out.println(solve(n, l, r, h));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA1232().run();
    }


}
