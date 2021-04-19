package GoogleCodeJam.Y2021.Round1A.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Round1A\\A\\A-test.in";
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

    private int solve(int N, String[] b) {
        int ans = 0;

        for (int i = 1; i < N; i++) {
            if(b[i-1].startsWith(b[i])){
                BigInteger aa = new BigInteger(b[i-1]);
                aa = aa.add(BigInteger.ONE);
                String nb = aa.toString();
                if(nb.startsWith(b[i])) {
                    ans += nb.length() -b[i].length();
                    b[i] = nb;
                    continue;
                }
            }

            if (biggerEq(b[i - 1], b[i])) {
                while (biggerEq(b[i - 1], b[i])) {
                    b[i] = b[i] + "0";
                    ans++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            debug(b[i]);
        }
        
        return ans;
    }

    //return true if a > b
    private boolean biggerEq(String a, String b){
        if(a.length() > b.length()) return true;
        if(a.length() == b.length()) return a.compareTo(b) >= 0;
        else return false;
    }

    private boolean eq(String a, String b){
        return a.length() == b.length() && a.compareTo(b) == 0;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N= sc.nextInt();
            sc.nextLine();
            String[] b = new String[N];
            for (int j = 0; j < N; j++) {
                b[j] = String.valueOf(sc.nextInt());
            }
            out.print("Case #" + i + ": ");
            System.out.println(solve(N, b));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}