package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public static void main(String[] args){
        int[] candidates  = new int[]{2,3,6,7};
        int target  = 7;
        CombinationSum.Solution s = new CombinationSum.Solution();
        System.out.println(s.combinationSum(candidates, target));
    }

    static
    class Solution {
        public List<String> fizzBuzz(int n) {
            ArrayList<String> r = new ArrayList<>();
            for(int i=1; i<=n; ++i){
                if(i % 15 == 0)
                    r.add("FizzBuzz");
                else if(i%5 == 0)
                    r.add("Buzz");
                else if(i%3==0)
                    r.add("Fizz");
                else
                    r.add(String.valueOf(i));
            }

            return r;
        }
    }
}
