package Geometry.Circles;

import java.util.*;

/**
 * Created by ymlai on 23/4/2017.
 *
 * problem: https://onlinejudge.org/external/100/10005.pdf
 *
 * #circle #UVA #Lv3
 */
public class UVA10005 {
    static class Circle{
        Point c;
        double r;

        public Circle(Point p, double r){
            c = p;
            this.r = r;
        }

        boolean contains(Point p){
            return Math.sqrt((p.x - c.x)*(p.x - c.x) + (p.y - c.y)*(p.y - c.y)) <= r *(1+1e-9);
        }
    }

    static class Point { double x, y; // only used if more precision is needed
        Point() { x = y = 0.0; } // default constructor
        Point(double _x, double _y) {
            x = _x;
            y = _y;
        } }; // user-defined

    static class Vec { double x, y; // name: ‘vec’ is different from STL vector
        Vec(double _x, double _y) {
            x = _x;
            y = _y;
        } };

    static Vec toVec(Point a, Point b) { // convert 2 points to vector a->b
        return new Vec(b.x - a.x, b.y - a.y); }

    static double cross(Vec a, Vec b) { return a.x * b.y - a.y * b.x; }

    static double dist(Point p1, Point p2) { // Euclidean distance
        return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y)); } // return double

    static Circle makeCircle(List<Point> points) {
        // Clone list to preserve the caller's data, randomize order
        List<Point> shuffled = new ArrayList<>(points);
        Collections.shuffle(shuffled, new Random());

        // Progressively add points to circle or recompute circle
        Circle c = null;
        for (int i = 0; i < shuffled.size(); i++) {
            Point p = shuffled.get(i);
            if (c == null || !c.contains(p))
                c = makeCircleOnePoint(shuffled.subList(0, i + 1), p);
        }
        return c;
    }

    // One boundary point known
    private static Circle makeCircleOnePoint(List<Point> points, Point p) {
        Circle c = new Circle(p, 0);
        for (int i = 0; i < points.size(); i++) {
            Point q = points.get(i);
            if (!c.contains(q)) {
                if (c.r == 0)
                    c = makeDiameter(p, q);
                else
                    c = makeCircleTwoPoints(points.subList(0, i + 1), p, q);
            }
        }
        return c;
    }

    // Two boundary points known
    private static Circle makeCircleTwoPoints(List<Point> points, Point p, Point q) {
        Circle circ = makeDiameter(p, q);
        Circle left = null;
        Circle right = null;

        // For each point not in the two-point circle
        Vec pq = toVec(p, q);
        for (Point r : points) {
            if (circ.contains(r))
                continue;

            // Form a circumcircle and classify it on left or right side
            double cross = cross(pq, toVec(p, r));
            Circle c = makeCircumcircle(p, q, r);
            if (c == null)
                continue;
            else if (cross > 0 && (left == null || cross(pq, toVec(p,c.c)) > cross(pq, toVec(p, left.c))))
                left = c;
            else if (cross < 0 && (right == null || cross(pq, toVec(p, c.c)) < cross(pq, toVec(p, right.c))))
                right = c;
        }

        // Select which circle to return
        if (left == null && right == null)
            return circ;
        else if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return left.r <= right.r ? left : right;
    }


    static Circle makeDiameter(Point a, Point b) {
        Point c = new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
        return new Circle(c, Math.max(dist(c, a), dist(c, b)));
    }

    static Circle makeCircumcircle(Point a, Point b, Point c) {
        // Mathematical algorithm from Wikipedia: Circumscribed circle
        double ox = (Math.min(Math.min(a.x, b.x), c.x) + Math.max(Math.min(a.x, b.x), c.x)) / 2;
        double oy = (Math.min(Math.min(a.y, b.y), c.y) + Math.max(Math.min(a.y, b.y), c.y)) / 2;
        double ax = a.x - ox, ay = a.y - oy;
        double bx = b.x - ox, by = b.y - oy;
        double cx = c.x - ox, cy = c.y - oy;
        double d = (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) * 2;
        if (d == 0)
            return null;
        double x = ((ax * ax + ay * ay) * (by - cy) + (bx * bx + by * by) * (cy - ay) + (cx * cx + cy * cy) * (ay - by)) / d;
        double y = ((ax * ax + ay * ay) * (cx - bx) + (bx * bx + by * by) * (ax - cx) + (cx * cx + cy * cy) * (bx - ax)) / d;
        Point p = new Point(ox + x, oy + y);
        double r = Math.max(Math.max(dist(p, a), dist(p, b)), dist(p, c));
        return new Circle(p, r);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int n = sc.nextInt();
            if(n == 0) break;
            List<Point> points = new ArrayList<Point>();
            for(int i=0; i<n; ++i)
                points.add(new Point(sc.nextInt(), sc.nextInt()));
            Circle c = makeCircle(points);

            double R = sc.nextDouble();

            if(c.r > R && !(Math.abs(R-c.r) < 1e-9))
                System.out.println("There is no way of packing that polygon.");
            else
                System.out.println("The polygon can be packed in the circle.");
        }


    }

}
