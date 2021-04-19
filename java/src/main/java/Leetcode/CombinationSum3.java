package Leetcode;

import java.util.ArrayList;
import java.util.List;

/*
url: https://leetcode.com/problems/combination-sum-iii/
level: medium
solution: something like combination 1

#backtracking

 */

public class CombinationSum3 {
    public static void main(String[] args){
        int n, k;
        n = 7; k=3;
        Solution s = new Solution();
        System.out.println(s.combinationSum3(k, n));
    }

    static
    class Solution {

        public List<List<Integer>> combinationSum3(int k, int n) {
            return helper(9, k, n);
        }

        public List<List<Integer>> helper(int b, int k, int n) {
            //System.out.format("%d %d %d\n", b,k ,n);
            ArrayList<List<Integer>> result = new ArrayList<>();
            List<List<Integer>> temp;
            for(int j=0, p=0; p<=1 && j<=n; j+=b, ++p){
                //pruning b*k < n

                if(n-j == 0){
                    if(k-p == 0) {
                        ArrayList<Integer> t = new ArrayList<>();
                        if(p==1)
                            t.add(b);
                        result.add(t);
                    }
                }
                else if((b-1)*(k-p) >= n-j) {
                    temp = helper(b - 1, k - p, n - j);

                    for(List<Integer> t: temp){
                        if(p==1)
                            t.add(b);
                        result.add(t);
                    }
                }
            }

            return result;
        }
    }
}
