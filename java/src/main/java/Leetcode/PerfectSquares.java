    package Leetcode;

import DataStructure.JavaPriorityQueue.UVA1203;

import java.util.HashMap;

/**
number: 279
url: https://leetcode.com/problems/perfect-squares/
level: medium
solution: proof that maximum is 4, but can use dp to speed up

 #math #dp #bfs
 */

public class PerfectSquares {
    public static void main(String[] args) {
        int n=7217;
        Solution sol = new Solution();
        int r = sol.numSquares(n);
        System.out.println(r);
    }

    static
    class Solution {
        //Fastest Solution – Lagrange’s four-square theorem

        int[] dp;
        public int numSquares(int n) {
            if(dp!=null) {
                if(dp.length < n+1) {
                    int[] tdp = dp;
                    dp = new int[n+1];
                    for(int i=0; i<tdp.length; ++i) dp[i] = tdp[i];
                }
            }
            else
                dp = new int[n+1];

            dp[0] = 0;
            dp[1] = 1;
            for(int i=2; i*i<n+1; ++i)
                dp[i*i] = 1;

            return helperIterative(n);
        }

        public int helperIterative(int n){
            for(int i=2; i<=n; ++i){
                if(dp[i] > 0) continue;
                for(int j=1; j*j<=i; ++j){
                    dp[i] = Math.min(dp[i]==0?Integer.MAX_VALUE:dp[i], dp[i-j*j]+1);
                }
            }
            return dp[n];
        }

        public int helperRecursive(int n){
            //System.out.format("%d %d -> %d\n",n,0,0);
            if(dp[n] > 0)
                return dp[n];
            int k = (int)Math.floor(Math.sqrt(n));

            int r = Integer.MAX_VALUE;

            for(int i=k; i>1; --i) {
                int i2 = i*i;
                for (int j = Math.min(r, n/i2); j >0; j -= 1) {  //purning from 1296 -> 166
                    int t = helperRecursive(n - j*i2) + j;
                    if (t < r)
                        r = t;
                }
            }
            r = Math.min(r,n);


            if(dp[n] == 0)
                dp[n] = r;
            return r;
        }
    }
}
