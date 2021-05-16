package GoogleCodeJam.Y2013.Round1B.B;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2013\\Round1B\\B\\B-test.in";
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

    String s;

    private int solve(String s){
        this.s = s;

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int ans = dpHelper(0, -1);

        return ans;
    }


    int[][] dp =new int[4001][4001];
    int INF = 99999999;

    private int dpHelper(int ptr, int lastErrorPos) {

        if(ptr == s.length())
            return 0;

        if(lastErrorPos != -1 && dp[ptr][lastErrorPos] != -1)
            return dp[ptr][lastErrorPos];

        int ans = INF;

        for (int i = ptr+dictMin; i <= Math.min(s.length(), ptr+dictMax); i++) {
            String ss = s.substring(ptr, i);
            ArrayList<String> cc = new ArrayList<>();

            //3, and now is 5, then the next possible location = 3+5-1-5 = 2;
            int possibleStart = lastErrorPos == -1 ? 0 : lastErrorPos + 5 - ptr;
            if(possibleStart < 0) possibleStart = 0;

            if(possibleStart < ss.length() && !lookup.containsKey(ss))
                generateAllCombo(ss, possibleStart, cc);
            else
                cc.add(ss);

            for (String scc: cc) {
                if(lookup.containsKey(scc)){
                    //now we count how many star
                    int change = 0;
                    int lastStar = -1;
                    for (int j = 0; j < scc.length(); j++) {
                        if(scc.charAt(j) == '*') {
                            ++change;
                            lastStar = j+ptr;
                        }
                    }

                    if(lastStar== -1)
                        lastStar= lastErrorPos;

                    int temp = dpHelper(ptr+scc.length(), lastStar) + change;
                    if(temp < ans){
                        ans = temp;
                    }
                }
            }
        }

        if(lastErrorPos!=-1)
            dp[ptr][lastErrorPos] = ans;

        return ans;
    }

    HashMap<String, String> lookup = new HashMap<>();

    private void generateAllCombo(String s, int ptr, ArrayList<String> combo){
        if(ptr >= s.length()){
            //lookup.put(s, w);
            combo.add(s);
            return;
        }
        //either change this or skip this
        String ns = s.substring(0, ptr) + "*" + s.substring(ptr+1, s.length());
        generateAllCombo(ns, ptr+5, combo);
        generateAllCombo(s, ptr+1, combo);
    }

    private void generateTest(){
        Random r = new Random();
        int w = r.nextInt(20);
        String[] ww = new String[] {
                "aabea",
                "bobs",
                "code",
                "in",
                "jam",
                "oo",
                "operation",
                "production",
                "system"
        };

        String a = "";
        for (int i = 0; i < w; i++) {
            a = a+ww[r.nextInt(9)];
        }

        out.println(a);
    }

    int dictMin = 9999999;
    int dictMax = 0;
    private void run() throws Exception {

        if(false){
            generateTest();
            return;
        }

        // out = new PrintStream(new FileOutputStream(OUT));
        int N = sc.nextInt();
        sc.nextLine();


        for (int j = 0; j < N; j++) {
            String w = sc.nextLine();
            dictMin = Math.min(dictMin, w.length());
            dictMax = Math.max(dictMax, w.length());
            ArrayList<String> combo = new ArrayList<>();
            generateAllCombo(w, 0, combo);

            for (int i = 0; i < combo.size(); i++) {
                lookup.put(combo.get(i), w);
            }
        }

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String a = sc.nextLine();
            int r = solve(a);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
