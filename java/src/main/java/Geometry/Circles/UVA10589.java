package Geometry.Circles;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by ymlai on 23/4/2017.
 */
public class UVA10589 {
    static Point[] centers = new Point[4];

    static class Point { BigDecimal x, y; // only used if more precision is needed
        Point() { x = y = BigDecimal.ZERO; } // default constructor
        Point(BigDecimal _x, BigDecimal _y) {
            x = _x;
            y = _y;
        } }; // user-defined

    static boolean withinDist(Point p1, Point p2, BigDecimal r){
        BigDecimal dx= (p1.x.subtract(p2.x)).multiply(p1.x.subtract(p2.x));
        BigDecimal dy = (p1.y.subtract(p2.y)).multiply(p1.y.subtract(p2.y));

        return dx.add(dy).compareTo(r.multiply(r)) <= 0 ;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);


        while(true){
            int N = sc.nextInt();
            int a = sc.nextInt();

            if(N==0 && a==0) break;

            centers[0] = new Point(BigDecimal.ZERO, BigDecimal.ZERO);
            centers[1] = new Point(BigDecimal.valueOf(a), BigDecimal.ZERO);
            centers[2] = new Point(BigDecimal.ZERO, BigDecimal.valueOf(a));
            centers[3] = new Point(BigDecimal.valueOf(a), BigDecimal.valueOf(a));

            int M = 0;
            for(int i=0; i<N; ++i){
                String x = sc.next();
                String y = sc.next();

                Point p = new Point(new BigDecimal(x), new BigDecimal(y));

                boolean inside=true;
                for(int j=0; j<4; ++j){
                    if(!withinDist(p, centers[j], BigDecimal.valueOf(a))){
                        inside = false;
                        break;
                    }
                }

                if(inside) ++M;
            }

            DecimalFormat df = new DecimalFormat("0.00000");
            BigDecimal ans = BigDecimal.valueOf(M * a * a).divide(BigDecimal.valueOf(N));
            System.out.println(df.format(ans));
        }

    }
}
