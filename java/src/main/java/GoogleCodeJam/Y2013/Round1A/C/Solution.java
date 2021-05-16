package GoogleCodeJam.Y2013.Round1A.C;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

/*
    AC for small.

 */
public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2013\\Round1A\\C\\C-test.in";
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

    private int[][] solve(int R, int N, int M, int K, int[][] test) {
        int[][] ans = new int[R][];
        HashMap<Set, HashMap<Integer, Double>> lookup;
        HashMap<Integer, Double> evidence;
        //generate all the frequency by sampling and then we can solve the equation
        if(N==3) {
            lookup = m3;
            evidence = evidence3;
        }
        else {
            lookup = m12;
            evidence = evidence12;
        }

        
        HashMap<Integer, Double> freqTable = new HashMap<>();
        for (int i = 0; i < R; i++) {
            freqTable.clear();
            for (int j = 0; j < K; j++) {
                if(freqTable.containsKey(test[i][j]))
                    freqTable.put(test[i][j], freqTable.get(test[i][j])+1./K);
                else
                    freqTable.put(test[i][j], 1./K);
            }

            //now loop every set of lookup and find the possible shit
            double maxProb = 0;
            Set maxSet = null;
            for(Set ss: lookup.keySet()){
                HashMap<Integer, Double> shit = lookup.get(ss);
                double norm=1;
                double denorm =1;

                for (int j = 0; j < test[i].length; j++) {
                    int curN = test[i][j];
                    norm = norm * (shit.containsKey(curN) ? shit.get(curN) : 0);
                    denorm = denorm *evidence.get(curN);
                }

                double finalp = norm/denorm;
                if(finalp > maxProb){
                    maxProb = finalp;
                    maxSet = ss;
                }
            }

            ans[i] = maxSet.a;
        }

        return ans;
    }

    HashMap<Set, HashMap<Integer, Double>> m3 = new HashMap<>();
    HashMap<Set, Double> probSet3 = new HashMap<>();
    HashMap<Integer, Double> evidence3;
    HashMap<Set, HashMap<Integer, Double>> m12 = new HashMap<>();
    HashMap<Set, Double> probSet12 = new HashMap<>();
    HashMap<Integer, Double> evidence12;

    long total3 = 64;
    long total12 = 13841287201l;

    static class Set{
        int[] a;
        public Set(int[] a){
            this.a = a.clone();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Set){
                Set os = (Set)obj;
                if(os.a.length != a.length) return false;
                for (int i = 0; i < os.a.length; i++) {
                    if(os.a[i] != a[i]) return false;
                }
                return true;
            }
            else return false;
        }

        @Override
        public int hashCode() {
            long l = 0;
            for (int i = 0; i < a.length; i++) {
                l = l*157 + a[i];
            }
            return Long.valueOf(l).hashCode();
        }
    }

    private double prob(int[] e){
        long comb = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        int start = 0;
        for (int i = 1; i < e.length; i++) {
            if(e[i] != e[i-1]) {
                freq.put(e[i - 1], i-start);
                start = i;
            }
        }
        freq.put(e[e.length-1], e.length-start);

        //now combination
        int hole = e.length;
        comb = 1;
        for (Integer fi: freq.keySet()) {
            int n = hole;
            int r = freq.get(fi);
            BigInteger result = factorial(n).divide(factorial(r).multiply(factorial(n-r)));
            comb = comb * result.longValue();
            hole -= r;
        }

        long total = e.length == 3 ? total3 : total12;
        double r = comb;
        r = r/total;
        return r;
    }

    static BigInteger factorial(int n){
        BigInteger result = BigInteger.ONE;
        for(int i=2; i<=n; ++i){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    private void prepareProb(int level, int[] elements, int minv, int maxv){

        if(level == elements.length){

            if(level==3) {
                HashMap<Integer, Double> a = sampling(elements);
                double probS = prob(elements);
                Set s = new Set(elements);
                m3.put(s, a);
                probSet3.put(s, probS);
            }
            else {
                //total = 2^8 = 256
                HashMap<Integer, Double> a = sampling(elements);
                Set s = new Set(elements);
                double probS = prob(elements);
                m12.put(s, a);
                probSet12.put(s, probS);
            }

            return;
        }

        int minv2 = level==0? minv: elements[level-1];
        for (int i = minv2; i <= maxv; i++) {
            elements[level] = i;
            prepareProb(level+1, elements, minv, maxv);
        }
    }

    private HashMap<Integer, Double> sampling(int[] a){
        HashMap<Integer, Double> result = new HashMap<>();
        for (int j = 0; j < Math.pow(2, a.length); j++) {
            int curj = j;
            double delta = 1/Math.pow(2, a.length);
            int p = 1;
            for (int i = 0; i < a.length; i++) {
                if(curj %2 == 1)
                    p *= a[i];
                curj /= 2;
            }
            if(result.containsKey(p))
                result.put(p, result.get(p)+delta);
            else
                result.put(p, delta);
        }

        return result;
    }

    //need to shit
    private HashMap<Integer, Double> prepareEvidence(HashMap<Set, HashMap<Integer, Double>> lookup, HashMap<Set, Double> probSet){
        HashMap<Integer, Double> result =new HashMap<>();

        for (Set ss: lookup.keySet()) {
            double probS = probSet.get(ss);
            for(Integer ii: lookup.get(ss).keySet()){
                double prob = probS * lookup.get(ss).get(ii);

                if(result.containsKey(ii))
                    result.put(ii, result.get(ii)+prob);
                else
                    result.put(ii, prob);
            }
        }

        return result;
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        prepareProb(0, new int[3], 2, 5);
        //prepareProb(0, new int[12], 2, 8);

        evidence3 = prepareEvidence(m3, probSet3);
        //evidence12 = prepareEvidence(m12, probSet12);

        //debug
        /*
        for (Set ss: probSet3.keySet()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ss.a.length; i++) {
                sb.append(ss.a[i] + " ");
            }
            debug(sb.toString() , probSet3.get(ss));
        }

        for (Integer ss: evidence3.keySet()) {
            debug(ss, evidence3.get(ss));
        }

         */

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.println("Case #" + i + ": ");
            int R = sc.nextInt();
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K= sc.nextInt();
            int[][] test=  new int[R][K];
            for (int j = 0; j < R; j++) {

                for (int k = 0; k < K; k++) {
                    test[j][k] = sc.nextInt();
                }
            }
            
            int[][] r = solve(R, N, M, K, test);
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < N; k++) {
                    out.print(r[j][k]);
                }
                out.println();
            }

        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
