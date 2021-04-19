package GoogleCodeJam.Y2016.Round1C.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2016\\Round1C\\A\\A-test.in";
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

    private String solve(int N, int[] s) {
        String ans = "";

        int ts = 0;
        for (int i = 0; i < N; i++) {
            ts += s[i];
        }

        while(ts != 0){
            //just extract the group
            //we can extract 1 or 2

            String cur = "";
            int mG = -1, vG=0;
            int fG, sG = 0;
            for (int i = 0; i < N; i++) {
                if(s[i] > vG){
                    mG = i;
                    vG = s[i];
                }
            }
            fG = mG;
            s[fG]--;
            --ts;
            cur = "" + (char)('A' + fG);

            //second
            mG = -1; vG=0;
            for (int i = 0; i < N; i++) {
                if(s[i] > vG){
                    mG = i;
                    vG = s[i];
                }
            }

            int tts = ts-1;
            boolean majority = false;
            for (int i = 0; i < N; i++) {
                if(i == mG) majority = (2* (s[i]-1) > tts);
                else majority = (2*s[i] > tts);

                if(majority) {
                    debug("majority: " + i);
                    break;
                }
            }

            if(!majority) {
                sG = mG;
                s[sG]--;
                --ts;
                cur = cur + (char)('A' + sG);
            }

            ans = ans + " " +cur;
        }

        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N = sc.nextInt();
            int[] s  =new int[N];
            for (int j = 0; j < N; j++) {
                s[j] = sc.nextInt();
            }
            out.print("Case #" + i + ":");
            out.println(solve(N, s));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}