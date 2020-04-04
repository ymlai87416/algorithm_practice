package GoogleCodeJam.Y2016.QualificationRound; /**
 * Created by Tom on 9/4/2016.
 */
/**
 * Created by Tom on 9/4/2016.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Fractiles {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "D-test";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void solve(int k, int comp, int show) {
        ArrayList<Integer> solution = new ArrayList<>();
        List<String> possible = genPossible(k);
        List<String> afterMutate = new ArrayList<String>();

        int[] fill = new int[(int)Math.pow(k, comp)];
        List<String>[] patternElim = new List[(int)Math.pow(k, comp)];
        for(int i=0; i<patternElim.length; ++i){
            patternElim[i] = new ArrayList<String>();
        }

        L(fill, patternElim, 1, comp, possible, k, 0);

        List<String> patternHere = possible;
        for(int i=0; i<show; ++i){
            if(patternHere.size() == 1) break;

            int maxpos=-1;
            List<String> removablePatternMax = new ArrayList<String>();
            for(int j=0; j<patternElim.length; ++j) {
                List<String> removablePattern = new ArrayList<String>();
                for(int kk=0; kk<patternElim[j].size(); ++kk){
                    if(!patternHere.contains(patternElim[j].get(kk))) {
                        removablePattern.add(patternElim[j].get(kk));
                    }
                }
                if(removablePatternMax.size() < removablePatternMax.size()){
                    removablePatternMax = removablePattern;
                    maxpos = j;
                }
            }

            for(String pattern : removablePatternMax)
                patternHere.remove(pattern);
            solution.add(maxpos);
        }

        if(afterMutate.size() == 1){
            for(int i=0; i<solution.size(); ++i)
                out.print(" " + solution.get(i)+1);
            out.println();
        }
        else{
            out.println(" IMPOSSIBLE");
        }
    }


    private List<String> genPossible(int k){
        if(k == 1) return Arrays.asList("G", "L");

        List<String> result = new ArrayList<String>();
        List<String> temp = genPossible(k-1);
        for(int i=0; i<temp.size(); ++i){
            result.add("G" + temp.get(i));
            result.add("L" + temp.get(i));
        }

        return result;
    }

    //have to change to visit id to shit...
    public void L(int[] fill, List<String> patternElim[], int level, int limit, List<String> pattern, int k, int pos){
        System.out.println("calling at pos, level" + pos + " " + level);
        if(level == limit){
            //fill the array here
            for(int i=0; i<k; ++i){
                List<String> temp = new ArrayList<String>();
                for(int j=0; j<pattern.size(); ++j) {
                    String p = pattern.get(j);
                    if (p.charAt(i) == 'L')
                        temp.add(p);
                    fill[pos*k+i] = temp.size();
                    patternElim[pos*k+1] = temp;
                }
            }

            return;
        }
        for(int i=0; i<k; ++i){
            List<String> temp = new ArrayList<String>();
            for(int j=0; j<pattern.size(); ++j) {
                String p = pattern.get(j);
                if (p.charAt(i) == 'L')
                    temp.add(p);

                L(fill, patternElim, level + 1, limit, temp, k, pos*k+i);
            }
        }
    }


    private void run() throws Exception {


        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            int k = sc.nextInt();
            int comp = sc.nextInt();
            int show = sc.nextInt();
            solve(k , comp, show);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Fractiles().run();
    }

}
