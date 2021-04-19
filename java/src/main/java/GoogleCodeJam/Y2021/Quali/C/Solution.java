package GoogleCodeJam.Y2021.Quali.C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Quali\\C\\C-test.in";
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

    //l => cost max = v.length - l
    //constraint, t must be at least = v.length-l
    private boolean helper2(int[] v, int l, int t){
        //System.out.println("F: " + l + " " + t);

        int leftLen = v.length - l;
        int minPossibleCost = leftLen-1;

        if(t < minPossibleCost)
            return false;
        if(leftLen == 1 && t == 0) {
            v[l] = l+1;
            return true;
        }

        int maxCost = v.length - l;
        int minNextT = v.length - l - 2;

        if(t - maxCost > minNextT) {
            v[l] = l+1;
            helper2(v, l+1, t-maxCost);
            //swap here
            for (int i = l, j= v.length-1; i < j ; i++, --j) {
                int tt = v[i];
                v[i] = v[j];
                v[j] = tt;
            }
        }
        else {
            int costSpend = t- minNextT;

            v[l] = l+1;
            helper2(v, l+1, minNextT);
            //swap here
            for (int i = l, j= l+costSpend-1; i < j ; i++, --j) {
                int tt = v[i];
                v[i] = v[j];
                v[j] = tt;
            }
        }

        return true;
    }


    //s inclusive, e exclusive
    private void helper(int[] v, int s, int e, int l, int t){
        if(t == e-s) {
            for (int i = s; i < e; i++) {
                v[i] = l + (i - s);
            }
        }
        else if(t > e-s){
            int remain = t - (e-s);
            int cost = Math.min(remain, e-s);
            int ll = l;
            for (int i = t-1; i >=s; i--) {
                v[i] = ll++;
            }
            for (int i = t; i < e; i++) {
                v[i] = ll++;
            }
            helper(v, s, e-1, l+1, t-cost);
        }
        /*
        else{
            if(t < e-s){
                //end here
                int ll = l;
                for (int i = t-1; i >=s; i--) {
                    v[i] = ll++;
                }
                for (int i = t; i < e; i++) {
                    v[i] = ll++;
                }
            }
            else {
                helper(v, s, e-1, l+1, t-(e-s));
                v[e-1] = l;
            }
        }*/
    }

    private int reverseSort(int[] v){
        int cost = 0;
        for (int i = 0; i < v.length -1; i++) {
            int minpos = i;
            int minv = v[i];
            for (int j = i+1; j < v.length; j++) {
                if(v[j] < minv){
                    minv = v[j];
                    minpos = j;
                }
            }

            cost += minpos-i + 1;
            for (int j = i ,k=minpos; j<k; j++, k--) {
                int t = v[j];
                v[j] = v[k];
                v[k] = t;
            }

            /*System.out.print("D: ");
            for (int j = 0; j < v.length; j++) {
                System.out.print(v[j] + " ");
            }
            System.out.println();*/
        }

        return cost;
    }

    private int[] solve(int N, int C) {
        int ans = 0;
        int[] v = new int[N];
        if (C < N-1)
            return null;
        else if(C > (N * (N+1))/2 -1)
            return null;
        else
            helper2(v, 0, C);

        //test
        int[] tv = new int[v.length];
        for (int i = 0; i < v.length; i++) {
            tv[i] = v[i];
        }
        int a = reverseSort(tv);

        if(a != C) {
            System.out.println("Shit Actual: " + a + " Need: " + C);
            for (int i = 0; i < v.length; i++) {
                System.out.print(v[i]);
            }
            System.out.println();
        }

        return v;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            int N = sc.nextInt();
            int C = sc.nextInt();
            int[] test = solve(N, C);
            if(test == null)
                System.out.println(" IMPOSSIBLE");
            else {
                for (int j = 0; j < test.length; j++) {
                    System.out.print(" " + test[j]);
                }
                System.out.println();
            }
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}