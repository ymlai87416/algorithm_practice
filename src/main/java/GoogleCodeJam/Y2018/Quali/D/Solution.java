package GoogleCodeJam.Y2018.Quali.D;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by ymlai on 8/4/2018.
 */

class Square{
    double p1_x;    //represent the red (Y axis)
    double p1_y;
    double p2_x;    //represent the green (X axis)
    double p2_y;
    double p3_x;    //represent the blue (Z axis)
    double p3_y;

    double[][] points;

    public Square(){
        points = new double[8][3];

        points[0][0] = -0.5;
        points[0][1] = 0.5;
        points[0][2] = 0.5;

        points[1][0] = -0.5;
        points[1][1] = 0.5;
        points[1][2] = -0.5;

        points[2][0] = 0.5;
        points[2][1] = 0.5;
        points[2][2] = 0.5;

        points[3][0] = 0.5;
        points[3][1] = 0.5;
        points[3][2] = -0.5;

        points[4][0] = -0.5;
        points[4][1] = -0.5;
        points[4][2] = 0.5;

        points[5][0] = -0.5;
        points[5][1] = -0.5;
        points[5][2] = -0.5;

        points[6][0] = 0.5;
        points[6][1] = -0.5;
        points[6][2] = 0.5;

        points[7][0] = 0.5;
        points[7][1] = -0.5;
        points[7][2] = -0.5;
    }

    public double[] getPlaneRedCenter(){
        double[] result = new double[] {0.0, 0.0, 0.0};

        int[] relatedCorner = new int[]{0, 1, 2, 3};
        for(int i=0; i<relatedCorner.length; ++i){
            result[0] += points[relatedCorner[i]][0]/4;
            result[1] += points[relatedCorner[i]][1]/4;
            result[2] += points[relatedCorner[i]][2]/4;
        }

        return result;
    }

    public double[] getPlaneGreenCenter(){
        double[] result = new double[] {0.0, 0.0, 0.0};

        int[] relatedCorner = new int[]{2, 3, 6, 7};
        for(int i=0; i<relatedCorner.length; ++i){
            result[0] += points[relatedCorner[i]][0]/4;
            result[1] += points[relatedCorner[i]][1]/4;
            result[2] += points[relatedCorner[i]][2]/4;
        }

        return result;
    }

    public double[] getPlaneBlueCenter(){
        double[] result = new double[] {0.0, 0.0, 0.0};

        int[] relatedCorner = new int[]{0, 2, 4, 6};
        for(int i=0; i<relatedCorner.length; ++i){
            result[0] += points[relatedCorner[i]][0]/4;
            result[1] += points[relatedCorner[i]][1]/4;
            result[2] += points[relatedCorner[i]][2]/4;
        }

        return result;
    }

    //first to ratote
    public void rotateAlongZAxis(double q){
        for(int i=0; i<8; ++i){
            double x = points[i][0];
            double y = points[i][1];
            double z = points[i][2];

            points[i][0] = x*Math.cos(q)- y*Math.sin(q);
            points[i][1] = x*Math.sin(q)+ y*Math.cos(q);
            points[i][2] = z;
        }
    }

    //second to rotate
    public void rotateAlongXAxis(double q){
        for(int i=0; i<8; ++i){
            double x = points[i][0];
            double y = points[i][1];
            double z = points[i][2];

            points[i][1] = y*Math.cos(q)- z*Math.sin(q);
            points[i][2] = y*Math.sin(q)+ z*Math.cos(q);
            points[i][0] = x;
        }
    }

    public double calculateXZProjectionArea(){
        List<Point> projPoints = new ArrayList<Point>();

        for(int i=0; i<8; ++i){
            projPoints.add(new Point(points[i][0], points[i][2]));
        }

        List<Point> convexHull = CH(projPoints);
        double convexHullArea = area(convexHull);

        return convexHullArea;
    }

    //convex hull code
    static final double EPS = 1e-9;

    static class Point { double x, y; // only used if more precision is needed
        Point() { x = y = 0.0; } // default constructor
        Point(double _x, double _y){
            x =_x;
            y= _y;
        }}; // user-defined

    static class Vec { double x, y; // name: 'vec' is different from STL vector
        Vec(double _x, double _y) {
            x = _x;
            y = _y;
        } };

    static double cross(Vec a, Vec b) { return a.x * b.y - a.y * b.x; }

    static Vec toVec(Point a, Point b) { // convert 2 points to vector a->b
        return new Vec(b.x - a.x, b.y - a.y); }

    // returns true if point r is on the same line as the line pq
    static boolean collinear(Point p, Point q, Point r) {
        return Math.abs(cross(toVec(p, q), toVec(p, r))) < EPS; }

    static boolean angleCmp(Point a, Point b, Point pivot) { // angle-sorting function
        if (collinear(pivot, a, b)) // special case
            return dist(pivot, a) < dist(pivot, b); // check which one is closer
        double d1x = a.x - pivot.x, d1y = a.y - pivot.y;
        double d2x = b.x - pivot.x, d2y = b.y - pivot.y;
        return (Math.atan2(d1y, d1x) - Math.atan2(d2y, d2x)) < 0; } // compare two angles

    static double dist(Point p1, Point p2) { // Euclidean distance
        return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y, 2));
    } // return double

    static boolean ccw(Point p, Point q, Point r) {
        return cross(toVec(p, q), toVec(p, r)) > 0;
    }

    static List<Point> CH(List<Point> P) { // the content of P may be reshuffled
        int i, j, n = (int)P.size();
        if (n <= 3) {
            if (!(P.get(0) == P.get(n-1))) P.add(P.get(0)); // safeguard from corner case
            return P;
        } // special case, the CH is P itself

        // first, find P0 = point with lowest Y and if tie: rightmost X
        int P0 = 0;
        for (i = 1; i < n; i++)
            if (P.get(i).y < P.get(P0).y || (P.get(i).y == P.get(P0).y && P.get(i).x > P.get(P0).x))
                P0 = i;

        Point temp = P.get(0); P.set(0, P.get(P0)); P.set(P0,temp); // swap P[P0] with P[0]

        // second, sort points by angle w.r.t. pivot P0
        Collections.sort(P.subList(1, P.size()), new Comparator<Point>(){
            Point pivot = P.get(0);
            @Override
            public int compare(Point o1, Point o2) {
                if(o1 == o2) return 0;
                boolean result = angleCmp(o1, o2, pivot);
                if(result) return -1;
                else return 1;
            }
        });
        // to be continued

        // continuation from the earlier part
        // third, the ccw tests
        Stack<Point> S = new Stack<Point>();
        S.push(P.get(n-1)); S.push(P.get(0)); S.push(P.get(1)); // initial S
        i = 2; // then, we check the rest
        while (i < n) { // note: N must be >= 3 for this method to work
            j = S.size()-1;
            if (ccw(S.get(j-1), S.get(j), P.get(i))) S.push(P.get(i++)); // left turn, accept
            else S.pop();
        } // or pop the top of S until we have a left turn
        return S;
    }

    // the answer is stored in the third parameter (pass by reference)
    static Line pointsToLine(Point p1, Point p2) {
        Line l = new Line();
        if (Math.abs(p1.x - p2.x) < EPS) { // vertical line is fine
            l.a = 1.0; l.b = 0.0; l.c = -p1.x; // default values
        } else {
            l.a = -(p1.y - p2.y) / (p1.x - p2.x);
            l.b = 1.0; // IMPORTANT: we fix the value of b to 1.0
            l.c = -(l.a * p1.x) - p1.y;
        }
        return l;
    }

    static double distToLine(Point p, Point a, Point b) {
        // formula: c = a + u * ab
        Point c;
        Vec ap = toVec(a, p), ab = toVec(a, b);
        double u = dot(ap, ab) / norm_sq(ab);
        c = translate(a, scale(ab, u)); // translate a to c
        return dist(p, c);
    } // Euclidean distance between p and c

    static Vec scale(Vec v, double s) { // nonnegative s = [<1 .. 1 .. >1]
        return new Vec(v.x * s, v.y * s);
    } // shorter.same.longer

    static Point translate(Point p, Vec v) { // translate p according to v
        return new Point(p.x + v.x, p.y + v.y);
    }

    static double dot(Vec a, Vec b) {
        return (a.x * b.x + a.y * b.y);
    }

    static double norm_sq(Vec v) {
        return v.x * v.x + v.y * v.y;
    }

    static class Line { double a, b, c; }; // a way to represent a line

    static double area(List<Point> P) {
        double result = 0.0, x1, y1, x2, y2;
        for (int i = 0; i < (int)P.size()-1; i++) {
            x1 = P.get(i).x; x2 = P.get(i+1).x;
            y1 = P.get(i).y; y2 = P.get(i+1).y;
            result += (x1 * y2 - x2 * y1);
        }
        return Math.abs(result) / 2.0;
    }
}
public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
            IN = null;
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


    private void solve(double shade){
        double pi = Math.PI;
        if(shade <= 1.414213){
            double minP=0;
            double maxP=pi/4 + 1e-6;
            double mid;
            Square sq;

            while(true){
                mid = (minP+maxP)/2;
                sq = new Square();
                sq.rotateAlongZAxis(mid);
                double area = sq.calculateXZProjectionArea();

                if(Math.abs(area-shade) < 1e-6)
                    break; //find the answer

                if(area > shade) // reduce angle
                    maxP = mid;
                else
                    minP = mid;
            }
            double[] rcenter = sq.getPlaneRedCenter();
            double[] bcenter = sq.getPlaneBlueCenter();
            double[] gcenter = sq.getPlaneGreenCenter();
            out.println(String.format("%.16f %.16f %.16f", rcenter[0], rcenter[1], rcenter[2]));
            out.println(String.format("%.16f %.16f %.16f", bcenter[0], bcenter[1], bcenter[2]));
            out.println(String.format("%.16f %.16f %.16f", gcenter[0], gcenter[1], gcenter[2]));
        }
        else{
            double minP=0;
            //double maxP=pi/4 + 1e-6;
            double maxP = 0.61541; // from https://math.stackexchange.com/questions/2725780/orthogonal-projection-area-of-a-3-d-cube
            double mid = 0;
            Square sq = null;
            while(true){
                mid = (minP+maxP)/2;
                sq = new Square();
                sq.rotateAlongZAxis(pi/4);
                sq.rotateAlongXAxis(mid);
                double area = sq.calculateXZProjectionArea();

                if(Math.abs(area-shade) < 1e-6)
                    break; //find the answer

                if(area > shade) // reduce angle
                    maxP = mid;
                else
                    minP = mid;
            }
            double[] rcenter = sq.getPlaneRedCenter();
            double[] bcenter = sq.getPlaneBlueCenter();
            double[] gcenter = sq.getPlaneGreenCenter();
            out.println(String.format("%.16f %.16f %.16f", rcenter[0], rcenter[1], rcenter[2]));
            out.println(String.format("%.16f %.16f %.16f", bcenter[0], bcenter[1], bcenter[2]));
            out.println(String.format("%.16f %.16f %.16f", gcenter[0], gcenter[1], gcenter[2]));
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.println("Case #" + i + ": ");

            double r = sc.nextDouble();
            solve(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}

