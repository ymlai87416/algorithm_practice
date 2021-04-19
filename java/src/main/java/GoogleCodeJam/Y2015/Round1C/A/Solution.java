package GoogleCodeJam.Y2015.Round1C.A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by ymlai on 22/4/2017.
 */
public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2015\\Round1C\\A\\A-test.in";
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

    private void solve(int R, int C, int W) {
        int ans = 0;
        int mark = 0;
        mark = C/W;

        ans += mark * (R-1);

        int hitline = 0;
        if(C % W == 0)
            hitline = mark-1+W;
        else
            hitline = mark+W;

        ans+=hitline;

        out.println(ans);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int R, C, W;
            R = sc.nextInt();
            C = sc.nextInt();
            W = sc.nextInt();

            solve(R, C, W);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
