package ProblemSolving.Knapsack;

import java.util.Arrays;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/108/10819.pdf
 level:
 solution: Thinking for day, just circumvent the TLE using difference case. instead of formula a general case... time spends: 1 day.

 #dp #01_knapsack #UVA #Lv2

 **/
public class UVA10819 {

    static int[][] dptable = new int[10200+1][100+1];
    static int[] price = new int[100+1];
    static int[] index = new int[100+1];

    public static int dp(int money, int obj,  int limit){
        if(obj == 0)
            return 0;

        if(dptable[money][obj] != 0)
            return dptable[money][obj];

        int temp = money+price[obj];
        if(temp > 2000){
            if(temp > limit+200)
                return dp(money, obj-1, limit);
            else
                return  Math.max(dp(money + price[obj], obj-1, limit)+index[obj], dp(money, obj-1, limit));
        } else{
            if(temp > limit)
                return dp(money, obj-1, limit);
            else
                return  Math.max(dp(money + price[obj], obj-1,limit)+index[obj], dp(money, obj-1, limit));
        }
    }

    public static int dpSmall(int m, int n){
        for(int i=1; i<=n; ++i){
            for(int j=0; j<=m; ++j){
                if(j-price[i] < 0)
                    dptable[j][i] = dptable[j][i-1];
                else
                    dptable[j][i] = Math.max(dptable[j-price[i]][i-1]+index[i], dptable[j][i-1]);
            }
        }
        return dptable[m][n];
    }

    public static int dpBig(int m, int n){
        for(int i=1; i<=n; ++i){
            for(int j=0; j<=m+200; ++j){
                if(j-price[i] < 0)
                    dptable[j][i] = dptable[j][i-1];
                else
                    dptable[j][i] = Math.max(dptable[j-price[i]][i-1]+index[i], dptable[j][i-1]);
            }
        }
        return dptable[m+200][n];
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            int m = sc.nextInt();
            int n = sc.nextInt();
            sc.nextLine();

            for(int i=0; i<10000+1; ++i) {
                Arrays.fill(dptable[i], 0);
                dptable[i][0] = 0;
            }
            //base case is 0.

            for(int i=1; i<=n; ++i){
                price[i] = sc.nextInt();
                index[i] = sc.nextInt();
            }

            int result=0;
            if(m < 1801)
                result = dpSmall(m,  n);
            else if( m >= 1801 && m <= 2000)
                result = dp(0, n, m);
            else
                result = dpBig(m, n);

            /*for(int i=1; i<=n; ++i){
                //chose it, or not choose it
                for(int j=0; j<=m+200; ++j){
                    System.out.print(dptable[j][i] + " ");
                }
                System.out.println();
            }*/

            System.out.println(result);
        }
    }
}
