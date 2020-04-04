package Leetcode;

import java.util.Stack;

/*
url: https://leetcode.com/problems/evaluate-reverse-polish-notation/
level: medium
solution: polish notation
 */

public class EvaluateReversePolishNotation {
    public static void main(String[] args){
        String[] input = new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        Solution s = new Solution();
        System.out.println(s.evalRPN(input));
    }

    static
    class Solution {
        public int evalRPN(String[] tokens) {
            Stack<Integer> s = new Stack<Integer>();

            int right, left;
            for(int i=0; i<tokens.length; ++i){
                if(tokens[i].compareTo("+") == 0){
                    right = s.pop(); left = s.pop();
                    s.push(left+right);
                }
                else if(tokens[i].compareTo("-") == 0){
                    right = s.pop(); left = s.pop();
                    s.push(left-right);
                }
                else if(tokens[i].compareTo("*") == 0){
                    right = s.pop(); left = s.pop();
                    s.push(left*right);
                }
                else if(tokens[i].compareTo("/") == 0){
                    right = s.pop(); left = s.pop();
                    s.push(left/right);
                }
                else{
                    s.push(Integer.valueOf(tokens[i]));
                }
            }

            return s.pop();
        }
    }

}
