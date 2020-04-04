package Leetcode;

import java.util.ArrayList;
import java.util.Stack;

/*
problem: https://leetcode.com/problems/basic-calculator/
level: hard
solution: hand coded parsing
 */
public class BasicCalculator {
    public static void main(String[] args){

    }

    static
    class Solution {
        public int calculate(String s) {
            ArrayList<String> a = tokenize(s);
            return eval(a, 0, a.size()-1);
        }

        private int eval(ArrayList<String> s, int start, int end){
            if(start == end)
                return Integer.parseInt(s.get(start));
            if(s.get(start) == "(" && s.get(end) == ")")
                return eval(s, start+1, end-1);
            int r = 0;
            String op = "+";
            Stack<Integer> a = new Stack<>();
            for(int i=start; i<=end; ++i){
                if(isNumber(s.get(i)))
                    eval(op, r, Integer.parseInt(s.get(i)));
                else if(s.get(i).compareTo("+") == 0 || s.get(i).compareTo("-") == 0)
                    op = s.get(i);
                else if(s.get(i).compareTo("(") == 0){
                    a.push(i);
                    int lastJ = 0;
                    for(int j=i+1; j<=end; ++j){
                        if(s.get(j).compareTo("(") == 0) a.push(j);
                        else if(s.get(j).compareTo(")") == 0){
                            a.pop();
                            if(a.isEmpty()){
                                lastJ = j;
                                break;
                            }
                        }
                    }

                    eval(op, r, eval(s, i+1, lastJ-1));
                    i = lastJ;
                }
            }

            return r;
        }

        private boolean isNumber(String s){
            return s.charAt(0) >= '0' && s.charAt(0) <='9';
        }

        private int eval(String operator, int operand1, int operand2){
            if(operator.charAt(0) == '+') return operand1 + operand2;
            else return operand1 - operand2;
        }

        private ArrayList<String> tokenize(String s){
            ArrayList<String> token = new ArrayList<>();
            for(int i=0; i<s.length(); ++i){
                char c = s.charAt(i);
                if(c == ' ') continue;

                if(c >= '0' && c <= '9') {
                    StringBuilder sb = new StringBuilder();
                    while (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                        sb.append(s.charAt(i));
                        i++;
                    }
                    i-=1;

                    token.add(sb.toString());
                }

                if(c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/')
                    token.add(String.valueOf(c));
            }

            return token;
        }
    }

}
