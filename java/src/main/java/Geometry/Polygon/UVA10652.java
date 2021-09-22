package Geometry.Polygon;

import java.util.*;

/**
 * Created by ymlai on 26/4/2017.
 * problem: https://onlinejudge.org/external/106/10652.pdf
 *
 * #polygon #UVA #Lv4
 */
public class UVA10652 {

    static final double EPS = 1e-9;

    static class Point { double x, y; // only used if more precision is needed
        Point() { x = y = 0.0; } // default constructor
        Point(double _x, double _y){
            x =_x;
            y= _y;
        }}; // user-defined

    // rotate p by theta degrees CCW w.r.t origin (0, 0)
    static Point rotate(Point p, double theta) {
        double rad = DEG_to_RAD(theta); // multiply theta with PI / 180.0
        return new Point(p.x * Math.cos(rad) - p.y * Math.sin(rad),
                p.x * Math.sin(rad) + p.y * Math.cos(rad)); }

    static double DEG_to_RAD(double degree){
        return degree * Math.PI / 180.0;
    }

    static class Vec { double x, y; // name: ‘vec’ is different from STL vector
        Vec(double _x, double _y) {
            x = _x; y= _y;
        } };

    static Vec toVec(Point a, Point b) { // convert 2 points to vector a->b
        return new Vec(b.x - a.x, b.y - a.y);
    }

    static double cross(Vec a, Vec b) {
        return a.x * b.y - a.y * b.x;
    }

    static double dist(Point p1, Point p2) { // Euclidean distance
        return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y, 2)); } // return double

    // note: to accept collinear points, we have to change the ‘> 0’
    // returns true if point r is on the left side of line pq
    static boolean ccw(Point p, Point q, Point r) {
        return cross(toVec(p, q), toVec(p, r)) > 0;
    }

    // returns true if point r is on the same line as the line pq
    static boolean collinear(Point p, Point q, Point r) {
        return Math.abs(cross(toVec(p, q), toVec(p, r))) < EPS;
    }

    static boolean angleCmp(Point a, Point b, Point pivot) { // angle-sorting function
        if (collinear(pivot, a, b)) // special case
            return dist(pivot, a) < dist(pivot, b); // check which one is closer
        double d1x = a.x - pivot.x, d1y = a.y - pivot.y;
        double d2x = b.x - pivot.x, d2y = b.y - pivot.y;
        return (Math.atan2(d1y, d1x) - Math.atan2(d2y, d2x)) < 0; } // compare two angles

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
        Point pivot = P.get(0); // use this global variable as reference
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
            j = (int)S.size()-1;
            if (ccw(S.get(j-1), S.get(j), P.get(i))) S.push(P.get(i++)); // left turn, accept
            else S.pop();
        } // or pop the top of S until we have a left turn
        return S;
    }

    // returns the area, which is half the determinant
    static double area(List<Point> P) {
        double result = 0.0, x1, y1, x2, y2;
        for (int i = 0; i < (int)P.size()-1; i++) {
            x1 = P.get(i).x; x2 = P.get(i+1).x;
            y1 = P.get(i).y; y2 = P.get(i+1).y;
            result += (x1 * y2 - x2 * y1);
        }
        return Math.abs(result) / 2.0;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int i=0; i<nc; ++i){
            int n = sc.nextInt();

            ArrayList<Point> totalP = new ArrayList<Point>();
            double recArea = 0.0;
            for(int j=0; j<n; ++j){
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                double w = sc.nextDouble();
                double h = sc.nextDouble();
                double theta = sc.nextDouble();

                List<Point> points = new ArrayList<Point>();
                points.add(new Point(w/2, h/2));
                points.add(new Point(w/2, -h/2));
                points.add(new Point(-w/2, h/2));
                points.add(new Point(-w/2, -h/2));

                for(int k=0; k<4; ++k){
                    Point r = rotate(points.get(k), -theta);
                    r.x += x;
                    r.y += y;
                    points.set(k, r);
                }

                totalP.addAll(points);
                recArea += w*h;
            }

            List<Point> convexHull = CH(totalP);

            double convexHullArea = area(convexHull);

            System.out.format("%.1f %%\n", recArea/convexHullArea *100);
        }
    }
}
