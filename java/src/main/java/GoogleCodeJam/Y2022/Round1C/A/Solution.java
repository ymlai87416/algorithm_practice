package GoogleCodeJam.Y2022.Round1C.A;

/**
 * Created by Tom on 30/4/2022.
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/GoogleCodeJam/Y2022/Round1C/A/A-test.in";
            //OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\consistency_chapter_2_output.txt";
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

    private String solve(String[] cw) {
        String ans = "";
        //block, all e, then good
        //if not, then we make sure that when I eat them, i eat them all
        //my goal is have a topological sort of the thing
        int n = cw.length;
        List[]adjList = new List[cw.length];
        int[] type = new int[cw.length];
        int finalLen = 0;
        for (int i = 0; i < n; i++) {
            if(!isValid(cw[i])) {
                return "IMPOSSIBLE";
            }
            if(isFull(cw[i]))
                type[i]= 1;
            else type[i] = 0;

            finalLen += cw[i].length();
        }

        Map<Character, String> mapping = new HashMap<>();
        Map<Character, String> full = new HashMap<>();
        int[] indegree = new int[26];

        for (int i = 0; i < n; i++) {

            if(type[i] == 1){
                char c = cw[i].charAt(0);
                if(full.containsKey(c))
                    full.put(c, full.get(c) + cw[i]);
                else
                    full.put(c, cw[i]);
            }
            else {
                char fc = getFirstChar(cw[i]);
                char lc = getLastChar(cw[i]);
                if(mapping.containsKey(fc))
                    return "IMPOSSIBLE";
                else
                    mapping.put(fc, cw[i]);
                indegree[lc-'A']++;
            }
        }

        boolean[] used = new boolean[26];
        for(int i=0; i<26; ++i) used[i] = false;
        ans = "";


        while(true) {
            //now pick the in-degree = 0 stuff
            String seed = null;
            for (int i = 0; i < 26; i++) {
                char cc = (char)(i+'A');
                if(indegree[i] == 0 && mapping.containsKey(cc)) {
                    seed = mapping.get(cc);
                    mapping.remove(cc);
                    break;
                }
            }

            if(seed == null)
                break;

            char fc = getFirstChar(seed);
            char lc = getLastChar(seed);
            if(full.containsKey(fc)) {
                seed = full.getOrDefault(fc, "") + seed;
                full.remove(fc);
            }
            if(full.containsKey(lc)) {
                seed = seed + full.getOrDefault(lc, "");
                full.remove(lc);
            }

            while(mapping.containsKey(lc)){
                seed += mapping.get(lc);
                lc = getLastChar(seed);
                if(full.containsKey(lc)) {
                    seed = seed + full.getOrDefault(lc, "");
                    full.remove(lc);
                }

            }


            //now look at the last character

            ans = ans + seed;
        }

        for(var s : full.values())
            ans += s;

        if(ans.length() != finalLen)
            return "IMPOSSIBLE";
        if(!isValid(ans))
            return "IMPOSSIBLE";

        return ans;
    }

    private boolean isFull(String a){
        char c = a.charAt(0);
        for(int i=0; i<a.length(); ++i)
            if(a.charAt(i) != c) return false;
        return true;
    }
    private boolean isValid(String a){
        int[] freq = new int[26];
        for (int i = 0; i < 26; i++) {
            freq[i] = 0;
        }
        Character prev = null, cur;
        for(int i=0; i<a.length(); ++i){
            cur  =a.charAt(i);
            int ci = cur-'A';
            freq[ci]++;
        }

        int ptr = 0;

        while(ptr < a.length()){
            char c = a.charAt(ptr);
            int ci = c-'A';
            int freqc = freq[ci];
            for(int i=0; i<freqc; ++i)
                if(a.charAt(ptr+i) != c)
                    return false;
            ptr += freqc;
        }

        return true;
    }

    private char getLastChar(String a){
        return a.charAt(a.length()-1);
    }

    private char getFirstChar(String a ){
        return a.charAt(0);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int c = sc.nextInt();
            String[] cw = new String[c];
            for (int j = 0; j < c; j++) {
                cw[j] = sc.next();
            }
            String r = solve(cw);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    private void test(){
        //System.out.println(isFull("A"));
    }


    public static void main(String args[]) throws Exception {
        new Solution().test();
        new Solution().run();
    }

}

/*
//pick a character which only have 1 string.
            //go as long as it could.
            int seed = -1;
            for(var c : mapping.keySet()){
                int ci = c-'A';
                if(!used[ci] && mapping.get(c).size() == 1) {
                    used[ci] = true;
                    seed = mapping.get(c).get(0);
                    break;
                }
            }

            if(seed == -1) return "IMPOSSIBLE";

            while(true) {
                ans += cw[seed];
                char last = getLastChar(cw[seed]);
                if (full.containsKey(last))
                    ans += full.get(last);

                //now we should see only 1
                if(!mapping.containsKey(last))
                    break;

                if(mapping.get(last).size() > 1)
                    return "IMPOSSIBLE";
                else {
                    used[last-'A'] = true;
                    seed = mapping.get(last).get(0);
                }
            }

 */