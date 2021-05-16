package GoogleCodeJam.Y2021.Round1C.B;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Round1C\\B\\B-test.in";
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

    boolean debugflag = true;
    private void debug(String s){
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

    private String solveSmall(String s){
        int a = Integer.parseInt(s)+1;
        while(true) {
            String sa = String.valueOf(a);
            if(isRoaring(sa))
                return sa;
            a++;
        }
    }

    private boolean isRoaring(String a){
        for (int i = 1; i <= a.length()/2; i++) {
            int aa = Integer.parseInt(a.substring(0,i));
            StringBuilder sb = new StringBuilder();
            while(sb.length() < a.length()){
                sb.append(aa);
                aa = aa+1;
            }

            if(sb.toString().compareTo(a) == 0) return true;
        }
        return false;
    }

    private String solve(String s) {
        String ans = null;

        if(s.length()==1)
            return "12";

        //i don't have to resolve

        for (int i = 1; i <= s.length()/2; i++) {
            StringBuilder sb = new StringBuilder();
            int rep = s.length()/i;
            String sf = s.substring(0, i);
            Long a = Long.parseLong(sf);
            Long ma = (long) Math.pow(10, i);

            String prevRR = null;

            for (long j = a; j < ma; j++) {

                long ja=j;

                sb = new StringBuilder();
                while(sb.length() < s.length()) {
                    sb.append(ja);
                    ja += 1;
                }

                //debug(i, sb.toString());

                String rr = sb.toString();

                if(j-a > 30 && prevRR != null && prevRR.length() > s.length() && rr.length() > s.length() && rr.length() > prevRR.length())
                    break;

                if(compareNum(s, rr) && rr.length()==s.length()) {
                    if (ans == null || (compareNum(rr, ans))) {
                        ans = rr;
                        break;
                    }
                }
                prevRR = rr;
            }



            /*
            //how about we add 1 ?
            sb = new StringBuilder();
            a = Long.parseLong(sf)+1;
            while(sb.length() < s.length()) {
                sb.append(a);
                a += 1;
            }

            //debug(i, sb.toString());

            rr = sb.toString();
            if(compareNum(s, rr)) {
                if (ans == null || (compareNum(rr, ans))) {
                    ans = rr;
                }
            }*/
        }

        if(ans!=null && ans.length() == s.length()) return ans;

        //if I have to restore to next digit
        long[] t = new long[]{0, 0, 12,
                123,
                1011,
                12345,
                100101,
                1234567,
                10001001,
                100101102,
                1000010001,
                12345678910l,
                100000100001l,
                1234567891011l,
                10000001000001l,
                100001000110002l,
                1000000010000001l,
                12345678910111213l,
                100000000100000001l,};

        int sl1 = s.length()+1;
        if(ans == null)
            return String.valueOf(t[sl1]);
        else if(ans != null && compareNum(String.valueOf(t[sl1]), ans))
            return String.valueOf(t[sl1]);
        else
                return ans;
    }

    private boolean compareNum(String a, String b){
        return new BigInteger(a).compareTo(new BigInteger(b)) < 0;
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(true){
            //solve("91011");

            for (int i = 10; i <100000; i++) {
                String si = String.valueOf(i);
                String t1 =solveSmall(si);
                String t2 = solve(si);

                if(t1.compareTo(t2)!=0)
                    debug("Shit " + i, t1, t2);
            }

            return;
        }


        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String s = sc.nextLine();
            String r = solve(s);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
