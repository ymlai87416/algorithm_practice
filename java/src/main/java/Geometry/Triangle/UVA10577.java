package Geometry.Triangle;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 25/4/2017.
 *
 * problem: https://onlinejudge.org/external/105/10577.pdf
 *
 * #Lv4 #triangles #UVA
 */
public class UVA10577 {

    static double EPS= 1e-9;
    static double area(double a, double b, double c){
        double s = perimeter(a,b,c)/2;
        return Math.sqrt(s * (s-a) * (s-b) * (s-c));
    }

    static double perimeter(double a, double b, double c){
        return a+b+c;
    }

    static class Line { double a, b, c; }; // a way to represent a line

    static double rCircumCircle(double ab, double bc, double ca) {
        return ab * bc * ca / (4.0 * area(ab, bc, ca)); }

    static double rCircumCircle(Point a, Point b, Point c) {
        return rCircumCircle(dist(a, b), dist(b, c), dist(c, a)); }

    static Line pointsToLine(Point p1, Point p2) {
        Line l = new Line();
        if (Math.abs(p1.x - p2.x) < EPS) { // vertical line is fine
            l.a = 1.0; l.b = 0.0; l.c = -p1.x; // default values
        } else {
            l.a = -(double)(p1.y - p2.y) / (p1.x - p2.x);
            l.b = 1.0; // IMPORTANT: we fix the value of b to 1.0
            l.c = -(double)(l.a * p1.x) - p1.y;
        }
        return l;
    }


    static Point circumCircle(Point p1, Point p2, Point p3) {
        double r = rCircumCircle(p1, p2, p3);
        if (Math.abs(r) < EPS) return null; // no inCircle center
        Line l1, l2; // compute these two perpendicular bisectors
        Point mid = new Point((p1.x+p2.x)/2, (p1.y+p2.y)/2);
        l1 = pointsToLine(p1, p2);
        double t = l1.a; l1.a=-l1.b; l1.b=t;
        l1.c = -(l1.a*mid.x + l1.b*mid.y);

        mid = new Point((p1.x+p3.x)/2, (p1.y+p3.y)/2);
        l2 = pointsToLine(p1, p3);
        t = l2.a; l2.a=-l2.b; l2.b=t;
        l2.c = -(l2.a*mid.x + l2.b*mid.y);

        Point p = areIntersect(l1, l2); // get their intersection point
        return p;
    }

    static boolean areParallel(Line l1, Line l2) { // check coefficients a & b
        return (Math.abs(l1.a-l2.a) < EPS) && (Math.abs(l1.b-l2.b) < EPS); }

    // returns true (+ intersection point) if two lines are intersect
    static Point areIntersect(Line l1, Line l2) {
        if (areParallel(l1, l2)) return null; // no intersection
        Point p = new Point();
        // solve system of 2 linear algebraic equations with 2 unknowns
        p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
        // special case: test for vertical line to avoid division by zero
        if (Math.abs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c)/l1.b;
        else p.y = -(l2.a * p.x + l2.c)/l2.b;
        return p; }

    static double dist(Point p1, Point p2) { // Euclidean distance
        // hypot(dx, dy) returns sqrt(dx * dx + dy * dy)
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2)); } // return double

    static class Point { double x, y; // only used if more precision is needed
        Point() { x = y = 0.0; } // default constructor
        Point(double _x, double _y){
            x = _x;
            y = _y;
        }}; // user-defined

    // rotate p by theta degrees CCW w.r.t origin (0, 0)
    static Point rotate(Point p, double theta) {
        double rad = DEG_to_RAD(theta); // multiply theta with PI / 180.0
        return new Point(p.x * Math.cos(rad) - p.y * Math.sin(rad),
                p.x * Math.sin(rad) + p.y * Math.cos(rad)); }

    static double DEG_to_RAD(double theta){
        return Math.PI *theta /180.0;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int nc=0;
        while(true){
            int n=  sc.nextInt();
            if(n == 0) break;
            ++nc;

            Point a = new Point(sc.nextDouble(), sc.nextDouble());
            Point b = new Point(sc.nextDouble(), sc.nextDouble());
            Point c = new Point(sc.nextDouble(), sc.nextDouble());

            double r = rCircumCircle(a, b, c);
            Point center = circumCircle(a,b,c);

            List<Point> pList = new ArrayList<Point>();

            Point prev = new Point(a.x-center.x, a.y-center.y);
            pList.add(prev);
            for(int i=0; i<n-1; ++i){
                prev = rotate(prev, 360.0/n);
                pList.add(prev);
            }

            double minX = Double.POSITIVE_INFINITY;
            double maxX = Double.NEGATIVE_INFINITY;
            double minY = Double.POSITIVE_INFINITY;
            double maxY = Double.NEGATIVE_INFINITY;
            for(int i=0; i<n; ++i){
                Point p = pList.get(i);
                minX = Math.min(minX, p.x);
                maxX = Math.max(maxX, p.x);
                minY = Math.min(minY, p.y);
                maxY = Math.max(maxY, p.y);
            }

            System.out.format("Polygon %d: %.3f\n", nc, (maxX-minX) *(maxY-minY));
        }
    }
}
