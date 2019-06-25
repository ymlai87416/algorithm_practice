package Leetcode;

public class BitwiseAndOfNumberRange {
    public static void main(String[] args){
        int m = 5;
        int n = 7;

        Solution s = new Solution();
        System.out.println(s.rangeBitwiseAnd(m, n));
    }


    static
    class Solution {
        //naive
        public int rangeBitwiseAnd(int m, int n) {
            //helper(m, n, 30);
            return loopHelper(m, n);
        }

        //7ms at best, rank at 10%
        private int helper(int m, int n, int left){
            System.out.format("%d %d\n", m, n);
            //base case
            if(left == 0){
                return m;
            }
            else{
                int pow2 = 1 << left;

                //crossing pow2 which is 100..000
                if (m <= pow2 && pow2 <=n){
                    if(m < pow2)
                        return 0;
                    else
                        return pow2;
                }
                else if(n < pow2){
                    return helper(m, n, left - 1);
                }
                else /*if(m > pow2)*/{
                    return pow2 + helper(m-pow2, n-pow2, left - 1);
                }
            }
            // no need to process if ra[left] is already set to 0.
        }

        //5ms at best, rank at 50%
        private int loopHelper(int m, int n){
            int r = 0;
            for(int i=30; i>=0; --i){
                //System.out.format("%d %d %d\n", i, m, n);
                int pow2 = 1 << i;

                //crossing pow2 which is 100..000
                if (m <= pow2 && pow2 <=n){
                    if (m >= pow2) {
                        r += pow2;
                    }
                    break;
                }
                else if(n < pow2){
                    //r += 0;
                }
                else /*if(m > pow2)*/{
                    r += pow2;
                    m -= pow2;
                    n -= pow2;
                }
            }

            return r;
        }
    }
}
