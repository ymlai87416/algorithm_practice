package Leetcode;

import java.util.Stack;

/*
problem: https://leetcode.com/problems/daily-temperatures/
level: medium
solution:

#hashTable #stack
 */

public class DailyTemperature {
    public static void main(String[] args) {
        int[] test = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        Solution sol = new Solution();
        int[] r = sol.dailyTemperatures(test);
        for(int i=0; i<r.length; ++i)
            System.out.print(r[i] + " ");
        System.out.println();
    }

    static
    class Solution {
        public int[] dailyTemperatures(int[] T) {
            int[] r = new int[T.length];
            Stack<Integer> t = new Stack<Integer>();
            for(int i=0; i<T.length; ++i){
                while(!t.empty() && T[t.peek()] < T[i]){
                    int b = t.pop();
                    r[b] = i-b;
                }
                t.push(i);
            }

            return r;
        }
    }
}
