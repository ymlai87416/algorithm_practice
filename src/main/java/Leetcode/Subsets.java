package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args){
        String w1 = "horse";
        String w2 = "ros";

        EditDistance.Solution s = new EditDistance.Solution();
        System.out.println(s.minDistance(w1, w2));
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
