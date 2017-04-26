package Geometry.PointsAndLines;

import java.util.*;

/**
 * Created by ymlai on 25/4/2017.
 */
public class UVA10927 {

    static double EPS = 1e-11;
    static class Point implements Comparable<Point>{
        double theta;
        double r;
        int h;
        int x;
        int y;

        public Point(double r, double theta, int h, int x, int y){
            this.r = r;
            this.theta = theta;
            this.h = h;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if(Math.abs(theta-o.theta) <EPS){
                if(Math.abs(r - o.r) < EPS)
                    return 0;
                else if(r < o.r)
                    return -1;
                else return 1;
            }
            else if (theta < o.theta) return -1;
            else return 1;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int nc = 0;

        while(true){
            int n=  sc.nextInt();
            if(n == 0) break;
            nc++;
            List<Point> points = new ArrayList<Point>();
            for(int i=0; i<n; ++i){
                int x = sc.nextInt();
                int y = sc.nextInt();
                int h = sc.nextInt();

                double r = 1.0*x*x+y*y; //no need to sqrt
                double theta = Math.atan2(x, y);

                Point p = new Point(r, theta, h, x, y);
                points.add(p);
            }

            Collections.sort(points);
            Point prev = points.get(0);

            List<Point> result = new ArrayList<Point>();

            for(int i=1; i<points.size(); ++i){
                Point cur = points.get(i);
                //check same
                if(Math.abs(prev.theta -cur.theta) < EPS){
                    //if same, then check h
                    if(prev.h < cur.h){
                        prev = cur;
                    }
                    else
                        result.add(cur);
                }
                else
                    prev = cur;

            }

            Collections.sort(result, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    if(o1.x < o2.x) return -1;
                    else if(o1.x > o2.x) return 1;
                    else return Integer.compare(o1.y, o2.y);
                }
            });

            System.out.format("Data set %d:\n", nc);
            if(result.size() == 0){
                System.out.println("All the lights are visible.");
            }
            else{
                System.out.println("Some lights are not visible:");
                for(int i=0; i<result.size(); ++i){
                    Point p = result.get(i);
                    System.out.format("x = %d, y = %d", p.x, p.y);
                    if(i == result.size()-1)
                        System.out.println(".");
                    else
                        System.out.println(";");
                }
            }
        }
    }
}
