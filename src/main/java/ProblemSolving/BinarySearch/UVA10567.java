package ProblemSolving.BinarySearch;

import java.util.*;

/**
 * Created by Tom on 8/5/2016.
 */
public class UVA10567 {
    static TreeSet<Integer>[] result = new TreeSet[52];
    static int[] cnt= new int[52];
    static String index = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<result.length; ++i)
            result[i] = new TreeSet<Integer>();

        String a =sc.nextLine();

        for(int i=0; i<a.length(); ++i){
            char c = a.charAt(i);
            int ci = index.indexOf(c);
            result[ci].add(i);
        }

        int nq = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<nq; ++i){
            int minP=0;
            int maxP=0;

            int cur = -1;
            String query = sc.nextLine();
            boolean ok=true;

            for(int j=0; j<query.length(); ++j){
                char c = query.charAt(j);
                int ci = index.indexOf(c);
                Integer highb = result[ci].higher(cur);
                if(highb == null) {
                    ok=false;
                    break;
                }

                cur=highb;
                if(j==0) minP=cur;
                if(j==query.length()-1) maxP = cur;
            }

            if(ok)
                System.out.format("Matched %d %d\n", minP, maxP);
            else
                System.out.println("Not matched");
        }
    }
}
