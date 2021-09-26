package Facebook.Y2021.Round2;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class A {
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round2\\runway_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round2\\runway_output.txt";
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round2\\A-input.txt";
            //OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round2\\A-output.txt";
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

    private long solve(int N, int M, int[] S, int[][] P) {
        debugflag = true;
        debug("====================");
        boolean[] changed = new boolean[M+1];
        for (int i = 0; i < M+1; i++) {
            changed[i] =false;
        }


        int result = 0;
        //ArrayDeque<Integer> order = new ArrayDeque<>();
        int[] order = new int[2];
        for (int i = 0; i < N; i++) {
            debug("round " + i);

            HashMap<Integer, Set<Integer>> lookup = new HashMap<>();
            TreeSet<Integer> models = new TreeSet<>();
            for (int j = 1; j <= M; j++) {
                int color = changed[j] ? -S[j] : S[j];

                if(lookup.containsKey(color))
                    lookup.get(color).add(j);
                else{
                    TreeSet<Integer> a = new TreeSet<>();
                    a.add(j);
                    lookup.put(color, a);
                }
                models.add(changed[j] ? j: -j);
            }

            TreeSet<Integer> place = new TreeSet<>();
            for (int j = 0; j < M; j++) {
                place.add(j);
            }

            for (int j = 0; j < M; j++) {
                //changed
                if(lookup.containsKey(-P[i][j])){
                    int ok = lookup.get(-P[i][j]).iterator().next();
                    place.remove(j);
                    boolean bok = lookup.get(-P[i][j]).remove(ok);
                    if(lookup.get(-P[i][j]).size() == 0) lookup.remove(-P[i][j]);
                    bok = bok && models.remove(ok);
                    if(!bok) throw new Error("shit");
                    continue;
                }

                //non changed
                if(lookup.containsKey(P[i][j])){
                    int ok = lookup.get(P[i][j]).iterator().next();
                    place.remove(j);
                    boolean bok  = lookup.get(P[i][j]).remove(ok);
                    if(lookup.get(P[i][j]).size() == 0) lookup.remove(P[i][j]);
                    bok = bok && models.remove(-ok);
                    if(!bok) throw new Error("shit");
                }
            }

            for (int j: place) {
                //now we choose an unmatched model for them and not yet prioritized with unchanged first.
                int ok = models.iterator().next();
                int abok = Math.abs(ok);

                int old= S[abok];
                S[abok] = P[i][j];
                models.remove(ok);

                if(!changed[abok]){
                    changed[abok]=true;
                    //debug("update outfit with nowork at " + i + " " + j + " from " + old + " to " + P[i][j]);
                }
                else {
                    //debug("update outfit with work at " + i + " " + j + " from " + old + " to " + P[i][j]);
                    result++;
                }
            }

        }

        
        return result;
    }

    private int silly(int N, int M, int[] S, int[][] P){
        boolean[] changed = new boolean[M];
        for (int i = 0; i < M; i++) {
            changed[i] = true;
        }

        //generate all permutation
        List<List<Integer>> permu = new ArrayList<>();
        int plen = 1;
        for (int i = 1; i <= N; i++) {
            plen *= i;
        }
        /*
        for (int i = 0; i < ; i++) {

        }

        for (int i = 0; i <; i++) {

        }*/
        return 0;
    }

    private void updateSet( HashMap<Integer, Set<Integer>> lookup, int i, int oldOutfit, int newOutfit){
        //change the outfit of I
        lookup.get(oldOutfit).remove(i);
        if(lookup.get(oldOutfit).size() == 0) lookup.remove(oldOutfit);

        //add it to lookup
        if(lookup.containsKey(-newOutfit)){
            lookup.get(-newOutfit).add(i);
        }
        else{
            TreeSet<Integer> t = new TreeSet<>();
            t.add(i);
            lookup.put(-newOutfit, t);
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] S = new int[M+1];
            int[][] P = new int[N][M];

            for (int j = 0; j < M; j++) {
                S[j+1] = sc.nextInt();
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    P[j][k] =sc.nextInt();
                }
            }

            long r = solve(N, M, S, P);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }
}
