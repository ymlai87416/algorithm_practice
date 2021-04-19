package Leetcode;

import java.util.Stack;

/**
number: 20
problem: https://leetcode.com/problems/valid-parentheses/
level: easy
solution: just use stack.

#string #stack

 **/

public class ValidParentheses {
    public static void main(String[] args) {
        String i = "()[]{}";
        Solution s = new Solution();
        System.out.println(s.isValid(i));
    }


    static
    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<Character>();

            for(int i=0; i<s.length(); ++i) {
                char c = s.charAt(i);

                if (c == '(' || c == '[' || c == '{')
                    stack.push(c);
                else {
                    boolean ok = false;

                    if(stack.empty()) return false;
                    else {
                        char x = stack.pop();
                        switch (c) {
                            case ')':
                                ok = x == '(';
                                break;
                            case ']':
                                ok = x == '[';
                                break;
                            case '}':
                                ok = x == '{';
                                break;
                            default:
                                ok = false;
                                break;
                        }

                        if (!ok) return false;
                    }
                }
            }

            return stack.empty();
        }
    }

}
