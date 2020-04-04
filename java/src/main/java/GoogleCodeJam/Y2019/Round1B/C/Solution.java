package GoogleCodeJam.Y2019.Round1B.C;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
            IN = null;
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

    private int solve(int n, int k, int[] C, int[] D, Integer[] c1, Integer[] d1)
    {
        int result = 0;
        for(int i=0; i<n; ++i){
            for(int j=i; j<n; ++j){
                int start = i;
                int end = j;

                int cm = 0, dm = 0;
                for(int u=0; u<n; ++u)
                    if(start <=c1[u] && c1[u] <= end){
                        cm = C[c1[u]];
                        break;
                    }

                for(int u=0; u<n; ++u)
                    if(start <=d1[u] && d1[u] <= end){
                        dm = D[d1[u]];
                        break;
                    }

                if(Math.abs(cm-dm) <=k)
                    result = result+1;
            }
        }

        return result;
    }



    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] C; int[] D;

            C = new int[n];
            D = new int[n];

            for (int j=0; j<n; ++j) {
                C[j] = sc.nextInt();
            }

            ArrayIndexComparator comparator = new ArrayIndexComparator(C);
            Integer[] c1 = comparator.createIndexArray();
            Arrays.sort(c1, comparator);

            for(int j=0; j<n; ++j){
                D[j] = sc.nextInt();
            }

            comparator = new ArrayIndexComparator(D);
            Integer[] d1 = comparator.createIndexArray();
            Arrays.sort(d1, comparator);

            out.println(solve(n, k, C, D, c1, d1));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}


class ArrayIndexComparator implements Comparator<Integer>
{
    private final int[] array;

    public ArrayIndexComparator(int[] array)
    {
        this.array = array;
    }

    public Integer[] createIndexArray()
    {
        Integer[] indexes = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
        {
            indexes[i] = i; // Autoboxing
        }
        return indexes;
    }

    @Override
    public int compare(Integer index1, Integer index2)
    {
        // Autounbox from Integer to int to use as array indexes
        return array[index2] - (array[index1]);
    }
}