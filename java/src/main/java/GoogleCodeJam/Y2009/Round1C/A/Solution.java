package GoogleCodeJam.Y2009.Round1C.A;


/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2009\\Round1C\\A\\A-test.in";
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


    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }


    String t = "abcdefghijklmnopqrstuvwxyz0123456789";
    private long solve(String a) {

        int radix = 0;
        int[] exist = new int[36];

        for (int i = 0; i < exist.length; i++) {
            exist[i] = -99;
        }

        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            int tpos = t.indexOf(c);
            if(exist[tpos] == -99){
                exist[tpos] = -1;
                radix +=1;
            }
        }

        radix = Math.max(2, radix);
        debug("r: " + radix);


        //put 1 in the current digit and 0 to the second
        char c; int tpos;
        c = a.charAt(0);
        tpos = t.indexOf(c);
        exist[tpos] =1;

        int curptr = 0;
        for (int i = 1; i < a.length(); i++) {

            c = a.charAt(i);
            tpos = t.indexOf(c);

            if(exist[tpos] == -1) {
                exist[tpos] = curptr;
                if (curptr == 0)
                    curptr = 2;
                else curptr++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length(); i++) {
            if(exist[i] == -99) continue;
            sb.append(t.charAt(i) + ": " + exist[i] + " ");
        }
        debug(sb.toString());

        //then calculate the sum of the shit
        long ans = 0;
        for (int i = 0; i < a.length(); i++) {
            c = a.charAt(i);
            tpos = t.indexOf(c);
            int rc = exist[tpos];
            ans = ans * radix + rc;
        }

        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String in = sc.nextLine();
            out.println(solve(in));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}