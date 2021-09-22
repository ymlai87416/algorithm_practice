package Geometry.PointsAndLines;

import java.util.Scanner;

/**
 * Created by Tom on 17/5/2016.
 *
 * problem: https://onlinejudge.org/external/102/10263.pdf
 *
 * #UVA #lines #Lv3
 */
public class UVA10263 {
    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Vector {
        double x, y; // name: ‘vec’ is different from STL vector

        Vector(double _x, double _y) {
            this.x = _x;
            this.y = _y;
        }
    }

    static Vector toVec(Point a, Point b) { // convert 2 points to vector a->b
        return new Vector(b.x - a.x, b.y - a.y);
    }

    static Vector scale(Vector v, double s) { // nonnegative s = [<1 .. 1 .. >1]
        return new Vector(v.x * s, v.y * s);
    } // shorter.same.longer

    static Point translate(Point p, Vector v) { // translate p according to v
        return new Point(p.x + v.x, p.y + v.y);
    }

    static double dot(Vector a, Vector b) {
        return (a.x * b.x + a.y * b.y);
    }

    static double norm_sq(Vector v) {
        return v.x * v.x + v.y * v.y;
    }

    static double dist(Point p1, Point p2) { // Euclidean distance
        // hypot(dx, dy) returns sqrt(dx * dx + dy * dy)
        return Math.sqrt((p1.x - p2.x) *(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
    }

    // returns the distance from p to the line defined by
    // two points a and b (a and b must be different)
    // the closest point is stored in the 4th parameter (byref)
    static double distToLine(Point p, Point a, Point b, Point c) {
        // formula: c = a + u * ab
        Vector ap = toVec(a, p), ab = toVec(a, b);
        double u = dot(ap, ab) / norm_sq(ab);
        Point temp = translate(a, scale(ab, u)); // translate a to c
        c.x = temp.x;
        c.y = temp.y;
        return dist(p, c);
    }

    static double distToLineSegment(Point p, Point a, Point b, Point c) {
        Vector ap = toVec(a, p), ab = toVec(a, b);
        Point temp;
        double u = dot(ap, ab) / norm_sq(ab);
        if (u < 0.0) {
            temp = new Point(a.x, a.y); // closer to a
            c.x = temp.x; c.y = temp.y;
            return dist(p, a);
        } // Euclidean distance between p and a
        if (u > 1.0) {
            temp = new Point(b.x, b.y); // closer to b
            c.x = temp.x; c.y = temp.y;
            return dist(p, b);
        } // Euclidean distance between p and b
        return distToLine(p, a, b, c);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            if(!sc.hasNextDouble()) break;
            double xm = sc.nextDouble();
            double ym = sc.nextDouble();
            Point m = new Point(xm, ym);

            int n = sc.nextInt();

            double x = sc.nextDouble();
            double y = sc.nextDouble();
            Point cur = new Point(x, y);
            Point prev;

            double min = Double.POSITIVE_INFINITY;
            Point result = null;
            for (int i = 0; i < n; ++i) {
                prev = cur;
                x = sc.nextDouble();
                y = sc.nextDouble();

                cur = new Point(x, y);
                Point nearestPoint = new Point(0, 0);
                double dist = distToLineSegment(m, prev, cur, nearestPoint);
                if(min > dist){
                    min = dist;
                    result =nearestPoint;
                }
            }

            System.out.format("%.4f\n", result.x);
            System.out.format("%.4f\n", result.y);
        }
    }
}
