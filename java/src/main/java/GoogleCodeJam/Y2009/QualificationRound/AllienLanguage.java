package GoogleCodeJam.Y2009.QualificationRound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Tom on 30/4/2016.
 */
public class AllienLanguage {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    ArrayList<String> dict;
    char[][] dictReverse = new char[16][26];

    private void solve(int l, int d) {
        ArrayList<String> input = new ArrayList<String>();

        String s = sc.nextLine();

        boolean bracketStart = false;
        String temp="";
        for(int i=0; i<s.length(); ++i){
            char cs = s.charAt(i);

            if(bracketStart){
                if(cs == ')'){
                    bracketStart = false;
                    input.add(temp);
                } else{
                    temp += cs;
                }
            } else {
                if(cs == '('){
                    bracketStart = true;
                    temp= "";
                } else {
                    input.add(String.valueOf(cs));
                }
            }
        }

        ArrayList<String> possible = new ArrayList<String>(dict);
        ArrayList<String> removeList = new ArrayList<String>();

        for(int i=0; i<l; ++i){
            removeList.clear();
            String c = input.get(i);
            for(String k : possible){
                boolean hasChar = false;
                for(int j=0; j<c.length(); ++j){
                    if(k.charAt(i) == c.charAt(j)) {
                        hasChar = true;
                        break;
                    }
                }
                if(!hasChar)
                    removeList.add(k);
            }

            for(String ss : removeList)
                possible.remove(ss);
        }

        out.println(possible.size());
    }


    public int numValidCombination(ArrayList<String> input, int i, char[] cc, int l){
        if(i == l){
            StringBuilder sb = new StringBuilder();
            sb.append(cc);
            if(dict.contains(sb.toString())) return 1;
            else return 0;
        }
        String cur = input.get(i);
        int result= 0;
        for(int j=0; j<cur.length(); ++j){
            char x = cur.charAt(j);
            if(dictReverse[i][x-'a'] == 0) continue;
            cc[i] = x;
            result += numValidCombination(input, i+1, cc, l);
        }
        return result;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int len = sc.nextInt();
        dict = new ArrayList<String>();
        int nword = sc.nextInt();

        int t = sc.nextInt();
        sc.nextLine();

        for(int i=0; i<nword; ++i)
            dict.add(sc.nextLine());

        for(int i=0; i<16; ++i) Arrays.fill(dictReverse[i], (char)0);
        for(int i=0; i<dict.size(); ++i) {
            String xx = dict.get(i);
            for(int j=0; j<xx.length(); ++j){
                char xy = xx.charAt(j);
                dictReverse[j][xy-'a'] = (char)1;
            }
        }

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            solve(len, nword);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new AllienLanguage().run();
    }
}
