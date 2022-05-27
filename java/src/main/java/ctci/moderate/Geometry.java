package ctci.moderate;

public class Geometry {
}

class Point{
    double x;
    double  y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
}

class Line{
    double a;
    double b;
    double c;

    public Line(double a, double b, double c){
        this.a = a; this.b = b; this.c = c;
    }

    //ax + by + c = 0
    public static Line createLineFromTwoPoint(Point p1, Point p2){
        if(Math.abs(p1.x - p2.x) < 1e-9){
            //verticle line with infinity slop;
            double a = 1; double b = 0; double c = -p1.x;
            return new Line(a, b, c);
        }
        else{
            //-mx+y+c = 0
            double b = 1;
            double a = -(p1.y - p2.y) / (p1.x - p2.x);
            double c = -(a * p1.x + p1.y);

            return new Line(a, b, c);
        }
    }

    boolean isSame(Line l2){
        return isParallel(l2) && Math.abs(c - l2.c) < 1e-9;
    }

    boolean isParallel(Line l2){
        return Math.abs(a - l2.a) < 1e-9 && Math.abs(b - l2.b) < 1e-9;
    }

    Point intersect(Line l2){
        if(isParallel(l2)) return null;

        double x = -(l2.b * c - b * l2.c) / (a * l2.b - l2.a * b);
        double y;
        if( Math.abs(b) > 1e-9)
            return new Point(x, (-c-a*x) / b);
        else
            return new Point(x, (-l2.c - l2.a*x) / l2.b);
    }
}

class Square{
    double top;
    double left;
    double right;
    double bottom;

    public Square(double t, double l, double r, double b){
        top = t; left = l; right = r; bottom = b;
    }
}