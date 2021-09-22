package Geometry.Triangle;

import java.util.Scanner;

/**
 * Created by ymlai on 25/4/2017.
 *
 * problem: https://onlinejudge.org/external/119/11909.pdf
 *
 * #Lv3 #triangles #UVA
 */
public class UVA11909 {

    static double DEG_to_RAD(double theta){
        return Math.PI *theta /180.0;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextInt()){
            int l = sc.nextInt();
            int w = sc.nextInt();
            int h= sc.nextInt();
            int theta =sc.nextInt();

            double thetaRad = DEG_to_RAD(theta);
            double limit = 1.0*h/l;

            if(l*Math.tan(thetaRad) < h){
                System.out.format("%.3f mL\n", (l*h - l*l*Math.tan(thetaRad)/2)*w);
            }
            else
                System.out.format("%.3f mL\n", h*h*Math.tan(Math.PI/2-thetaRad)/2*w);

        }
    }
}
