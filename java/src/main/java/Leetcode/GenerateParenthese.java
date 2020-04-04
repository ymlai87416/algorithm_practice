package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
url: https://leetcode.com/problems/generate-parentheses/
level: medium
solution: take a recursive strategy
 */

public class GenerateParenthese {
    public static void main(String[] args){
        int n = 3;
        Solution s = new Solution();
        System.out.println(s.generateParenthesis(n));
    }


    static
    class Solution {
        int n;
        ArrayList<String> result = null;

        public List<String> generateParenthesis(int n) {
            this.n = n;
            result = new ArrayList<String>();
            gen(0, 0, "");
            return result;
        }

        private void gen(int open, int close, String a){
            if(a.length() == 2*n) result.add(a);
            else{
                if(open < n) gen(open+1, close,  a+"(");
                if(open-close > 0) gen(open, close+1, a+")");
            }
        }
    }

}
