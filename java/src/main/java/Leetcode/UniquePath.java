package Leetcode;

/*
number: 62
url: https://leetcode.com/problems/unique-paths/
level: medium
solution: robot go n-1 left and m-1 down. => slot = (n+m-2) => how to assign n-1
 */

public class UniquePath {
    public static void main(String[] args){
        int m=10;
        int n=1;
        Solution s = new Solution();
        System.out.println(s.uniquePaths(m, n));
    }


    static
    class Solution {
        public int uniquePaths(int m, int n) {
            return (int)C(m+n-2, Math.max(m,n)-1);
        }

        private long C(int n, int r) {
            long denom, numero;
            denom = 1; numero =1;
            for(int i=0; i<n-r; ++i){
                denom = denom * (n-i);
                numero = numero * (i+1);
            }
            System.out.format("%d %d\n", denom, numero);
            return denom/numero;
        }
    }
}
