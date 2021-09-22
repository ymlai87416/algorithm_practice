package Geometry.Triangle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ymlai on 21/4/2017.
 *
 * problem: https://onlinejudge.org/external/111/11152.pdf
 *
 * #Lv2 #triangles #UVA
 */
public class UVA11152 {

    static double area(double a, double b, double c){
        double s = (a+b+c)/2;
        return Math.sqrt(s *(s-a)*(s-b)*(s-c));
    }

    static double perimeter(double a, double b, double c){
        return a+b+c;
    }

    static double rInCircle(double ab, double bc, double ca) {
        return area(ab, bc, ca) / (0.5 * perimeter(ab, bc, ca)); }

    static double rCircumCircle(double ab, double bc, double ca) {
        return ab * bc * ca / (4.0 * area(ab, bc, ca)); }

    static double circleArea(double r){
        return Math.PI * r * r;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader sc= new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String line = sc.readLine();

            if(line == null || line.trim().length() == 0)break;

            String[] token = line.split(" ");
            double a = Integer.parseInt(token[0]);
            double b = Integer.parseInt(token[1]);
            double c = Integer.parseInt(token[2]);

            double f =circleArea(rInCircle(a,b,c));
            double e = area(a,b,c);
            double d = circleArea(rCircumCircle(a,b,c));

            System.out.format("%.4f %.4f %.4f\n", d-e, e-f, f);
        }
    }
}
