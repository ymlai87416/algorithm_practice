package Leetcode;

/*
url: https://leetcode.com/problems/count-primes/
level: Easy
solution: just a seive
 */
public class CountPrimes {
    public static void main(String[] args){
        int a = 19;
        Solution s = new Solution();
        System.out.println(s.countPrimes(a));
    }

    static
    class Solution {
        public int countPrimes(int n) {
            if(n == 0 || n == 1) return 0;
            boolean[] seive = new boolean[n];
            for(int i=0; i<seive.length; ++i) seive[i] = true;
            int sqN = (int)Math.floor(Math.sqrt(n));
            seive[0] = false;
            seive[1] = false;
            int i = 2;
            while(i <= sqN){
                for(int j=i+i; j<n; j+=i) seive[j] = false;

                int nextI = i+1;
                for(; !seive[nextI]; ++nextI );
                i = nextI;
            }

            int cnt = 0;
            for(int j=2; j<n; ++j){
                if(seive[j]) ++cnt;
            }
            return cnt;
        }
    }
}
