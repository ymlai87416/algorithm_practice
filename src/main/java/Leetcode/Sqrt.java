package Leetcode;

public class Sqrt {
    public static void main(String[] args){
        int input = 2147395599;
        Solution s = new Solution();
        int t=s.mySqrt(input);
        System.out.println(t);
    }

    static
    class Solution {
        public int mySqrt(int x) {
            if(x ==0 || x == 1) return x;
            long start = 0;
            long end = x;
            while(start < end){
                long mid = (start+end)/2;
                long mid2=mid*mid;

                if(mid2 == x) return (int)mid;
                if(mid2 < x){
                    start = mid+1;
                }
                else {
                    end = mid;
                }
            }

            return (int)start-1;
        }
    }
}
