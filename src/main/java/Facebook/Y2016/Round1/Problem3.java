package Facebook.Y2016.Round1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.util.Scanner;

/**
 * Created by Tom on 10/1/2017.
 * https://www.facebook.com/hackercup/past_rounds/165233060474397/
 */
public class Problem3 {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\yachtzee (1)";
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


    private void solve(int n, int a, int b, int[] c) {
        int prev = 0;
        int sum = 0;
        int cnt = 0;
        //1st

        double result;

        while(true){
            if(sum > a)
                break;
            prev = sum;
            sum += c[cnt];
            ++cnt;

            if(cnt >= n)
                cnt = 0;
        }

        if(sum >= b){
            double area = 1.0*(b-prev) * (b -prev)/2;
            double cut1 = 1.0*(a-prev) * (a-prev)/2;

            result = (area-cut1)/(b-a);


        }
        else{
            result =  1.0*(sum-prev) * (sum -prev)/2;
            result -= 1.0*(a-prev) * (a-prev)/2;

            while(true){
                if(sum > b)
                    break;

                prev = sum;
                sum += c[cnt];
                ++cnt;

                if(cnt >= n)
                    cnt = 0;

                result += 1.0*(Math.min(sum,b)-prev)*(Math.min(sum,b)-prev)/2;
            }

            result = result/(b-a);
        }
        String resultS = String.format("%.9f", result);
        out.println(resultS);
        System.out.println(resultS);
    }

    int[] c = new int[100000];

    private void run() throws Exception {

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");

            int n = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            for(int p=0; p<n; ++p)
                c[p] = sc.nextInt();

            solve(n, a, b, c);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem3().run();
    }
}
