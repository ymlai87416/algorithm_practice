package GoogleCodeJam.Y2008.Round1C.B;


/**
* Created by Tom on 9/4/2016.
*/
import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;


public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\B\\B-test.in";
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

    ArrayList[][] smallDp;
    String input;

    //Small AC: Big MLE
    private int solveSmall(String input){
        int N = input.length();
        this.smallDp = new ArrayList[N+1][N+1];
        this.input = input;

        ArrayList<Long> allPossible = smallHelper(0, N);

        int ans = 0;
        for(int i=0; i<allPossible.size(); ++i){
            long d = allPossible.get(i);
            if(d %2==0 || d%3==0 || d%5==0 || d%7==0)
                ans+=1;
        }

        return ans;
    }

    //start: inclusive, end:exclusive
    private ArrayList<Long> smallHelper(int start, int end){
        if(start==end) return new ArrayList<Long>();

        if(smallDp[start][end] != null) return smallDp[start][end];

        ArrayList<Long> result = new ArrayList<>();
        //add the full number
        result.add(Long.valueOf(input.substring(start, end)));

        for(int mid=start+1; mid<end; ++mid){
            //first set no change
            long l1 = Long.valueOf(input.substring(start, mid));
            ArrayList<Long> set2 = smallHelper(mid, end);

            for(Long l2: set2){
                result.add(l1+l2);
                result.add(l1-l2);
            }
        }

        smallDp[start][end] = result;
        return result;
    }


    long[][][] bigDp;
    boolean[][] bigDpInit;

    private long solveBig(String input) {
        int N = input.length();
        this.bigDp = new long[N+1][N+1][210];
        this.bigDpInit = new boolean[N+1][N+1];
        this.input = input;

        long[] result = bigHelper(0, N);

        long ans = 0;
        for(int i=0; i<result.length; ++i){
            if(i %2==0 || i%3==0 || i%5==0 || i%7==0)
                ans+=result[i];
        }

        return ans;
    }

    private long[] bigHelper(int start, int end){
        if(start==end) return bigDp[start][end];

        if(bigDpInit[start][end])
            return bigDp[start][end];

        long[] result = bigDp[start][end];
        //add the full number
        BigInteger l = new BigInteger(input.substring(start, end));
        int lr = l.mod(BigInteger.valueOf(210)).intValue();

        result[lr] +=1;

        for(int mid=start+1; mid<end; ++mid){
            //first set no change
            BigInteger l1 = new BigInteger(input.substring(start, mid));
            int l1r = l1.mod(BigInteger.valueOf(210)).intValue();
            long[] set2 = bigHelper(mid, end);

            for (int i = 0; i < set2.length; i++) {
                int rplus = (l1r + i) %210;
                int rminus = (l1r - i)%210;
                while(rminus < 0) rminus+=210;
                result[rplus] += set2[i];
                result[rminus] += set2[i];
            }
        }

        bigDpInit[start][end] = true;

        return result;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String s = sc.nextLine();
            long r;
            //r = solveSmall(s);
            r = solveBig(s);
            System.out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}