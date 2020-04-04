package GoogleCodeJam.Y2017.QualificationRound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ymlai on 8/4/2017.
 */
public class B {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "B-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solveRe(BigInteger N){
        while(true){
            BigInteger rN = solve(N);

            if(rN.compareTo(N) == 0) {
                out.println(rN);
                break;
            }
            else
                N = rN;
        }
    }

    private BigInteger solve(BigInteger N) {
        BigInteger ans = N;

        List<Integer> Ns= new ArrayList<>();
        while(N != BigInteger.ZERO){
            Ns.add(N.mod(BigInteger.TEN).intValue());
            N = N.divide(BigInteger.TEN);
        }

        int curLow = 0;
        int idx = Ns.size()-1;
        for(; idx>=0; --idx){
            if(curLow > Ns.get(idx))
                break;
            else
                curLow = Ns.get(idx);
        }

        if(idx == -1)
            return ans;
        else{
            Ns.set(idx+1, Ns.get(idx+1)-1);

            for(int i=idx; i>=0; --i)
                Ns.set(i, 9);

            ans = BigInteger.ZERO;
            for(int i=0; i<Ns.size(); ++i){
                ans = ans.add(BigInteger.TEN.pow(i).multiply(BigInteger.valueOf(Ns.get(i))));
            }
            return ans;
        }
    }

    private void solveBruteForce(){}


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            BigInteger N = new BigInteger(sc.nextLine());
            solveRe(N);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new B().run();
    }
}
