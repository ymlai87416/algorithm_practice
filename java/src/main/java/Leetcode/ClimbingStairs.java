package Leetcode;

/*
url: https://leetcode.com/problems/climbing-stairs/
level: easy
solution: fibonacci number
 */
public class ClimbingStairs {public static void main(String[] args){
        int n=2;

        Solution s = new Solution();
        System.out.println(s.climbStairs(n));
    }


    static
    class Solution {
        public int climbStairs(int n) {
            double sqrt5 = Math.sqrt(5);
            double r = 1/sqrt5*Math.pow(((1+sqrt5)/2), n+1) - 1/sqrt5*Math.pow(((1-sqrt5)/2), n+1);
            //System.out.println(r);
            return (int)Math.round(r);
        }
    }
}
