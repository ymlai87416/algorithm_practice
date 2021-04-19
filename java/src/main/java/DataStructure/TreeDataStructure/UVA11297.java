package DataStructure.TreeDataStructure;

/**
 problem: https://onlinejudge.org/external/112/11297.pdf
 level: 4
 solution: AC

 #quadtree #segmentTree #segmentTree2D

 **/

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UVA11297 {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\DataStructure\\TreeDataStructure\\UVA11297.in";
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

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    static class SegmentTree { // the segment tree is stored like a heap array
        private int[][] maxValue = new int[501*4][501*4];
        private int[][] minValue = new int[501*4][501*4];
        private int[][] A;
        private int n;
        private int m;

        private int left(int p) {
            return p << 1;
        } // same as binary heap operations

        private int right(int p) {
            return (p << 1) + 1;
        }

        private void buildX(int p, int L, int R) { // O(n)
            if (L != R) { // recursively compute the values
                buildX(left(p), L, (L + R) / 2);
                buildX(right(p), (L + R) / 2 + 1, R);
            }
            buildY(p, L, R, 1, 0, m-1);
        }

        private void buildY(int px, int Lx, int Rx, int py, int Ly, int Ry) { // O(n)
            if (Ly == Ry) // as L == R, either one is fine
                if(Lx == Rx) {
                    maxValue[px][py] = A[Lx][Ly];
                    minValue[px][py] = A[Lx][Ly];
                }
                else {
                    maxValue[px][py] = Math.max(maxValue[left(px)][py], maxValue[right(px)][py]);
                    minValue[px][py] = Math.min(minValue[left(px)][py], minValue[right(px)][py]);
                }
            else { // recursively compute the values
                buildY(px, Lx, Rx, left(py), Ly, (Ly+Ry)/2);
                buildY(px, Lx, Rx, right(py), (Ly+Ry)/2+1, Ry);

                maxValue[px][py] = Math.max(maxValue[px][left(py)], maxValue[px][right(py)]);
                minValue[px][py] = Math.min(minValue[px][left(py)], minValue[px][right(py)]);
            }
        }

        public void update(int x, int y, int val){
            updateX(1, 0, n-1, x, y, val);
        }

        public void updateY(int px, int Lx, int Rx, int py, int Ly, int Ry, int ix, int iy, int val){
            if (Ly == Ry) { // as L == R, either one is fine
                if(Lx == Rx) {
                    maxValue[px][py] = val;
                    minValue[px][py] = val;
                }
                else {
                    maxValue[px][py] = Math.max(maxValue[left(px)][py], maxValue[right(px)][py]);
                    minValue[px][py] = Math.min(minValue[left(px)][py], minValue[right(px)][py]);
                }
            }
            else { // recursively compute the values
                int My = (Ly+Ry)/2;
                if(Ly <= iy && iy <= My) updateY(px, Lx, Rx, left(py), Ly, My, ix, iy, val);
                else if (My+1 <= iy && iy<= Ry)updateY(px, Lx, Rx, right(py), My+1, Ry, ix, iy, val);
                maxValue[px][py] = Math.max(maxValue[px][left(py)], maxValue[px][right(py)]);
                minValue[px][py] = Math.min(minValue[px][left(py)], minValue[px][right(py)]);
            }
        }

        public void updateX(int px, int Lx, int Rx, int ix, int iy, int val){
            if (Lx != Rx) { // recursively compute the values
                int Mx = (Lx + Rx)/2;
                if(Lx <= ix && ix <= Mx) updateX(left(px), Lx, Mx,  ix, iy, val);
                else if (Mx+1 <= ix && ix<= Rx)updateX(right(px), Mx+1, Rx, ix, iy, val);
            }
            updateY(px, Lx, Rx, 1, 0, m-1, ix, iy, val);
        }

        private int[] rmqY(int px, int py, int Ly, int Ry, int ly, int ry) { // O(log n)
            if (ly > ry) return null; // current segment outside query range
            if (Ly == ly && Ry == ry) return new int[]{minValue[px][py], maxValue[px][py]}; // inside query range
            // compute the min position in the left and right part of the interval
            int My = (Ly+Ry)/2;
            int[] p1 = rmqY(px, left(py), Ly, My, ly, Math.min(ry, My));
            int[] p2 = rmqY(px, right(py), My + 1, Ry, Math.max(ly, My+1), ry);
            if (p1 == null) return p2; // if we try to access segment outside query
            if (p2 == null) return p1; // same as above
            return new int[]{Math.min(p1[0], p2[0]), Math.max(p1[1], p2[1])}; // as in build routine
        }

        private int[] rmqX(int px, int Lx, int Rx, int lx, int rx, int ly, int ry) { // O(log n)
            if (lx > rx) return null; // current segment outside query range
            if (Lx == lx && Rx == rx) return rmqY(px, 1, 0, m-1, ly, ry); // inside query range
            // compute the min position in the left and right part of the interval
            int Mx = (Lx+Rx) /2;
            int[] p1 = rmqX(left(px), Lx, Mx, lx, Math.min(rx, Mx), ly, ry);
            int[] p2 = rmqX(right(px), Mx + 1, Rx, Math.max(lx, Mx+1), rx, ly, ry);
            if (p1 == null) return p2; // if we try to access segment outside query
            if (p2 == null) return p1; // same as above
            return new int[]{Math.min(p1[0], p2[0]), Math.max(p1[1], p2[1])}; // as in build routine
        }

        public SegmentTree(int[][] A, int[] size) {
            this.A = A;
            n = size[0];
            m = size[1];
            //Arrays.fill(st, 0);
            buildX(1, 0, n - 1); // recursive build
        }

        public int[] rmq(int lx, int rx, int ly, int ry) {
            return rmqX(1, 0, n - 1, lx, rx, ly, ry);
        } // overloading
    }


    private void solve(int N, int[][] C, String[] command) {

        int[] size = new int[]{N, N};
        SegmentTree tree = new SegmentTree(C, size);

        for (int i = 0; i < command.length; i++) {
            StringTokenizer st=  new StringTokenizer(command[i]);
            String op = st.nextToken();

            if(op.compareTo("q") == 0) {
                int x1 = Integer.parseInt(st.nextToken())-1;
                int y1 = Integer.parseInt(st.nextToken())-1;
                int x2 = Integer.parseInt(st.nextToken())-1;
                int y2 = Integer.parseInt(st.nextToken())-1;

                int[] result = tree.rmq(x1, x2, y1, y2);
                System.out.println(result[1] + " " + result[0] );
            }
            else if(op.compareTo("c") == 0){
                int x = Integer.parseInt(st.nextToken())-1;
                int y = Integer.parseInt(st.nextToken())-1;
                int val = Integer.parseInt(st.nextToken());

                tree.update(x, y, val);
            }
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        //out.print("Case #" + i + ": ");
        int N = sc.nextInt();
        int[][] C = new int[N][N];
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                C[j][k] = sc.nextInt();
            }
        }
        sc.nextLine();
        int Q = Integer.parseInt(sc.nextLine());
        String[] command = new String[Q];
        for (int j = 0; j < Q; j++) {
            command[j] = sc.nextLine();
        }

        solve(N, C, command);
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new UVA11297().run();
    }

}
