package GoogleCodeJam.Y2011.Round1C.C;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1C\\C\\C-test.in";
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

    private BigInteger gcd(BigInteger a, BigInteger b){
        if (a.mod(b).compareTo(BigInteger.ZERO) == 0) return b;
        else return gcd(b, a.mod(b));
    }

    BigInteger[] lcm = new BigInteger[10001];
    BigInteger[] gcd = new BigInteger[10001];

    private BigInteger solve(int N, long L, long H, long[] freq) {

        //the result must be multiple of lcm (f_1 ... f_k) and multiple of gcd(f_k+1 ... f_N)
        Arrays.sort(freq);

        //all one return L
        boolean allOne = true;
        for (int i = 0; i < N; i++) {
            if(freq[i] != 1){
                allOne =false;
                break;
            }
        }
        if(allOne) return BigInteger.valueOf(L);

        BigInteger gcda = null;
        BigInteger product = BigInteger.ONE;
        for (int i = 0; i < N; i++) {
            product = product.multiply(BigInteger.valueOf(freq[i]));
            gcda = gcda == null ? BigInteger.valueOf(freq[i]) : gcd(BigInteger.valueOf(freq[i]), gcda);
            lcm[i] = i==0 ? BigInteger.valueOf(freq[i]) : product.divide(gcda);
        }

        gcda = null;
        for (int i = N-1; i >=0 ; i--) {
            gcda = gcda== null ? BigInteger.valueOf(freq[i]) : gcd(gcda, BigInteger.valueOf(freq[i]));
            gcd[i] = gcda;
        }

        BigInteger ans = null;
        //now try for each N
        for (int i = 0; i <= N; i++) {
            //from 0 ... i-1 lcm
            //from i ... N-1 gcd
            //F must be M * LCM and GCD / N

            BigInteger lcm_ = i==0? BigInteger.ONE: lcm[i-1];
            BigInteger gcd_ = i==N ? BigInteger.ONE: gcd[i];

            if(i!=N && gcd_.compareTo(BigInteger.ONE) == 0)
                continue;

            BigInteger a = lcm_;
            BigInteger b = gcd_;

            BigInteger Lb = BigInteger.valueOf(L);
            BigInteger Hb = BigInteger.valueOf(H);

            if(i== 0){
                //find a M * gcd between L and H

            }
            else if(i == N){

            }


            //find the LCM of a and b, and multiply it above L and below H
            BigInteger prod2 = lcm_.multiply(gcd_);
            BigInteger gcd2 = gcd(lcm_, gcd_);
            BigInteger vv = prod2.divide(gcd2);

            BigInteger temp = null;
            if(vv.compareTo(Hb) > 0) continue;
            if(vv.compareTo(Lb) < 0){
                if(Lb.mod(vv).compareTo(BigInteger.ZERO) == 0){
                    temp = Lb;
                }
                BigInteger v = Lb.divide(vv);
                BigInteger vvv = vv.multiply(v.add(BigInteger.ONE));
                if(vvv.compareTo(Hb) <= 0) temp = vvv;
            }
            if(temp==null) continue;

            if(ans == null || ans.compareTo(temp) > 0)
                ans=temp;
        }

        return ans;

    }
    /*
    public int findFactorWithin(int low, int high, int curr, ArrayList<Pair> ai, int ptr){
        if(low <= curr && curr <=high){
            return curr;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= ai.get(ptr).second; i++) {
            int t = findFactorWithin(low, high, curr, ai, ptr+1);
            if(min > t)
                min = t;
            curr *= ai.get(ptr).first;
        }
        return min;
    }


    public void factorize (int N, ArrayList<Pair> a){
        int cnt = 0;
        while(N%2==0) {
            N/=2;
            cnt++;
        }
        if(cnt != 0)
        a.add(new Pair(2, cnt));

        for (int i = 3; i <= Math.sqrt(N); i+=2) {
            cnt= 0;
            while(N%i==0){
                ++cnt;
                N/=i;
            }
            if(cnt!=0)
                a.add(new Pair(i, cnt));
        }
        if(N!= 1)
            a.add(new Pair(N, 1));

        return;
    }
    static class Pair{
        int first; int second;
        public Pair(int f, int s){ this.first = f; this.second =s ;}
    }
     */



    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N  =sc.nextInt();
            long L = sc.nextLong();
            long H = sc.nextLong();
            long[] freq = new long[N];
            for (int j = 0; j < N; j++) {
                freq[j] = sc.nextLong();
            }
            BigInteger r = solve(N, L, H, freq);
            if(r == null) out.println("NO");
            else out.println(r.toString());
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
