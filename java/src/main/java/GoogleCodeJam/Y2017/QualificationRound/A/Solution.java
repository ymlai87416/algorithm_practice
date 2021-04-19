package GoogleCodeJam.Y2017.QualificationRound.A;


import java.io.File;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by ymlai on 8/4/2017.
 *
 * review: so silly you cannot know this is a greedy algorithm ...
 */
public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2017\\QualificationRound\\A\\A-test.in";
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

    Long INF = Long.MAX_VALUE / 2;

    Map<BitSet, Long> dp;
    int K;
    int SLen;
    Set<BitSet> track;

    private void solve(BitSet S, int flipK, int slen) {
        long ans = 0;

        dp = new HashMap<BitSet, Long>();
        K = flipK;
        SLen = slen;
        track = new HashSet<BitSet>();
        track.add(S);

        ans = solveHelper(S);

        if(ans == INF)
            out.println("IMPOSSIBLE");
        else
            out.println(ans);
    }


    private void solveBig(BitSet S, int K, int slen){
        long ans = 0;
        for (int i = 0; i <= slen-K; i++) {
            if(!S.get(i)){
                for (int j = 0; j < K; j++) {
                    S.flip(i+j);
                }
                ans++;
            }
        }

        for (int i = slen-K+1; i < slen; i++) {
            if(!S.get(i))
                ans = INF;
        }

        if(ans == INF)
            out.println("IMPOSSIBLE");
        else
            out.println(ans);
    }

    private long solveHelper(BitSet S){
        //System.out.println(S.toString() + " lvl: " + lvl);
        if(dp.containsKey(S))
            return dp.get(S);

        boolean allset = true;
        for(int i=0; i<SLen; ++i)
            allset = allset && S.get(i);

        if(allset)
            return 0;

        long minflip = INF;

        int idx = 0;
        for(; idx<=SLen-K; ++idx)
            if(!S.get(idx))
                break;

        if(idx <= SLen-K){
            track.add(S);
            S.flip(idx, idx+K);
            minflip = Math.min(minflip, solveHelper(S) + 1);
        }

        /*for(; idx<=SLen-K; ++idx){
            S.flip(idx, idx+K);
            if(!track.contains(S)) {

                track.remove(S);
            }
            S.flip(idx, idx+K);
        }*/

        dp.put(S, minflip);
        return minflip;
    }

    private void solveBruteForce(){}
    

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String line = sc.nextLine();

            StringTokenizer st = new StringTokenizer(line, " ");
            String Sstr = st.nextToken();

            int K = Integer.parseInt(st.nextToken());

            BitSet S = new BitSet(Sstr.length());
            for(int j=0; j<Sstr.length(); ++j){
                if(Sstr.charAt(j) == '+')
                    S.set(j);
                else
                    S.clear(j);
            }

            solveBig(S, K, Sstr.length());
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
