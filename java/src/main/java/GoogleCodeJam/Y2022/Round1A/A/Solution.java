package GoogleCodeJam.Y2022.Round1A.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Round1A/A/A-test.in";
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
    String in;
    private String solve2(String in){
        this.in = in;
        return solve2H(0);
    }
    private String solve2H(int idx){
        if(idx == in.length()) return "";
        char x = in.charAt(idx);
        String nn = solve2H(idx+1);

        String a1 = "" +x + nn;
        String a2 = "" +x + a1;
        return a1.compareTo(a2) < 0 ? a1 : a2;
    }

    private String solve(String a) {
        char[] rightL  = new char[a.length()];
        rightL[a.length()-1] = a.charAt(a.length()-1);
        for(int i=a.length()-2; i>=0; --i){
            char x = a.charAt(i);
            rightL[i] = x > rightL[i+1] ? x: rightL[i+1];
        }

        StringBuffer sb = new StringBuffer();

        for(int i=0; i<a.length(); ++i){
            //if next letter is bigger than current, duplicate, else just do it
            Character cur = a.charAt(i);
            Character next = i+1 == a.length() ? null : a.charAt(i+1);

            if(cur == next){
                if(cur < rightL[i]){
                    sb.append(cur);sb.append(cur);
                }
                else
                    sb.append(cur);
            }
            else if(next !=null && cur < next) {
                sb.append(cur);
                sb.append(cur);
            }
            else
                sb.append(cur);
        }

        return sb.toString();
    }

    String xx = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String genRandom(){
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for(int i=0; i<10; ++i) {
            int rr = r.nextInt(26);
            sb.append(xx.charAt(rr));
        }
        return sb.toString();
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        /*
        for(int i=0; i<100; ++i){
            String t = genRandom();
            String t1 = solve(t);
            String t2 = solve2(t);

            if(t1.compareTo(t2)!= 0)
                System.out.println("Shit: " + t + " " + t1 + " " + t2);
        }*/

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            String str = sc.nextLine();
            out.print("Case #" + i + ": ");
            System.out.println(solve2(str));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}