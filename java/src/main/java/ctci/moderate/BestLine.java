package ctci.moderate;
import java.util.*;

public class BestLine {
    //leetcode: https://leetcode.com/problems/max-points-on-a-line/
    //with 1e-9 precision won't pass some test case, need to switch it to BigDecimal with rounding to 50dp.

    public Line bestLine(Point[] points){
        //check for the slope of each point
        Line line = null;
        int best = 0;
        for(int i=0; i<points.length; ++i){
            HashMap<Double, Integer> cnt = new HashMap<>();
            int verticleLine = 0;

            int curBest = 0;
            Double curBestS = null;


            for(int j=i+1; j<points.length; ++j){
                if(i == j) continue;
                Line lineT = Line.createLineFromTwoPoint(points[i], points[j] );

                if(lineT.b == 0) ++verticleLine;
                else{
                    double floorSlope = roundToNearestEpsilon(lineT.a);
                    int freq = cnt.getOrDefault(floorSlope, 0)+1;
                    cnt.put(floorSlope, freq);

                }
            }

            //find the best slope

            for(double d: cnt.keySet()){
                int realCnt = cnt.get(d);
                realCnt += cnt.getOrDefault(d + 1e-9, 0);
                realCnt += cnt.getOrDefault(d - 1e-9, 0);

                if(realCnt+1 > curBest){
                    curBest = realCnt+1;
                    curBestS = d;
                }

            }

            if(verticleLine+1 > curBest){
                curBest = verticleLine+1;
                curBestS = Double.POSITIVE_INFINITY;
            }


            if(curBest > best){
                best = curBest;

                if(curBestS == Double.POSITIVE_INFINITY)
                    line = new Line(1, 0, -points[i].x);
                else{
                    double c = -(curBestS * points[i].x + points[i].y);
                    line  = new Line(curBestS, 1, c);
                }
            }
        }

        return line;
    }

    double roundToNearestEpsilon(double x){
        int r = (int) (x / 1e-9) ;
        return r * 1e-9;
    }

    public static void main(String[] args) {

    }
}
