package Geometry.Polygon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 26/4/2017.
 */
public class UVA11265 {

    static class Point { double x, y; // only used if more precision is needed
        Point() { x = y = 0.0; } // default constructor
        Point(double _x, double _y){
            x = _x; y = _y;
        } }; // user-defined

    static final double EPS = 1e-9;

    // line segment p-q intersect with line A-B.
    static Point lineIntersectSeg(Point p, Point q, Point A, Point B) {
        double a = B.y - A.y;
        double b = A.x - B.x;
        double c = B.x * A.y - A.x * B.y;
        double u = Math.abs(a * p.x + b * p.y + c);
        double v = Math.abs(a * q.x + b * q.y + c);
        return new Point((p.x * v + q.x * u) / (u+v), (p.y * v + q.y * u) / (u+v)); }

    static class Vec { double x, y; // name: ‘vec’ is different from STL vector
        Vec(double _x, double _y) {
            x = _x; y= _y;
        } };

    static double cross(Vec a, Vec b) {
        return a.x * b.y - a.y * b.x;
    }

    static Vec toVec(Point a, Point b) { // convert 2 points to vector a->b
        return new Vec(b.x - a.x, b.y - a.y);
    }

    // cuts polygon Q along the line formed by point a -> point b
    // (note: the last point must be the same as the first point)
    // return left by calling cutPolygon(a, b, Q), right by calling cutPolygon(b, a, Q)
    static List<Point> cutPolygon(Point a, Point b, List<Point> Q) {
        List<Point> P = new ArrayList<>();
        for (int i = 0; i < (int)Q.size(); i++) {
            double left1 = cross(toVec(a, b), toVec(a, Q.get(i))), left2 = 0;
            if (i != (int)Q.size()-1) left2 = cross(toVec(a, b), toVec(a, Q.get(i+1)));
            if (left1 > -EPS) P.add(Q.get(i)); // Q[i] is on the left of ab
            if (left1 * left2 < -EPS) // edge (Q[i], Q[i+1]) crosses line ab
                P.add(lineIntersectSeg(Q.get(i), Q.get(i+1), a, b));
        }
        if (!P.isEmpty() && !(P.get(P.size()-1) == P.get(0)))
            P.add(P.get(0)); // make P’s first point = P’s last point
        return P;
    }

    // returns true if point p is in either convex/concave polygon P
    static boolean inPolygon(Point pt, List<Point> P) {
        if ((int)P.size() == 0) return false;
        double sum = 0; // assume the first vertex is equal to the last vertex
        for (int i = 0; i < (int)P.size()-1; i++) {
            if (ccw(pt, P.get(i), P.get(i+1)))
                sum += angle(P.get(i), pt, P.get(i+1)); // left turn/ccw
            else sum -= angle(P.get(i), pt, P.get(i+1)); } // right turn/cw
        return Math.abs(Math.abs(sum) - 2*Math.PI) < EPS;
    }

    // note: to accept collinear points, we have to change the ‘> 0’
    // returns true if point r is on the left side of line pq
    static boolean ccw(Point p, Point q, Point r) {
        return cross(toVec(p, q), toVec(p, r)) > 0;
    }

    static double angle(Point a, Point o, Point b) { // returns angle aob in rad
        Vec oa = toVec(o, a), ob = toVec(o, b);
        return Math.acos(dot(oa, ob) / Math.sqrt(norm_sq(oa) * norm_sq(ob)));
    }

    static double dot(Vec a, Vec b) {
        return (a.x * b.x + a.y * b.y);
    }

    static double norm_sq(Vec v) {
        return v.x * v.x + v.y * v.y;
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

    public static void main(String[] args) throws Exception{
        if(args.length!= 0)
            System.setIn(new FileInputStream(args[0]));

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int nc = 0;
        while(true){
            String line = sc.readLine();
            if(line == null) break;
            if(line.trim().length() == 0)
                continue;

            List<List<Point>> polygon = new ArrayList<>();
            String[] token = line.split(" ");
            int n = Integer.parseInt(token[0]);
            int w = Integer.parseInt(token[1]);
            int h = Integer.parseInt(token[2]);
            int x = Integer.parseInt(token[3]);
            int y = Integer.parseInt(token[4]);

            nc++;

            Point fountain = new Point(x, y);

            List<Point> land = new ArrayList<>();
            land.add(new Point(0, 0));
            land.add(new Point(w, 0));
            land.add(new Point(w, h));
            land.add(new Point(0, h));
            land.add(new Point(0, 0));

            polygon.add(land);

            for(int i=0; i<n; ++i){
                line = sc.readLine();
                token = line.split(" ");
                int x1 =Integer.parseInt(token[0]);
                int y1 = Integer.parseInt(token[1]);
                int x2 = Integer.parseInt(token[2]);
                int y2 = Integer.parseInt(token[3]);

                Point p1 = new Point(x1, y1);
                Point p2 = new Point(x2, y2);

                List<List<Point>> polygonNext = new ArrayList<>();

                for(int j=0; j<polygon.size(); ++j){
                    List<Point> pg = polygon.get(j);
                    List<Point> polyLeft = cutPolygon(p1, p2, pg);
                    if(polyLeft.size() != 0 && inPolygon(fountain, polyLeft))
                        polygonNext.add(polyLeft);
                    else{
                        List<Point> polyRight = cutPolygon(p2, p1, pg);
                        if(polyRight.size() != 0) polygonNext.add(polyRight);
                    }
                }

                polygon = polygonNext;
            }

            double ans = 0;
            for(int i=0; i<polygon.size(); ++i){
                List<Point> poly = polygon.get(i);
                if(inPolygon(fountain, poly)){
                    ans = area(poly);
                    break;
                }
            }

            System.out.format("Case #%d: %.3f\n", nc, ans);
        }
    }
}
