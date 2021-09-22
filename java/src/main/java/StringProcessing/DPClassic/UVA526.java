package StringProcessing.DPClassic;

import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 *
 * Edit distance example
 *
 * problem: https://onlinejudge.org/external/5/526.pdf
 * #UVA #Lv3 #string #edit_distance #dp
 */
public class UVA526 {
    static int[][] dp = new int[85][85];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int ncnt = 0;
        while(true){
            if(!sc.hasNext()) break;
            String a = sc.nextLine();
            String b = sc.nextLine();
            if(ncnt != 0) System.out.println();

            //Edit distance
            dp[0][0]= 0;
            for(int i=1; i<=a.length(); ++i)
                dp[i][0] = i;
            for(int i=1; i<=b.length(); ++i)
                dp[0][i] = i;

            for(int i=1; i<=a.length(); ++i)
                for(int j=1; j<=b.length(); ++j){
                    boolean match = a.charAt(i-1) == b.charAt(j-1);
                    dp[i][j] = Math.min(dp[i-1][j-1]+(match?0:1), Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
                }

            /*for(int i=0; i<=a.length(); ++i) {
                for (int j = 0; j <= b.length(); ++j)
                    System.out.print(dp[i][j] + " ");
                System.out.println();
            }*/

            System.out.println(dp[a.length()][b.length()]);

            backtrack(dp, a, b, a.length(), b.length());

            ++ncnt;
        }
    }

    static int backtrack(int[][]dp, String a, String b, int i, int j){
        if(i == 0 && j == 0) return 0;
        if(i > 0 && j > 0 && a.charAt(i-1) == b.charAt(j-1) && dp[i-1][j-1] == dp[i][j]) {
            return backtrack(dp, a, b, i - 1, j - 1);
        }
        else if(i > 0 && j > 0 && a.charAt(i-1) != b.charAt(j-1) && dp[i-1][j-1]+1 == dp[i][j]) {
            int result = backtrack(dp, a, b, i - 1, j - 1);
            System.out.format("%d Replace %d,%s\n", dp[i][j], i+result, b.charAt(j-1));
            return result;
        }
        else if(i > 0 && dp[i-1][j]+1 == dp[i][j]) {
            int result = backtrack(dp, a, b, i-1, j);
            System.out.format("%d Delete %d\n", dp[i][j], i+result);
            return result-1;
        }
        else if(j > 0 && dp[i][j-1]+1 == dp[i][j]) {
            int result = backtrack(dp, a, b, i, j-1);
            System.out.format("%d Insert %d,%s\n", dp[i][j], i+1+result, b.charAt(j-1));
            return result+1;
        }
        return -1;
    }
}
