package Geometry.Quadrilaterals;

import java.util.Scanner;

/**
 * Created by ymlai on 23/4/2017.
 *
 * important insight, the farthest distance is from one corner to another one. and also check if the boundary can contain the circle.
 */
public class UVA11834 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int w, l, r1, r2;

            w = sc.nextInt();
            l = sc.nextInt();
            r1 = sc.nextInt();
            r2 = sc.nextInt();

            if(w == 0 || l == 0 || r1 == 0 || r2 == 0) break;

            int c1x = r1;
            int c1y = r1;
            int c2x = w-r2;
            int c2y = l-r2;

            if((c1x-c2x)*(c1x-c2x) + (c1y-c2y)*(c1y-c2y) >= (r1+r2)*(r1+r2) &&
                    Math.min(w, l) >= 2*r1 &&
                    Math.min(w, l) >= 2*r2)
                System.out.println("S");
            else
                System.out.println("N");
        }
    }
}
