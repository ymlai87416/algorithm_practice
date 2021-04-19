package GoogleCodeJam.Y2018.Round1B.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2018\\Round1B\\A\\A-test.in";
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

    private int solveSmall(int N, int L, int[] resp){
        int cN = 0;
        for (int i = 0; i < resp.length; i++) {
            if(resp[i] == 0) break;
            cN += resp[i];
        }
        return loopHelper(N-cN, 0, resp);
    }

    private int loopHelper(int left, int idx, int[] resp){
        int result= 0;
        if(left == 0){
            int r = 0;
            int N= 0;
            for (int i = 0; i < resp.length; i++) {
                if(resp[i] == 0) break;
                N += resp[i];
            }
            for (int i = 0; i < resp.length; i++) {
                if(resp[i] == 0) break;
                r += round100(resp[i], N);
            }

            String a = "";
            for (int i = 0; i < resp.length; i++) {
                if(resp[i] == 0) break;
                a = a + " " + resp[i];
            }
            if(r==101)
                debug(a);

            return r;
        }

        int i;
        if(resp[idx] == 0) i=1;
        else i=0;

        for (; i <= left; i++) {
            resp[idx] += i;
            int rr = loopHelper(left-i, idx+1, resp);
            result = Math.max(result, rr);
            resp[idx] -= i;
        }
        return result;
    }

    private int solve(int N, int L, int[] resp) {

        int curN =0;
        for (int i = 0; i < L; i++) {
            curN += resp[i];
        }

        if(100%N != 0){
            HashMap<Integer, Integer> tt = new HashMap<>();
            for (int i = 0; i < L; i++) {
                if(tt.containsKey(resp[i]) )
                    leftTo5[i] = tt.get(resp[i]);
                else {
                    int cnt = 0;
                    while (true) {
                        long d = 1000l * (resp[i] + cnt) / N;
                        if (d % 10 >= 5) {
                            tt.put(resp[i], cnt);
                            leftTo5[i] = cnt;
                            break;
                        }
                        ++cnt;
                    }
                }
            }

            int cnt= 0;
            while(true){
                long d = 1000l * (cnt)/ N;
                if(d% 10 >=5) {
                    break;
                }
                ++cnt;
            }

            for (int i = L; i < 100001; i++) {
                leftTo5[i] = cnt;
            }
        }
        else return 100;

        //sort the leftTo5 and we get the result
        Integer[] idx = new Integer[100001];
        for (int i = 0; i < idx.length; i++) {
            idx[i]  =i;
        }
        Arrays.sort(idx, new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                if(leftTo5[o1] == leftTo5[o2])
                    return o1.compareTo(o2);
                else{
                    Integer a =leftTo5[o1];
                    Integer b = leftTo5[o2];
                    return a.compareTo(b);
                }
            }
        });

        int leftToAdd = N-curN;
        int cnt = 0;
        while(leftToAdd != 0){
            if(leftTo5[idx[cnt]] != 0 ) {
                int toAdd = Math.min(leftTo5[idx[cnt]], leftToAdd);
                debug("add " + toAdd + " to idx " + idx[cnt]);
                resp[idx[cnt]] += toAdd;
                leftToAdd -= toAdd;
            }
            cnt++;
        }


        /*
        //add from curN to N
        for (int i = curN+1; i <= N; i++) {
            //find a language to add such that the percentage is like XX.YY% where YY >= 50
            boolean added=false;

            int goodLoc = -1;
            int minTo5 = Integer.MAX_VALUE;

            for (int j = 0; j < L; j++) {
                long d = 1000l * resp[j] / N;
                if(d% 10 >=5) continue;

                d = 1000l * (resp[j]+1) / N;
                if(d% 10 >=5) {
                    resp[j] += 1;
                    added = true;
                    break;
                }

                //find how many left to
                if(leftTo5[j] < minTo5) {
                    goodLoc = j;
                    minTo5 = leftTo5[j];
                }
            }

            if(!added && goodLoc != -1){
                resp[goodLoc] += 1;
                leftTo5[goodLoc] -=1;
                added=true;
            }

            if(!added){
                resp[L] = 1;
                L+=1;
            }
        }*/

        /*
        String a = "";
        for (int i = 0; i < L; i++) {
            a = a + " " + resp[i];
        }
        debug(a);
         */

        int ans = 0;
        for (int i = 0; i < resp.length; i++) {
            if(resp[i] == 0) break;
            int t = round100(resp[i], N);
            //debug("shit " + t);
            ans += t;
        }

        return ans;
    }

    private int round100(int a, int N){
        int d = 1000 * a / N;
        if(d % 10 >=5) return 100*a/N +1;
        else return 100 * a/N;
    }

    int[] resp = new int[100001];
    int[] leftTo5 =new int[100001];
    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        
        if(false){
            //randomly generate case
            Random r = new Random();

            for (int t = 0; t < 1000; t++) {
                int N = r.nextInt(26);
                int left = r.nextInt(N+1);
                int L = 0;
                while(left != 0){
                    int ll= r.nextInt(left)+1;
                    resp[L] =ll;
                    ++L;
                    left -= ll;
                }

                int Nb=N, Lb=L;
                int[] respB = new int[100001];
                for (int i = 0; i < resp.length; i++) {
                    respB[i] = resp[i];
                }

                if(N < 2 || L < 1) continue;

                int aa =solveSmall(N, L, resp);
                int bb= solve(N, L, resp);

                if(aa != bb){
                    debug(Nb + " " + Lb);
                    String kk = "";
                    for (int i = 0; i < L; i++) {
                        kk = kk + respB[i] + " ";
                    }
                    debug(kk);
                    debug("****** ");
                    debug("correct: " + aa + " me: " + bb);
                    debug("******");
                }


            }
            return;
        }
        
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int N= sc.nextInt();
            int L = sc.nextInt();

            for (int j = 0; j < resp.length; j++) {
                resp[j] = 0;
            }
            for (int j = 0; j < L; j++) {
                resp[j] = sc.nextInt();
            }
            out.print("Case #" + i + ": ");
            //out.println(solveSmall(N, L, resp));
            out.println(solve(N, L, resp));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
