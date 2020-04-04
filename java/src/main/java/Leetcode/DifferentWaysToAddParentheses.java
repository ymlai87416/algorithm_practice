package Leetcode;

import java.util.*;

/*
url: https://leetcode.com/problems/different-ways-to-add-parentheses/
level: medium
solution: a tree to decide which operand is doing first.
 */
public class DifferentWaysToAddParentheses {


    public static void main(String[] args){
        String a = "2*3-4*5";
        Solution sol = new Solution();
        System.out.println(sol.diffWaysToCompute(a));
    }

    static
    class Solution {

        public List<Integer> diffWaysToCompute(String input) {
            return treeHelper(input);
        }

        public List<Integer> treeHelper(String input){
            ArrayList<Integer> number = new ArrayList<Integer>();
            ArrayList<Character> op= new ArrayList<Character>();

            tokenized(input, number, op);

            List<Integer> r = treeHelperEx(number, op, 0, number.size(), 0, op.size());
            Collections.sort(r);
            return r;
        }

        private List<Integer> treeHelperEx(List<Integer> number, List<Character> op, int nStart, int nEnd, int oStart, int oEnd) {
            List<Integer> result  = new ArrayList<Integer>();

            if(oStart == oEnd) {
                result.add(number.get(nStart));
                return result;
            }

            for(int pivot=oStart; pivot<oEnd; ++pivot){
                List<Integer> left = treeHelperEx(number, op, nStart, nStart+(pivot-oStart)+1, oStart, pivot);
                List<Integer> right = treeHelperEx(number, op, nStart+(pivot-oStart)+1, nEnd, pivot+1, oEnd);

                for(int leftI : left){
                    for(int rightI: right){
                        result.add(eval(leftI, rightI, op.get(pivot)));
                    }
                }
            }

            return result;
        }

        //wrong, because return multiple result for the same case
        public List<Integer> helper(String input){
            ArrayList<Integer> number = new ArrayList<Integer>();
            ArrayList<Character> op= new ArrayList<Character>();

            tokenized(input, number, op);

            List<Integer> r = helperEx(number, op);
            Collections.sort(r);
            return r;
        }

        private List<Integer> helperEx(List<Integer> number, List<Character> op){
            if(op.size() == 0)
                return Arrays.asList(number.get(0));

            ArrayList<Integer> result = new ArrayList<>();
            for(int i=0; i<op.size(); ++i){
                List<Integer> newNumber = new ArrayList<Integer>();
                List<Character> newOp = new ArrayList<Character>();

                int j, k;
                for(j=0, k=0; j<op.size(); ++j, ++k){
                    if(i == j){
                        newNumber.add(eval(number.get(k), number.get(k+1), op.get(j)));
                        ++k;
                    }
                    else{
                        newNumber.add(number.get(k));
                        newOp.add(op.get(j));
                    }
                }
                if(k<number.size())
                    newNumber.add(number.get(k));

                List<Integer> tResult = helperEx(newNumber, newOp);
                result.addAll(tResult);
            }

            return result;
        }

        private Integer eval(int op1, int op2, char optr) {
            switch(optr) {
                case '+':
                    return op1+op2;
                case '-':
                    return op1-op2;
                case '*':
                    return op1*op2;
            }

            return -1;
        }

        private void tokenized(String input, List<Integer> number, List<Character> op){
            int tmp = 0;
            for(int i=1; i<input.length(); ++i){
                if(input.charAt(i) == '+' || input.charAt(i) == '*' || input.charAt(i) == '-'){
                    number.add(Integer.parseInt(input.substring(tmp, i)));
                    op.add(input.charAt(i));
                    tmp = i+1;
                }
            }
            number.add(Integer.parseInt(input.substring(tmp, input.length())));

            return;
        }

    }
}
