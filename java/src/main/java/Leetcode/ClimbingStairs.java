package Leetcode;

/**
problem: https://leetcode.com/problems/climbing-stairs/
level: easy
solution: fibonacci number, with both close and matrix form

#dp  #fibonacci_number

 */
public class ClimbingStairs {public static void main(String[] args){
        int n=5;

        Solution s = new Solution();
        System.out.println(s.climbStairs(n));
    }


    static
    class Solution {
        public int climbStairs(int n) {
            //return closeForm(n)
            return matrixForm(n)[0][0];
        }

        public int closeForm(int n){
            double sqrt5 = Math.sqrt(5);
            double r = 1/sqrt5*Math.pow(((1+sqrt5)/2), n+1) - 1/sqrt5*Math.pow(((1-sqrt5)/2), n+1);
            //System.out.println(r);
            return (int)Math.round(r);
        }

        /*define
        M_i = ( F_i+1   Fi  )
              ( Fi      Fi-1)
         */
        private int[][] M0 = {{1, 1}, {1, 0}};

        public int[][] matrixForm(int n){
            if(n == 1)
                return M0;
            else{
                int[][] a = matrixForm(n/2);
                if(n%2 == 0){
                    return matrixMul(a, a);
                }
                else{
                    return matrixMul(matrixMul(a, a), M0);
                }
            }
        }

        private int[][] matrixMul(int[][] a, int[][] b){
            int[][] r = new int[2][2];
            r[0][0] = a[0][0]*b[0][0]+a[0][1]*b[1][0];
            r[0][1] = a[0][0]*b[0][1]+a[0][1]*b[1][1];
            r[1][0] = a[1][0]*b[0][0]+a[1][1]*b[1][0];
            r[1][1] = a[1][0]*b[0][1]+a[1][1]*b[1][1];
            return r;
        }
    }
}
