import java.io.*;
import java.util.*;

//start at
public class Problem1 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\progress_pie";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private boolean equalToZero(double a){
        return Math.abs(a) < 1e-6;
    }

    private boolean biggerThan(double a, double b){
        return a-b > 0 && !equalToZero(a-b);
    }

    private boolean smallerThan(double a, double b){
        return a-b < 0 && !equalToZero(a-b);
    }

    private boolean atCenter(double x,double y){return equalToZero(x) && equalToZero(y);}

    private boolean onPositiveX(double x, double y){
        return equalToZero(y) && biggerThan(x, 0);
    }

    private boolean onPositiveY(double x, double y){
        return equalToZero(x) && biggerThan(y, 0);
    }

    private boolean onNegativeX(double x, double y){
        return equalToZero(y) && smallerThan(x, 0);
    }

    private boolean onNegativeY(double x, double y){
        return equalToZero(x) && smallerThan(y, 0);
    }

    private boolean inQuardant1(double x, double y){
        return biggerThan(x, 0) && biggerThan(y, 0);
    }
    private boolean inQuardant2(double x, double y){
        return smallerThan(x, 0) && biggerThan(y, 0);
    }
    private boolean inQuardant3(double x, double y){
        return smallerThan(x, 0) && smallerThan(y, 0);
    }
    private boolean inQuardant4(double x, double y){
        return biggerThan(x, 0) && smallerThan(y, 0);
    }

    private boolean anticlock(double ux, double uy, double vx, double vy){
        double sum = ux*vy - uy*vx;
        if(smallerThan(sum, 0) || equalToZero(sum))
            return true;
        else
            return false;
    }

    private void solve(int p, int x, int y) {
        String output = null;

        int rx = x-50;
        int ry = y-50;

        int dist = (rx*rx) + (ry*ry);

        if(dist >2500)
            output = "white";
        else{
            int temp = rx;
            rx = ry;
            ry = -temp;

            ry = -ry;

            double px = Math.cos(1.0*p/100 *Math.PI*2);
            double py = Math.sin(1.0*p/100 *Math.PI*2);

            if(p == 0){
                output = "white";
            }
            else if(p > 0 && p < 25){
                if(atCenter(rx, ry) || onPositiveX(rx, ry)
                        || (inQuardant1(rx, ry) && anticlock(px, py, rx, ry)) )
                    output = "black";
                else
                    output = "white";
            }
            else if(p == 25) {
                if(atCenter(rx, ry) || onPositiveX(rx, ry) || onPositiveY(rx, ry)
                        || inQuardant1(rx, ry) )
                    output = "black";
                else
                    output = "white";
            }
            else if( p > 25 && p < 50){
                if(atCenter(rx, ry) || onPositiveX(rx, ry) || onPositiveY(rx, ry)
                        || inQuardant1(rx, ry)
                        || (inQuardant2(rx, ry) && anticlock(px, py, rx, ry)))
                    output = "black";
                else
                    output = "white";
            }
            else if(p == 50){
                if(atCenter(rx, ry) || onPositiveX(rx, ry) || onPositiveY(rx, ry) || onNegativeX(rx,ry)
                        || inQuardant1(rx, ry)
                        || inQuardant2(rx, ry))
                    output = "black";
                else
                    output = "white";
            }
            else if(p > 50 && p<75){
                if(atCenter(rx, ry) || onPositiveX(rx, ry) || onPositiveY(rx, ry) || onNegativeX(rx,ry)
                        || inQuardant1(rx, ry)
                        || inQuardant2(rx, ry)
                        || (inQuardant3(rx, ry) && anticlock(px, py, rx, ry)))
                    output = "black";
                else
                    output = "white";
            }
            else if(p == 75){
                if(atCenter(rx, ry) || onPositiveX(rx, ry) || onPositiveY(rx, ry) || onNegativeY(rx,ry) || onNegativeX(rx, ry)
                        || inQuardant1(rx, ry)
                        || inQuardant2(rx, ry)
                        || inQuardant3(rx, ry))
                    output = "black";
                else
                    output = "white";
            }
            else if(p > 75 && p < 100){
                if(atCenter(rx, ry) || onPositiveX(rx, ry) || onPositiveY(rx, ry) || onNegativeY(rx,ry) || onNegativeX(rx, ry)
                        || inQuardant1(rx, ry)
                        || inQuardant2(rx, ry)
                        || inQuardant3(rx, ry)
                        || (inQuardant4(rx, ry) && anticlock(px, py, rx, ry)))
                    output = "black";
                else
                    output = "white";
            }
            else if(p  == 100){
                output = "black";
            }
        }

        System.out.println(output);
        out.println(output);
    }


    private void run() throws Exception {

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");
            int p = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            solve(p, x, y);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        long start_time = System.currentTimeMillis();
        new Problem1().run();
        long end_time = System.currentTimeMillis();
        long execution_time = (end_time - start_time);

        System.out.println(String.format("Total runtime: %dms", execution_time));
    }
}