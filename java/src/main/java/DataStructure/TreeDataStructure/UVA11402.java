package DataStructure.TreeDataStructure;


import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Tom on 16/5/2016.
 *
 * Accept.
 *
 url: https://onlinejudge.org/external/114/11402.pdf
 level:
 solution:

 * #segmentTree  #segmentTreeLazyUpdate
 */

public class UVA11402 {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\DataStructure\\TreeDataStructure\\UVA11402.in";
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

    static class SegmentTree2 { // the segment tree is stored like a heap array
        private int[] st = new int[1024001*4];
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
            updateRange(1, 0, n-1, i, j, val);
        }

        public void flipRange(int i, int j){
            flipRange(1, 0, n-1, i, j);
        }

        private void updateRange(int p, int L, int R, int i, int j, int val){
            if (L == R) // as L == R, either one is fine
                st[p] = val;
            else { // recursively compute the values
                if (L <= i && i <= (L + R) / 2)
                    updateRange(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2), val);
                if ((L + R) / 2 + 1 <= j && j <= R)
                    updateRange(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j, val);
                int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
                st[p] = p1 + p2;
            }
        }

        private void flipRange(int p, int L, int R, int i, int j){
            if (L == R) // as L == R, either one is fine
                st[p] = 1-st[p];
            else { // recursively compute the values
                if (L <= i && i <= (L + R) / 2)
                    flipRange(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2));
                if ((L + R) / 2 + 1 <= j && j <= R)
                    flipRange(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j);
                int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
                st[p] = p1 + p2;
            }
        }

        private int rmq(int p, int L, int R, int i, int j) { // O(log n)
            if (i > R || j < L) return -1; // current segment outside query range

            if (L >= i && R <= j) {
                return st[p];
            }
            // compute the min position in the left and right part of the interval
            int p1 = rmq(left(p), L, (L + R) / 2, i, j);
            int p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);
            if (p1 == -1) return p2; // if we try to access segment outside query
            if (p2 == -1) return p1; // same as above
            return p1 + p2; // as in build routine
        }

        public SegmentTree2(BitSet A, int size) {
            this.A = A;
            n = size;
            build(1, 0, n - 1); // recursive build
        }

        public int rmq(int i, int j) {
            return rmq(1, 0, n - 1, i, j);
        }

        private boolean debugflag = false;
        public void debugTree(int p, int L, int R, int level){
            if(!debugflag) return;
            if (L == R) // as L == R, either one is fine
                System.out.println(nTab(level) +"(" + st[p] + ")");
            else { // recursively compute the values

                System.out.print(nTab(level) + "(");
                System.out.println(st[p]);

                debugTree(left(p), L, (L+R)/2, level+1);

                debugTree(right(p), (L+R)/2+1, R, level+1);
                System.out.println(nTab(level) + ")");
            }
        }

        private String nTab(int n){
            String s = "";
            for (int i = 0; i < n; i++) {
                s += "\t";
            }
            return s;
        }

        public void debugTree2(int cnt){
            for(int i=0; i<cnt*4; ++i){
                System.out.print(st[i]);
            }
            System.out.println();
        }
    }

    static class SegmentTree { // the segment tree is stored like a heap array
        private int[] st = new int[1024001*4];
        private boolean[] flip = new boolean[1024001*4];
        private Integer[] set = new Integer[1024001*4];
        //if marked this node, the st value of child node are not valid.
        private boolean[] marked = new boolean[1024001*4];
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
                marked[p] =false;
            }
        }

        //update child node st value and child node flip and set value
        private void push(int p, int L, int R){
            if(marked[p]){
                //bugs here: e.g. node 28 is marked in 1 command , and node 14 is marked in command 2, if propagate, which overwrite command 1.
                //bugs 2: when there is flip, please flip the st
                int mid = (L + R) / 2;

                if(set[p] != null){
                    set[left(p)] = set[p];
                    flip[left(p)] = false;
                    set[right(p)] = set[p];
                    flip[right(p)] = false;
                    st[left(p)] = (mid-L+1) * set[left(p)];
                    st[right(p)] = (R-mid-1+1) * set[right(p)];
                } else if(flip[p]){
                    if(set[left(p)]!=null) {
                        set[left(p)] = 1 - set[left(p)];
                        flip[left(p)] = false;
                        st[left(p)] = (mid-L+1) * set[left(p)];
                    }
                    else {
                        flip[left(p)] = !flip[left(p)];
                        st[left(p)] = (mid - L + 1) - st[left(p)];
                    }
                    if(set[right(p)]!=null) {
                        set[right(p)] = 1 - set[right(p)];
                        flip[right(p)] = false;
                        st[right(p)] = (R-mid-1+1) * set[right(p)];
                    }
                    else {
                        flip[right(p)] = !flip[right(p)];
                        st[right(p)] = (R - mid-1 + 1) - st[right(p)];
                    }
                }

                marked[left(p)] = true; marked[right(p)] =true;

                st[p] = st[left(p)] + st[right(p)];

                marked[p] = false; flip[p] = false; set[p] = null;
            }
        }


        public void updateRange(int i, int j, int val){
            updateRange(1, 0, n-1, i, j, val);
        }

        public void flipRange(int i, int j){
            flipRange(1, 0, n-1, i, j);
        }

        private void updateRange(int p, int L, int R, int i, int j, int val){
            if (L == R) {// as L == R, either one is fine
                //bug3: end point also need init
                set[p] = val;
                flip[p] = false;
                st[p] = val;
            }
            else { // recursively compute the values
                if(L >= i && R <= j) {
                    set[p] = val;
                    flip[p] = false;
                    marked[p] = true;
                    st[p] = (R-L+1) * set[p];

                    //updateNodeValue(p, L, R);
                }
                else {
                    push(p, L, R);

                    if (L <= i && i <= (L + R) / 2)
                        updateRange(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2), val);
                    if ((L + R) / 2 + 1 <= j && j <= R)
                        updateRange(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j, val);
                    int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
                    st[p] = p1 + p2;
                }
            }
        }

        private void flipRange(int p, int L, int R, int i, int j){
            if (L == R) {// as L == R, either one is fine
                //bug3: end point also need init
                if(set[p] == null) {
                    flip[p] = !flip[p];
                    st[p] = 1 - st[p];
                }
                else {
                    set[p] = 1 - set[p];
                    flip[p] = false;
                    st[p] = 1 - st[p];
                }

            }
            else { // recursively compute the values
                if(L >= i && R <= j) {

                    if(set[p] == null) {
                        flip[p] = !flip[p];
                        st[p] = (R - L + 1) - st[p];
                    }
                    else {
                        set[p] = 1 - set[p];
                        flip[p] = false;
                        st[p] = (R-L+1) * set[p];
                    }
                    marked[p] = true;

                    //updateNodeValue(p, L, R);
                }
                else {
                    push(p, L, R);

                    if (L <= i && i <= (L + R) / 2)
                        flipRange(left(p), L, (L + R) / 2, i, Math.min(j, (L + R) / 2));
                    if ((L + R) / 2 + 1 <= j && j <= R)
                        flipRange(right(p), (L + R) / 2 + 1, R, Math.max(i, (L + R) / 2 + 1), j);
                    int p1 = st[left(p)], p2 = st[right(p)];          //Never a node with only left or right child.
                    st[p] = p1 + p2;
                }
            }
        }


        private int rmq(int p, int L, int R, int i, int j) { // O(log n)
            if (i > R || j < L) return -1; // current segment outside query range

            if (L >= i && R <= j) {
                return st[p];
            }
            push(p, L, R);
            // compute the min position in the left and right part of the interval
            int p1 = rmq(left(p), L, (L + R) / 2, i, j);
            int p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);
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
            return rmq(1, 0, n - 1, i, j);
        }

        private boolean debugflag = false;
        public void debugTree(int p, int L, int R, int level){
            if(!debugflag) return;
            if (L == R) // as L == R, either one is fine
                System.out.println(nTab(level) +"(" + st[p] + ")");
            else { // recursively compute the values

                System.out.print(nTab(level) + "(");
                if(marked[p])System.out.print("+");
                System.out.println(st[p]);

                debugTree(left(p), L, (L+R)/2, level+1);

                debugTree(right(p), (L+R)/2+1, R, level+1);
                System.out.println(nTab(level) + ")");
            }
        }

        private String nTab(int n){
           String s = "";
            for (int i = 0; i < n; i++) {
                s += "\t";
            }
            return s;
        }

        public void debugTree2(int cnt){
            for(int i=0; i<cnt*4; ++i){
                System.out.print(st[i]);
            }
            System.out.println();
        }
    }

    private void compareTree(SegmentTree t1, SegmentTree2 t2, int p, int L, int R){
        int wrong = t1.st[p];
        int corr = t2.st[p];
        if(wrong != corr)
            debug("Error find in node: " +p + " [" + L + " " + R + "]: w="+wrong + " c=" +corr);
        if(L == R){
            return;
        }
        else{
            if(!t1.marked[p]) {
                compareTree(t1, t2, p * 2, L, (L + R) / 2);
                compareTree(t1, t2, p * 2 + 1, (L + R) / 2 + 1, R);
            }
        }
    }


    int N;
    BitSet priates;
    String[] queries;
    private void solve() {
        SegmentTree stree = new SegmentTree(priates, N);
        //SegmentTree2 stree2 = new SegmentTree2(priates, N);

        int scnt = 0;
        for (int i = 0; i < queries.length; i++) {
            debug("Q: " + queries[i]);
            StringTokenizer st = new StringTokenizer(queries[i]);

            String op = st.nextToken();
            int op1 = Integer.parseInt(st.nextToken());
            int op2 = Integer.parseInt(st.nextToken());

            debugQ = i;
            if(op.compareTo("F") == 0){
                stree.updateRange(op1, op2, 1);
                //stree2.updateRange(op1, op2, 1);
                //stree.debugTree(1, 0, priates.size()-1, 0);
                //compareTree(stree, stree2, 1, 0, N-1);
            } else if(op.compareTo("E") == 0){
                stree.updateRange(op1, op2, 0);
                //stree2.updateRange(op1, op2, 0);
                //stree.debugTree(1, 0, priates.size()-1, 0);
                //compareTree(stree, stree2, 1, 0, N-1);
            } else if(op.compareTo("I") == 0){
                stree.flipRange(op1, op2);
                //stree2.flipRange(op1, op2);
                //stree.debugTree(1, 0, priates.size()-1, 0);
                //compareTree(stree, stree2, 1, 0, N-1);
            } else if(op.compareTo("S") == 0){
                int r = stree.rmq(op1, op2);
                System.out.format("Q%d: %d\n", ++scnt, r);
            }
        }
    }

    static int debugQ;

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= t; i++) {
            out.print("Case " + i + ":\n");
            //int N = Integer.parseInt(sc.nextLine());
            int M = Integer.parseInt(sc.nextLine());
            BitSet priates = new BitSet(N);

            int curptr = 0;
            for (int j = 0; j < M; j++) {
                int T = Integer.parseInt(sc.nextLine());
                String e = sc.nextLine();
                for (int k = 0; k < T; k++) {
                    for (int l = 0; l < e.length(); l++) {
                        if(e.charAt(l) == '1')
                            priates.set(curptr);
                        curptr++;
                    }   
                }
            }
            int Q =Integer.parseInt(sc.nextLine());
            String[] queries = new String[Q];
            for (int j = 0; j < Q; j++) {
                queries[j] = sc.nextLine();
            }

            this.N = curptr;
            this.priates = priates;
            this.queries = queries;

            solve();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA11402().run();
    }
}