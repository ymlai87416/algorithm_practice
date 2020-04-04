package Leetcode;

/*
number: 60
url: https://leetcode.com/problems/permutation-sequence/
level: medium
solution: it is just a number system. there is no zero.
 */

public class PermutationSequence {
    public static void main(String[] args){
        int n = 4;
        int k = 9;
        Solution s = new Solution();
        System.out.println(s.getPermutation(n, k));
    }

    static
    class Solution {
        public String getPermutation(int n, int k) {
            boolean[] visited = new boolean[n];

            for(int i=0; i<n; ++i)
                visited[i] = false;

            return getPermutationHelper(n, k-1, visited);
        }

        public String getPermutationHelper(int n, int k, boolean[] visited){
            //System.out.format("%d %d\n", n, k);
            if(n == 0) return "";

            int facN = factorial(n-1);
            int pos = k / facN;
            int mod = k % facN;

            int ipos;
            for(ipos=0; ipos<visited.length; ++ipos){
                if(!visited[ipos]){
                    if(pos == 0) break;
                    else --pos;
                }
            }

            visited[ipos] = true;
            return String.valueOf(ipos+1) + getPermutationHelper(n-1, mod, visited);
        }

        public int factorial(int n){
            int r=1;
            for(int i=1; i<=n; ++i) r = r * i;
            return r;
        }
    }
}
