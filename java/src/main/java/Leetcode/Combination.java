package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
url: https://leetcode.com/problems/combinations/
level: medium
solution: 1...k, at each recursion step, either add or not add. when the array reach n, stop it.
 */

public class Combination {
    public static void main(String[] args) {
        int n = 4; int k=2;
        Solution sol = new Solution();
        List<List<Integer>> r = sol.combine(n, k);
        System.out.println(r);
    }

    static
    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> r = new ArrayList<List<Integer>>();
            Stack<Integer> cur = new Stack<>();

            helper2(1, k, n, cur, r);

            return r;
        }

        private void helper(int n, int k, int nmax, Stack<Integer> cur, List<List<Integer>> r){
            if(k == 0){
                ArrayList<Integer> t= new ArrayList<Integer>(); t.addAll(cur);
                r.add(t);
                return;
            }
            if(n > nmax) return;

            helper(n+1, k, nmax, cur, r);
            cur.push(n);
            helper(n+1, k-1, nmax, cur, r);
            cur.pop();
        }

        private void helper2(int n, int k, int nmax, Stack<Integer> cur, List<List<Integer>> r){
            if(k == 0){
                r.add(new ArrayList<Integer>(cur));
                return;
            }

            for(int i=n; i<=nmax; ++i){
                cur.push(i);
                helper2(i+1, k-1, nmax, cur, r);
                cur.pop();
            }
        }
    }
}
