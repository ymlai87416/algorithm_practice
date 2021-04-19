package Leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
number: 679
url: https://leetcode.com/problems/24-game/
level: hard
solution: bracket is not important, we have to build the parse tree, set * set
            4 numbers, draw 2 and apply 4 operation. put in back, now we have 3 nums, apply 4 more, apply until we only
            got 1 number

 #dfs

 **/

public class TwentyFourGame {
    public static void main(String[] args) {
        int[] input = {1, 5, 9, 1};
        Solution s = new Solution();
        System.out.println(s.judgePoint24(input));
    }

    static
    class Solution {
        int[] number;
        HashMap<String, Set<Double>> dp;
        List<Character> opList = Arrays.asList('+','-','*','/');

        public boolean judgePoint24(int[] number) {
            return helper2(number);
        }

        //the best is to deal with the problem directly, like dfs.

        //best 62ms, still the worst.
        private boolean helper2(int[] number){
            this.number = number;
            boolean[] visited = new boolean[number.length];

            return helper2Ex(new Stack<>(), visited);
        }


        private boolean helper2Ex(Stack<Character> a, boolean[] visited){
            if(a.size() == 7){
                return Math.abs(evalReversePolish(a) - 24) < 1e-8;
            }
            int num = 0;
            int oprand = 0;
            for(int i=0; i<a.size(); ++i) {
                Character cur = a.get(i);
                if (isOp(cur)) {
                    oprand++;
                } else
                    num++;
            }

            boolean r;
            for(int i=0; i<visited.length; ++i){
                if(!visited[i]){
                    a.push((char)(number[i]+'0'));
                    visited[i] = true;
                    r = helper2Ex(a, visited);
                    if(r) return true;
                    a.pop();
                    visited[i] = false;
                }
            }

            if(num-1 > oprand){
                for(int i=0; i<opList.size(); ++i){
                    a.push(opList.get(i));
                    r = helper2Ex(a, visited);
                    if(r) return true;
                    a.pop();
                }
            }

            return false;
        }

        private double evalReversePolish(List<Character> a){
            //System.out.println(a);
            Stack<Double> st = new Stack<Double>();
            for(int i=0; i<a.size(); ++i){
                if (isOp(a.get(i))) {
                    double op1 = st.pop();
                    double op2 = st.pop();

                    double r = 0;
                    switch(a.get(i)){
                        case '+':
                            r= op1+op2;
                            break;
                        case '-':
                            r= op1-op2;
                            break;
                        case '*':
                            r= op1*op2;
                            break;
                        case '/':
                            r= op1/op2;
                            break;
                    }

                    st.push(r);
                }
                else
                    st.push((double)(a.get(i)-'0'));
            }

            return st.pop();
        }

        private boolean isOp(Character cur){
            //return opList.stream().anyMatch(op -> op.compareTo(cur) == 0);
            for(int i=0; i< opList.size(); ++i) {
                if (opList.get(i) == cur)
                    return true;
            }
            return false;
        }

        private boolean helper(int[] number) {
            this.number = number;
            dp = new HashMap<>();
            List<Integer> a = new ArrayList<>();
            for (int i = 0; i < number.length; ++i)
                a.add(number[i]);

            boolean result = helperEx(a).stream().anyMatch(e -> Math.abs(24 - e) < 1e-8);
                        /*
            for(String k: dp.keySet()){
                System.out.print(k + ": ");
                System.out.println(dp.get(k));
            }
            */
            return result;
        }

        private Set<Double> helperEx(List<Integer> list) {

            list.sort(Integer::compareTo);
            String key = String.join("", list.stream().map(x -> String.valueOf(x)).collect(Collectors.toList()));

            if (dp.containsKey(key))
                return dp.get(key);

            Set<Double> allResult = new HashSet<>();
            if (list.size() == 1) {
                allResult.add((double) list.get(0));
                return allResult;
            }
            if (list.size() == 2) {
                double p = list.get(0);
                double q = list.get(1);
                allResult.add(p + q);
                allResult.add(p - q);
                allResult.add(q - p);
                allResult.add(p * q);
                allResult.add(p / q);
                allResult.add(q / p);

                return allResult;
            }

            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();


            for (int i = 1; i < Math.pow(2, list.size()) - 1; ++i) {
                left.clear();
                right.clear();
                for (int j = 0; j < list.size(); ++j) {
                    if ((i & (1 << j)) == 0)
                        left.add(list.get(j));
                    else
                        right.add(list.get(j));
                }

                Set<Double> leftAns = helperEx(left);
                Set<Double> rightAns = helperEx(right);

                for (double p : leftAns) {
                    for (double q : rightAns) {
                        allResult.add(p + q);
                        allResult.add(p - q);
                        allResult.add(q - p);
                        allResult.add(p * q);
                        if (q != 0) allResult.add(p / q);
                        if (p != 0) allResult.add(q / p);
                    }
                }
            }

            dp.put(key, allResult);

            return allResult;
        }
    }
}
