package Facebook.Y2015.Round1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tom on 10/1/2017.
 * https://www.facebook.com/hackercup/past_rounds/165233060474397/
 * 00:10, ac at 00:36
 */
public class Problem3 {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\winning_at_sports (1)";
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

    final int modula = 1000000007;


    int[][] dp1 = new int[2001][2001];
    int[][] dp2 = new int[2001][2001];

    private int streeFreeHelper(int a, int b){
        if(b == 0)
            return 1;

        if(a == 1 && b == 0)
            return 1;

        if(dp1[a][b] != -1)
            return dp1[a][b];
        else{
            if(a-1 > b)
                dp1[a][b] = (streeFreeHelper(a-1, b) + streeFreeHelper(a, b-1)) %  modula;
            else
                dp1[a][b] = streeFreeHelper(a, b-1);
        }

        return dp1[a][b];
    }

    //a < b
    private int streeFullHelper(int a, int b, int fb){
        if(a == 0 || b == 0)
            return 1;

        if(dp2[a][b] != -1)
            return dp2[a][b];
        else{
            if(a > fb-1)
                dp2[a][b] = streeFullHelper(a-1, b, fb);
            else if(a > b-1)
                dp2[a][b] = streeFullHelper(a-1, b, fb);
            else
                dp2[a][b] = (streeFullHelper(a, b-1, fb) + streeFullHelper(a-1, b, fb)) % modula;
        }

        return dp2[a][b];
    }

    private void solve(int a, int b) {

        int r1 = streeFreeHelper(a, b);
        int r2 = streeFullHelper(a, b, b);

        out.println(r1 + " " + r2);
        System.out.println(r1 + " " + r2);
    }


    private void run() throws Exception {

        for(int i=0; i<=2000; ++i){
            for(int j=0; j<=2000; ++j)
                dp1[i][j] = dp2[i][j] = -1;
        }

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");

            String input = sc.nextLine();

            String score = "([0-9]+)-([0-9]+)";
            Pattern p = Pattern.compile(score);
            Matcher m = p.matcher(input);

            if(m.matches()){
                int a = Integer.parseInt(m.group(1));
                int b = Integer.parseInt(m.group(2));

                solve(a, b);
            }

        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem3().run();
    }
}
