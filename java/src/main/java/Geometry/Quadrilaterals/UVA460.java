package Geometry.Quadrilaterals;

import java.util.Scanner;

/**
 * Created by ymlai on 23/4/2017.
 *
 * problem: https://onlinejudge.org/external/4/460.pdf
 *
 * #Lv3 #quadrilaterals #UVA
 */
public class UVA460 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i=0; i<n; ++i ){
            int lx = sc.nextInt();
            int ly = sc.nextInt();
            int rx = sc.nextInt();
            int ry = sc.nextInt();

            int lx2 = sc.nextInt();
            int ly2 = sc.nextInt();
            int rx2 = sc.nextInt();
            int ry2 = sc.nextInt();

            int x0 = Math.max(lx, lx2); // lower bound of intersection interval
            int x1 = Math.min(rx, rx2); // upper bound of intersection interval
            boolean xoverlap =  x0 < x1;  // interval non-empty?

            int y0 = Math.max(ly, ly2); // lower bound of intersection interval
            int y1 = Math.min(ry, ry2); // upper bound of intersection interval
            boolean yoverlap =  y0 < y1;  // interval non-empty?

            if(xoverlap && yoverlap){
                System.out.format("%d %d %d %d\n",x0, y0, x1, y1);
            }
            else
                System.out.println("No Overlap");

            if(i!=n-1)
                System.out.println();
        }
    }
}
