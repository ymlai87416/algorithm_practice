package GoogleCodeJam.Y2016.QualificationRound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tom on 9/4/2016.
 */
public class RevengeOfThePancakes {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "B-small-attempt0";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true)); ;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    static boolean[] cakeTemp;
    public void flip(boolean[] cake, int start, int end){
        if(cakeTemp == null || cakeTemp.length != cake.length) cakeTemp = new boolean[cake.length];
        for(int i=0; i<cake.length; ++i) cakeTemp[i] = cake[i];

        for(int i=start, j=end-1; i<end; ++i, --j)
            cake[i] = !cakeTemp[j];
    }

    ArrayList<boolean[]> states;
    public boolean checkSame(boolean[] b){
        for(int j=0; j<states.size(); ++j){
            boolean[] a = states.get(j);
            boolean result = true;
            for(int i=0; i<a.length; ++i)
                result = result && (a[i] == b[i]);
            if(result) return result;
        }
        return false;
    }

    public int solveRe(boolean[] bcake, int move){
        int effSize = effectiveSize(bcake);
        if(effSize == 0) return move;

        /*
        System.out.println("Effective Size: " + effSize);
        for(int i=0; i<bcake.length; ++i)
            System.out.print(bcake[i] ? "+" : "-");
        System.out.println();*/

        int mresult = Integer.MAX_VALUE;
        boolean[] orig = bcake.clone();
        states.add(orig);
        //flip from the back
        int endPos = bcake.length;
        int startPos = 0;
        for(; startPos < endPos; --endPos) {
            flip(bcake, startPos, endPos);
            if(checkSame(bcake)) {
                flip(bcake, startPos, endPos);
                continue;
            }
            int result = solveRe(bcake, move+1);
            if(mresult > result)
                mresult = result;
            flip(bcake, startPos, endPos); //flip it back
        }

        return mresult;
    }

    private int effectiveSize(boolean[] bcakes){
        int effSize = bcakes.length;
        for(int i=bcakes.length-1; i >= 0; --i){
            if(!bcakes[i])
                break;
            --effSize;
        }
        return effSize;
    }

    private void solve(String cakes){
        //flip until the bottom is good
        boolean[] bcake = new boolean[cakes.length()];
        for(int i=0; i<cakes.length(); ++i)
            if(cakes.charAt(i) == '+') bcake[i] = true;
            else if (cakes.charAt(i) == '-') bcake[i] = false;

        states = new ArrayList<boolean[]>();

        int cnt = solveRe(bcake, 0);

        out.println(cnt);
        //System.out.println(cnt);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        boolean[] test = new boolean[] { false, false, true, false};
        flip(test, 0, 3);

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String cake = sc.nextLine();
            solve(cake);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new RevengeOfThePancakes().run();
    }
}
