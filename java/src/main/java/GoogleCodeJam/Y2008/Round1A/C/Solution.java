package GoogleCodeJam.Y2008.Round1A.C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Tom on 5/5/2016.
 *
 * #Satisfiability
 */
public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "A-test";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";

            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1A\\C\\C-test.in";
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

    private int[] solveSmall(int N, int M, int[][] a){
        int[] result = new int[N];
        int mresult = -1;
        int[] result2 = new int[N];
        int mresult2 = 0;

        for(int i=0; i<Math.pow(2, N); ++i){

            int ii = i;
            for(int j=0; j<N; ++j){
                result2[j] = ii%2;
                ii = ii/2;
            }

            boolean allGood = true;
            for(int j=0; j<M; ++j){
                //if not related, 0, unmalted = 1, malted = 2;
                boolean hasOne = false;
                for(int k=0; k<N; ++k){
                    if(a[j][k] == 1 && result2[k] == 0)
                        hasOne = true;
                    else if(a[j][k] == 2 && result2[k] == 1)
                        hasOne = true;
                }
                if(!hasOne)
                    allGood = false;
            }

            if(allGood) {

                mresult2 = 0;
                for (int j = 0; j < N; ++j) {
                    if (result2[j] == 1) mresult2 += 1;
                }

                //if null duplicate, else copy
                if (mresult == -1 || (mresult > mresult2)) {
                    mresult = mresult2;
                    for (int j = 0; j < N; ++j)
                        result[j] = result2[j];
                }
            }

        }

        if(mresult == -1) return null;
        return result;
    }


    //wrong
    private int[] solveBig(int N, int M, int[][] a) {
        //horn statifiable
        /*
            1. set all to unmalted and if ok, return
            2. for each unsatisfiable clause, then change it to malt. (only 1 path) and check
            3. after processing all with malted requirement and all good, then return answer

            unmalted = goal
            with 1 malted and other unmatled = definite
            with 1 malted = fact
         */

        ArrayList<Integer> maltedCustomer = new ArrayList<>();
        ArrayList<Integer> unmaltedCustomer = new ArrayList<>();
        for(int i=0; i<M; ++i){
            boolean hasMalted = false;
            for (int j = 0; j <N ; j++) {
                if (a[i][j] == 2) {
                    hasMalted = true;
                    maltedCustomer.add(i);
                    break;
                }
            }
            if(!hasMalted)
                unmaltedCustomer.add(i);
        }

        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = 0;
        }

        while(true){
            //check all
            boolean ok;
            for (int j = 0; j < unmaltedCustomer.size(); j++) {
                int c = unmaltedCustomer.get(j);
                ok = false;
                for (int k = 0; k < N; k++) {
                    if (a[c][k] == 1 && result[k] == 0) {
                        ok = true;
                        break;
                    }
                }
                if (!ok)
                    return null;
            }

            boolean changed = false;
            for (int i = 0; i < maltedCustomer.size(); i++) {
                int m = maltedCustomer.get(i);

                //check if satisfy
                int maltFavor = -1;
                ok = false;
                for (int j = 0; j < N; j++) {
                    if(a[m][j] == 2)
                        maltFavor = j;
                    if(a[m][j] == 1 && result[j] == 0){
                        ok = true;
                        break;
                    } else if(a[m][j] == 2 && result[j] == 1){
                        ok = true; break;
                    }
                }

                if(!ok) {
                    changed = true;
                    result[maltFavor] = 1;
                    break;
                }
            }

            if(changed) continue;
            else return result;
        }

    }

    private void generateTestCase(){
        Random random = new Random();
        int N = random.nextInt(10+1);
        int M = random.nextInt(100+1);

        System.out.println(N);
        System.out.println(M);

        //create an array of 1 to N
        List<Integer> a = IntStream.range(1, N+1).boxed().collect(Collectors.toList());

        for(int i=0; i<M; ++i){
            int acount = random.nextInt(N)+1;
            System.out.format("%d ", acount);
            Collections.shuffle(a);
            boolean hasm = false;
            for (int j = 0; j < acount; j++) {
                int malted = 0;
                if(!hasm && Math.random() < 0.2){
                    hasm = true;
                    malted = 1;
                }
                int b = a.get(j);
                System.out.format("%d %d ", b, malted);
            }
            System.out.println();
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        if(false){
            for(int i=0; i<10; ++i) {
                generateTestCase();

            }
            return;
        }

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] a = new int[M][N];

            for (int j = 0; j < M; j++) {
                int alen = sc.nextInt();
                for (int k = 0; k < alen; k++) {
                    int p = sc.nextInt()-1;
                    int q = sc.nextInt();
                    a[j][p] = (q==0) ? 1: 2;
                }
            }

            //int[] r = solveSmall(N, M, a);
            int[] r = solveBig(N, M, a);
            if(r == null)
                out.println("IMPOSSIBLE");
            else{
                for (int j = 0; j < N; j++) {
                    out.print(r[j]);
                    if(j != N-1) out.print(" ");
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
