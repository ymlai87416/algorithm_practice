package Facebook.Y2021.Round1;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class A3 {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\A2-test.in";
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\consistency_chapter_2_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\consistency_chapter_2_output.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.print("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debugLine(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    //now we need to what, to cache
    private int solve(int N, String s) {
        return 0;
    }

    long[] Gs = new long[20];
    long[] Ps = new long[20];
    long[] PinvsX = new long[20];
    long[] PinvsO = new long[20];
    long[] Qs = new long[20];
    char[] update = new char[8000];
    int[] updateloc = new int[20];

    private int study(){

        String s=  "XFOF.XFOFX"; //enter here
        int N = s.length();

        int len = 0;

        for (int i = 0; i < N; i++) {
            updateloc[i] = len;
            if(s.charAt(i) == '.'){
                for (int j = 0; j < len; j++) {
                    update[len+j] = update[j];
                }
                len = len * 2;
            }
            else {
                update[len] = s.charAt(i);
                ++len;
            }
        }

        for (int i = N-1; i >=0 ; i--) {
            Gs[i] = calcG(update, updateloc[i], len);
            Ps[i] = calcP(update, updateloc[i], len);
            Qs[i] = calcP(update, 0, updateloc[i+1]);
        }
        
        debugflag = true;
        for (int i = 0; i < N; i++) {
            debug(Gs[i] + " ");
        }
        debugLine("");
        for (int i = 0; i < N; i++) {
            debug(Ps[i] + " ");
        }
        debugLine("");
        for (int i = 0; i < N; i++) {
            debug(Qs[i] + " ");
        }
        debugLine("");
        for (int i = 0; i < N; i++) {
            //debug(Pinvs[i] + " ");
        }
        debugLine("");

        return 0;

    }

    //calcuate the substring from 1 ... n
    private long calcP(char[] c, int s, int e){
        long r = 0;
        for (int j = s+1; j <= e; j++) {
            r += solveA1(c, s, j);
        }
        return r;
    }

    //calculate all the substrings
    private long calcG(char[] c, int s, int e){
        long r = 0;
        for (int i = s; i < e; i++) {
            for (int j = i+1; j <= e; j++) {
                r += solveA1(c, i, j);
            }
        }
        return r;
    }

    private int solveA1(char[] cc, int s, int e) {
        int st = 0; //undetermine = 0, 1 = X and 2 = O
        int result= 0;
        for (int i = s; i < e; i++) {
            if(cc[i] == 'O'){
                if(st == 1){
                    st = 2;
                    result+=1;
                }
                else if(st == 0){
                    st = 2;
                }
            }
            else if(cc[i] == 'X'){
                if(st == 2){
                    st = 1;
                    result+=1;
                }
                else if(st == 0){
                    st = 1;
                }
            }
        }
        return result;
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            String str = sc.nextLine();

            int r = solve(N, str);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A3().study();
        //new A3().run();
    }


}
