package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*
problem: https://leetcode.com/problems/basic-calculator-ii/
level: medium
solution:
 */
public class BasicCalculatorII {
    public static void main(String[] args) {
        String s = " 3+5 / 2 ";
        Solution sol = new Solution();
        int r = sol.calculate(s);
        System.out.println(r);
    }

    static
    class Solution {
        static HashMap<String, Integer> piroirtyOp = new HashMap<String, Integer>();
        static {
            piroirtyOp.put("+", 1);
            piroirtyOp.put("-", 1);
            piroirtyOp.put("*", 2);
            piroirtyOp.put("/", 2);
        }

        //6.60% convert to reverse polish seems a lot worse...
        public int calculate(String s) {

            List<String> ts = tokenize(s);
            List<String> reversePolish = new ArrayList<String>();
            Stack<String> sop  = new Stack<String>();
            for(int i=0; i<ts.size(); ++i){
                String curToken = ts.get(i);
                if(curToken.compareTo("+") == 0 || curToken.compareTo("-") == 0 ||
                        curToken.compareTo("*") == 0 || curToken.compareTo("/") == 0){
                    while(!sop.empty()){
                        String u = sop.peek();
                        if(piroirtyOp.get(u) >= piroirtyOp.get(curToken))
                            reversePolish.add(sop.pop());
                        else
                            break;
                    }
                    sop.push(curToken);
                }
                else
                    reversePolish.add(curToken);
            }
            while(!sop.empty()) reversePolish.add(sop.pop());

            Stack<Integer> si = new Stack<Integer>();

            for(int i=0; i<reversePolish.size(); ++i){
                String curToken = reversePolish.get(i);
                if(curToken.compareTo("+") == 0 || curToken.compareTo("-") == 0 ||
                        curToken.compareTo("*") == 0 || curToken.compareTo("/") == 0){
                    int right = si.pop();
                    int left = si.pop();
                    int result = 0;
                    switch(curToken){
                        case "+":
                            result = left+right;
                            break;
                        case "-":
                            result = left-right;
                            break;
                        case "*":
                            result = left*right;
                            break;
                        case "/":
                            result = left/right;
                            break;
                    }
                    si.push(result);
                }
                else
                    si.push(Integer.valueOf(curToken));
            }

            return si.pop();
        }


        private List<String> tokenize(String s){
            List<String> b = new ArrayList<String>();
            StringBuilder t = null;
            for(int i=0; i<s.length() ; ++i){
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    if(t == null) {
                        t = new StringBuilder();
                        t.append(s.charAt(i));
                    }
                    else
                        t.append(s.charAt(i));
                }
                else {
                    if(t != null){
                        b.add(t.toString());
                        t= null;
                    }

                    if (s.charAt(i) == '+') {
                        b.add("+");
                    } else if (s.charAt(i) == '-') {
                        b.add("-");
                    } else if (s.charAt(i) == '*') {
                        b.add("*");
                    } else if (s.charAt(i) == '/') {
                        b.add("/");
                    }
                }
            }

            if(t!= null) b.add(t.toString());

            return b;
        }
    }
}

