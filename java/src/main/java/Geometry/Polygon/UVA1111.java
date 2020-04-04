package Geometry.Polygon;

import java.util.*;

/**
 * Created by ymlai on 25/4/2017.
 * the ans in graph representation: http://www.csie.ntnu.edu.tw/~u91029/FarthestPair4.png
 */
public class UVA1111 {

    static final double EPS = 1e-9;

    static class Point { double x, y; // only used if more precision is needed
        Point() { x = y = 0.0; } // default constructor
        Point(double _x, double _y){
            x =_x;
            y= _y;
        }}; // user-defined

    static class Vec { double x, y; // name: ‘vec’ is different from STL vector
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

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc =0;
        while(true) {

            int n = sc.nextInt();
            if(n == 0) break;
            nc++;

            List<Point> p = new ArrayList<>();

            for(int i=0; i<n; ++i) {
                p.add(new Point(sc.nextInt(), sc.nextInt()));
            }

            List<Point> convexHull = CH(p);

            double ans = Double.POSITIVE_INFINITY;
            for(int i=1; i<convexHull.size(); ++i){
                Point start = convexHull.get(i-1);
                Point end = convexHull.get(i);
                double tans = 0;
                for(int j=0; j<convexHull.size(); ++j){

                    Point c = convexHull.get(j);
                    if(c ==start || c==end) continue;

                    double dist = distToLine(c, start, end);
                    if(dist > tans)
                        tans = dist;
                }
                ans = Math.min(ans,tans);
            }

            System.out.format("Case %d: %.2f\n", nc, ans);

        }

    }
}
