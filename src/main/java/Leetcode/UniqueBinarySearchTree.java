package Leetcode;

public class UniqueBinarySearchTree {
    public static void main(String[] args) {
        int n = 3;
        Solution s = new Solution();
        System.out.println(s.numTrees(3));
    }

    static
    class Solution {
        int[][] dp = new int[100][100];

        public int numTrees(int n) {
            for(int i=0; i<100; ++i)
                for(int j=0; j<100; ++j)
                    dp[i][j] = -1;
            return numTreesHelper(n, 0);
        }

        public int numTreesHelper(int left, int right){
            //simpler appoarch is to multiply... dp[3] =
            if(left < 100 && right < 1000 && dp[left][right] != -1) return dp[left][right];

            int resultL = 0;
            if(left == 1 || left == 0) resultL = 1;
            else {
                for (int i = 0; i < left; ++i)
                    resultL += numTreesHelper(i, left-i-1);
            }
            int resultR = 0;
            if(right == 1 || right == 0) resultR = 1;
            else {
                for (int i = 0; i < right; ++i)
                    resultR += numTreesHelper(i, right-i-1);
            }

            int result = resultL * resultR;

            //System.out.format("%d %d: %d\n", left, right, result);
            dp[left][right] = result;
            return result;
        }
    }
}
