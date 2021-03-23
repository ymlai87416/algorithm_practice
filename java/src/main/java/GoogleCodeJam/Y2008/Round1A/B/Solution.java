package GoogleCodeJam.Y2008.Round1A.B;

import java.io.File;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by ymlai on 11/4/2017.
 *
 * #MatrixMuliplication
 * #FibonacciSequence
 */
public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "C-small-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
            */
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1A\\B\\B-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    //wrong, using loop method can correct up to 18, and will not be meaningful
    private String solveSmall(int n) {
        BigDecimal c = BigDecimal.valueOf(3+Math.sqrt(5));

        if(n == 0) return "001";

        BigDecimal result = BigDecimal.ONE;
        for(int i=0; i<n; ++i){
            result =  result.multiply(c);
        }

        String sr = "000" + result.toString();
        int dpos = sr.indexOf('.');
        return sr.substring(dpos-3, dpos);
    }


    private int[][] powerModulus(int a[][], long n, int modulus)
    {
        if (n == 1)
            return a;

        int[][] r = powerModulus(a, n / 2, modulus);
        int[][] f = multiplyModulus(r, r, modulus);
        if (n % 2 != 0)
            f = multiplyModulus(f, a, modulus);

        return f;
    }

    private int[][] multiplyModulus(int arr1[][], int arr2[][], int modulus)
    {
        int[][] r = new int[2][2];
        int x = (arr1[0][0] * (arr2[0][0])) + (arr1[0][1] *(arr2[1][0]));
        int y = (arr1[0][0] *(arr2[0][1])) + (arr1[0][1] *(arr2[1][1]));
        int z = (arr1[1][0] *(arr2[0][0])) + (arr1[1][1] *(arr2[1][0]));
        int w = (arr1[1][0] *(arr2[0][1])) + (arr1[1][1] *(arr2[1][1]));
        r[0][0] = x % modulus;
        r[0][1] = y % modulus;
        r[1][0] = z % modulus;
        r[1][1] = w % modulus;

        return r;
    }

    private String solveBig(int n){
        //use the formula (xn xn-1)  = B^(n-2) (x2, x1)
        /*
        B = (6 -4)
            (1  0)
         */

        if(n == 0) return "001";
        else if(n ==1) return "005";
        else if(n ==2) return "027";
        int[][] B = new int[2][2];
        B[0][0] = 6; B[0][1] = -4; B[1][0] =1; B[1][1] = 0;
        int[][] m = powerModulus(B, n-2, 1000);

        int r = m[0][0] * 28 + m[0][1] * 6-1;
        while(r < 0) r = 1000 + r;

        return String.format("%03d",r%1000);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int n = sc.nextInt();
            String r;
            r = solveSmall(n);
            //r = solveBig(n);

            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
