package D;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "C:\\GitProjects\\algorithm_practice\\src\\main\\java\\GoogleCodeJam\\Y2020\\Quali\\D\\D-test.in";
            IN = null;
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

    private String solve(int t, int b){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<15; ++i){
            //if > 10, then first 3 queries for detect
            int leftQ;
            if(i != 0){
                String temp = sb.toString();
                int qLeft = -1;
                int qRight = -1;
                int qMid = temp.length()/2;
                int q2Mid = -1;

                for(int j=0, k=temp.length(); j< temp.length(); ++j, --k){
                    if(temp.charAt(j) != temp.charAt(k)){
                        qLeft = j;
                        qRight = k;
                    }
                    if(temp.charAt(j) == temp.charAt(k)){
                        q2Mid = j;
                    }
                }

                if(qLeft == -1){
                    //it is symmetric, so just test flip
                    leftQ = 9;
                    out.println(qMid+1);
                    String aMid = sc.nextLine();
                    if(aMid.charAt(0) != temp.charAt(qMid)){
                        invertString(sb);
                    }
                }
                else if(temp.length() % 2 == 1) {
                    leftQ = 7;
                    out.println(qLeft+1);
                    int aLeft = sc.nextInt();
                    out.println(qRight+1);
                    int aRight = sc.nextInt();
                    out.println(qMid+1);
                    int aMid = sc.nextInt();

                    if(aMid != temp.charAt(qMid)) {
                        invertString(sb);
                        if(aLeft == temp.charAt(qLeft))
                            flipString(sb);
                    }
                    else {
                        if(aLeft != temp.charAt(qLeft))
                            flipString(sb);
                    }
                }
                else{
                    leftQ = 7;
                    out.println(qLeft+1);
                    int aLeft = sc.nextInt();
                    out.println(qRight+1);
                    int aRight = sc.nextInt();
                    out.println(q2Mid+1);
                    int a2Mid = sc.nextInt();

                    if(a2Mid != temp.charAt(q2Mid)) {
                        invertString(sb);
                        if(aLeft == temp.charAt(qLeft))
                            flipString(sb);
                    }
                    else{
                        if(aLeft != temp.charAt(qLeft))
                            flipString(sb);
                    }
                }
            }
            else{
                leftQ = 10;
            }

            for(int j=0; j<leftQ; ++j){
                int n = sb.length();
                if(n == b+1)
                    return sb.toString();
                out.println(n);
                int a = sc.nextInt();
                sb.append(a);
            }
        }

        return sb.toString();
    }

    private void flipString(StringBuilder sb){
        for(int i=0; i<sb.length(); ++i){
            char c = sb.charAt(i);
            sb.setCharAt(i, c == '0' ? '1': '0');
        }
    }

    private void invertString(StringBuilder sb){
        for(int i=0, j=sb.length()-1; i<sb.length(); ++i, --j){
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            int _t = sc.nextInt();
            int _b = sc.nextInt();

            String result = solve(_t, _b);
            out.println(result);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
