package ctci.moderate;

public class Intersection {
    //https://www.desmos.com/calculator/bugq76rxhs?lang=en
    public Point pointOfIntersection(Point[] segment1, Point[] segment2){

        Line line1 = Line.createLineFromTwoPoint(segment1[0], segment1[1]);
        Line line2 = Line.createLineFromTwoPoint(segment2[0], segment2[1]);

        if(line1.isSame(line2)){
            if(onLineSegment(segment1[0], segment2[0], segment2[1])) return segment1[0];
            if(onLineSegment(segment1[1], segment2[0], segment2[1])) return segment1[1];
            if(onLineSegment(segment2[0], segment1[0], segment1[1])) return segment2[0];
            if(onLineSegment(segment2[1], segment1[0], segment1[1])) return segment2[1];
            return null;
        }

        if(line1.isParallel(line2))
            return null;

        Point intersect = line1.intersect(line2);
        //now we need to check the point is within line segement

        if(onLineSegment(intersect, segment1[0], segment1[1]) &&
                onLineSegment(intersect, segment2[0], segment2[1])){

            return intersect;
        }
        else
            return null;

    }

    private boolean onLineSegment(Point p, Point start, Point end){
        double minx = Math.min(start.x, end.x);
        double maxx = Math.max(start.x, end.x);

        double miny = Math.min(start.y, end.y);
        double maxy = Math.max(start.y, end.y);

        return minx <= p.x && p.x <= maxx && miny <= p.y && p.y <= maxy;
    }


    public static void main(String[] args) {
        Intersection test  = new Intersection();
        Point[] seg1 = new Point[2];
        Point[] seg2 = new Point[2];

        seg1[0] = new Point(1, 1);
        seg1[1] = new Point(1, -4.3);

        seg2[0] = new Point(1, -1);
        seg2[1] = new Point(1, -4);

        Point ip = test.pointOfIntersection(seg1, seg2);
        if(ip == null)
            System.out.println("No intersection");
        else
            System.out.printf("Intersect at: %.3f %.3f", ip.x, ip.y);
    }
}
