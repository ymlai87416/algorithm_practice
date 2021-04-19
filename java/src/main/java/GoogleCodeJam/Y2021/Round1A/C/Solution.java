package GoogleCodeJam.Y2021.Round1A.C;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Round1A\\C\\C-test.in";
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

    private String toConfig(long N, int Q){
        String t = "";
        for (int i = 0; i < Q; i++) {
            if(N%2==1) t = t+"T"; else  t=t +"F";
            N/=2;
        }

        return t;
    }

    private String solveSmall(int N, int Q, String[] t, int[] score) {

        long maxP = 1;
        for (int i = 0; i < Q; i++) {
            maxP *=2;
        }

        List<String> possibleConfig = new ArrayList<String>();
        for (long i = 0; i < maxP; i++) {
            String correct = toConfig(i, Q);
            boolean pp = true;
            for (int j = 0; j < N && pp; j++) {
                pp = possible(correct, t[j], score[j]);
                if(!pp)
                    break;
            }
            if(pp)
                possibleConfig.add(correct);
        }

        int denom = possibleConfig.size();

        long maxScore = 0;
        String maxAns = "";
        for (int i = 0; i < N; i++) {
            if(score[i] * denom  > maxScore){
                maxScore = score[i] * denom;
                maxAns = t[i];
            }
        }

        //given a set of config, find out the max score
        for (long i = 0; i < maxP; i++) {
            int sc = 0;
            String me = toConfig(i, Q);
            for (int j = 0; j < possibleConfig.size(); j++) {
                sc += getScore(possibleConfig.get(j), me);
            }
            if(sc > maxScore){
                maxScore =sc;
                maxAns = me;
            }
        }

        int gcdx = (int)gcd(maxScore, denom);
        maxScore = maxScore / gcdx;
        denom = denom / gcdx;

        return String.format("%s %d/%d", maxAns, maxScore, denom);
    }

    private long gcd(long a, long b){
        if(a%b==0) return b;
        else return gcd(b, b%a);
    }

    private boolean possible(String a, String b, int score){
        int ss =  0;
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == b.charAt(i)) ss +=1;
        }
        if(ss != score) return false;
        else return true;
    }

    private int getScore(String correct, String me){
        int ss = 0;
        for (int i = 0; i < correct.length(); i++) {
            if(correct.charAt(i) == me.charAt(i))
                ss +=1;
        }
        return ss;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N = sc.nextInt();
            int Q = sc.nextInt();
            sc.nextLine();
            String[] tt = new String[N];
            int[] score = new int[N];
            for (int j = 0; j < N; j++) {
                String line = sc.nextLine();
                String[] tok= line.split(" ");
                tt[j] = tok[0];
                score[j] = Integer.parseInt(tok[1]);
            }
            out.print("Case #" + i + ": ");
            out.println(solveSmall(N, Q, tt, score));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}