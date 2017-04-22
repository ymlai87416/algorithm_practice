package GoogleCodeJam.Y2015.Round1A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 22/4/2017.
 */
public class C {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "C-large-practice (3)";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static class Point implements Comparable<Point>{
        long x, y;
        public Point() { x = y = 0; }
        public Point(long _x, long _y){
            x = _x;
            y = _y;
        }

        @Override
        public int compareTo(Point o) {
            if (x == o.x) // useful for sorting
                return x < o.x ? -1 : (x == o.x? 0: 1); // first criteria , by x-coordinate
            return y < o.y ? -1 : (y == o.y ? 0: 1);
        }
    }; // second criteria, by y-coordinate

    long cross(Vec a, Vec b) { return a.x * b.y - a.y * b.x; }

    static Vec toVec(Point a, Point b) { // convert 2 points to vector a->b
        return new Vec(b.x - a.x, b.y - a.y); }


    static class Vec { long x, y; // name: ‘vec’ is different from STL vector
        public Vec(long _x, long _y) {
            x = _x;
            y = _y;
        }
    };

    // note: to accept collinear points, we have to change the ‘> 0’
    // returns true if point r is on the left side of line pq
    private boolean ccw(Point p, Point q, Point r) {
        long a = cross(toVec(p, q), toVec(p, r));
        return  a > 0;
    }

    private boolean cw(Point p, Point q, Point r){
        long a = cross(toVec(p, q), toVec(p, r));
        if(a == 0)
            return false;

        return !ccw(p, q, r);
    }

    private void solveSmall(int N, List<Point> trees) {
        int ans = 0;

        for(int i=0; i<N; ++i){
            ans = Integer.MAX_VALUE;
            Point u = trees.get(i);

            System.out.format("%d %d\n", u.x, u.y);

            for(int j=0; j<N; ++j){
                if(i == j) continue;
                int tans = 0;

                Point v = trees.get(j);

                //System.out.format("\t%d %d\n", v.x, v.y);

                for(int k=0; k<N; ++k){
                    if(k == i || k == j) continue;
                    Point r = trees.get(k);

                    if(cw(v,u,r)) { //clockwise I don't add
                        //System.out.format("\t\t%d %d Removed!!\n", r.x, r.y);
                        ++tans;
                    }
                    else{
                        //System.out.format("\t\t%d %d\n", r.x, r.y);
                    }
                }

                ans = Math.min(ans, tans);
            }

            if(ans == Integer.MAX_VALUE)
                ans = 0;

            out.println(ans);
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.println("Case #" + i + ":");

            int N = sc.nextInt();

            ArrayList<Point> trees = new ArrayList<>();
            for(int j=0; j<N; ++j){
                trees.add(new Point(sc.nextInt(), sc.nextInt()));
            }

            solveSmall(N, trees);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new C().run();
    }
}
