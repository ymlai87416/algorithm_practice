package Leetcode;

public class NumberOf1Bits {
    public static void main(String[] args){
        int a = 0b00000000000000000000000000001011;
        Solution s = new Solution();
        System.out.println(s.hammingWeight(a));
    }

    static
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int cnt = 0;
            for(int i=0; i<32; ++i){
                if( (n & (1 << i)) != 0)
                    cnt += 1;
            }
            return cnt;
        }
    }
}
