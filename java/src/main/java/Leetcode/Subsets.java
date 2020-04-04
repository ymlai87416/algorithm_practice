package Leetcode;

import java.util.ArrayList;
import java.util.List;

/*
number: 78
url: https://leetcode.com/problems/subsets/
level: medium
solution: start with seed [], loop the number, expand the seed by appending the number to each seed element.
 */

public class Subsets {
    public static void main(String[] args){
        int[] nums = {1,2,3};
        Solution s = new Solution();
        var result = s.subsets(nums);
        for(int i=0; i<result.size(); ++i){
            System.out.println(result.get(i));
        }
    }


    static
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {

            ArrayList<List<Integer>> r = new ArrayList<List<Integer>>();
            r.add(new ArrayList<Integer>() );

            for(int n: nums){
                List<List<Integer>> newstate = new ArrayList<List<Integer>>();
                for(List<Integer> s: r){
                    ArrayList<Integer> tt = new ArrayList<Integer>(s);
                    tt.add(n);
                    newstate.add(tt);
                }
                r.addAll(newstate);
            }

            return r;
        }

    }
}
