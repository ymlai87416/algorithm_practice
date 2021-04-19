package Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
number: 149
problem: https://leetcode.com/problems/max-points-on-a-line/
level: hard
solution: it is an O(n^2) algorithm. for each pair of point, just create a line.

#hashTable #math

 **/

public class MaxPointsOnALine {

    static
    class Solution {
        private int gcd(int a, int b){
            if(b==0) return a;
            if(b > a) return gcd(b, a);
            return gcd(b, a%b);
        }

        public int maxPoints(int[][] points) {
            if(points.length == 0) return 0;
            if(points.length <= 2) return points.length;

            HashMap<Line, Set<Integer>> lines = new HashMap<>();
            for(int i=0; i<points.length; ++i){
                for(int j=i+1; j<points.length; ++j){
                    int dy = points[i][1] - points[j][1];
                    int dx = points[i][0] - points[j][0];
                    int c;

                    if(dy == 0 && dx == 0){
                        //degenerated lines....
                        dy =points[i][1]; dx = points[i][0];
                        c = Integer.MAX_VALUE;
                    }
                    else {
                        if (dy == 0) {
                            dx = 1;
                            //c = ??;
                        } else if (dx == 0) {
                            dy = 1;
                            //c = ??;
                        } else {
                            if (dx < 0 && dy < 0) {
                                dx = -dx;
                                dy = -dy;
                            }
                            int divisor = gcd(Math.abs(dy), Math.abs(dx));
                            dx /= divisor;
                            dy /= divisor;

                        }
                        c = -dy * points[i][0] + dx * points[i][1];
                    }

                    Line l = new Line(dx, dy, c);

                    if(lines.containsKey(l)){
                        Set<Integer> t = lines.get(l);
                        t.add(i); t.add(j);
                    }
                    else{
                        HashSet<Integer> t = new HashSet<>();
                        t.add(i); t.add(j);
                        lines.put(l, t);
                    }
                }
            }

            int r = 0;
            for(Line l: lines.keySet()) {
                if(lines.get(l).size() > r){
                    //System.out.format("line: %d %d %d, # points: %d\n", l.dx, l.dy, l.c, lines.get(l).size());
                    r = lines.get(l).size();
                }
            }

            return r;
        }
    }

    static
    class Line implements Comparable<Line>{
        public int dy;
        public int dx;
        public int c;

        public Line(int dx, int dy, int c){
            this.dx = dx;
            this.dy = dy;
            this.c = c;
        }

        @Override
        public int compareTo(Line o) {
            if(dx == o.dx){
                if(dy == o.dy){
                    return c - o.c;
                }
                else
                    return dy - o.dy;
            }
            else return dx-o.dx;
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof Line){
                Line ol = (Line)o;
                return ol.dx == dx && ol.dy == dy && ol.c ==  c;
            }
            else
                return false;
        }

        @Override
        public int hashCode(){
            return Integer.hashCode(dx+dy+c);
        }
    }
}
