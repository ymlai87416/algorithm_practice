package Geometry.PointsAndLines;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 17/5/2016.
 */
public class UVA920 {

    static class Pair implements Comparable<Pair>{
        int first, second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if(first < o.first) return 1;
            else if(first > o.first) return -1;
            else return second - o.second;
        }
    }

    static Pair[] points = new Pair[101];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int i=0; i<nc; ++i){
            int np = sc.nextInt();

            for(int j=0; j<np; ++j){
                int a = sc.nextInt();
                int b = sc.nextInt();

                points[j] = new Pair(a, b);
            }

            Arrays.sort(points, 0, np);

            Pair cur = points[0], prev;
            double sum = 0;
            Pair maxY = points[0];

            for(int j=1; j<np; ++j){
                prev = cur;
                cur = points[j];

                if(cur.second > maxY.second){
                    sum += length(prev, cur) * (cur.second - maxY.second) / Math.abs(cur.second - prev.second);
                    maxY = cur;
                }
            }

            System.out.format("%.2f\n", sum);
        }
    }

    static double length(Pair start, Pair end){
        return Math.sqrt((start.first - end.first)*(start.first - end.first)
                + (start.second - end.second)*(start.second - end.second));
    }
}
