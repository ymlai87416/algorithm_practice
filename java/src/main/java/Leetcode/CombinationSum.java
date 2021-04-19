package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
url: https://leetcode.com/problems/combination-sum/
level: medium
solution: sort the array, at each recursive step, either we keep the pointer unchange (add), or advance
 the pointer by one (skip). because it is addition. you can purne the tree when the target is smaller than the current
 element.

#backtracking

 */

public class CombinationSum {
    public static void main(String[] args){
        int[] candidates  = new int[]{2,3,6,7};
        int target  = 7;
        CombinationSum.Solution s = new CombinationSum.Solution();
        System.out.println(s.combinationSum(candidates, target));
    }

    /*
        no dp => 14ms
        dp => 10ms (28.84%)
        sort with pruning => 5ms (78.04%)
     */


    static
    class Solution {
        int[] candidates;
        HashMap<Parameter, List<List<Integer>>> dp = new HashMap<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            this.candidates = candidates;
            this.dp.clear();

            Arrays.sort(candidates);

            return helper(0, target);
        }

        private List<List<Integer>> helper(int ptr, int target){
            //System.out.format("%d %d\n", ptr, target );
            if(target == 0){
                ArrayList<List<Integer>> result= new ArrayList<List<Integer>>();
                result.add(new ArrayList<Integer>());
                return result;
            }

            if(ptr == candidates.length || target < 0 || target < candidates[ptr]){
                return new ArrayList<List<Integer>>();
            }

            Parameter p = new Parameter(ptr, target);
            if(dp.containsKey(p)){
                return dp.get(p);
            }

            ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();

            List<List<Integer>> result1 = helper(ptr, target-candidates[ptr]);
            List<List<Integer>> result2 = helper(ptr+1, target);

            for(int i=0; i<result1.size(); ++i){
                List<Integer> t = new ArrayList<Integer>();
                t.add(candidates[ptr]);
                t.addAll(result1.get(i));
                result.add(t);
            }

            result.addAll(result2);

            dp.put(p, result);

            return result;
        }

    }

    static
    class Parameter{
        int ptr, target;
        public Parameter(int p, int t){
            this.ptr = p;
            this.target = t;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + this.ptr;
            hash = 31 * hash +this.target;

            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            Parameter p = (Parameter) o;
            return ptr == p.ptr
                    && (target == p.target);
        }
    }
}
