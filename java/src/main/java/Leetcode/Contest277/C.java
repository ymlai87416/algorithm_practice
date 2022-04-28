package Leetcode.Contest277;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 problem: https://leetcode.com/contest/weekly-contest-277/problems/find-all-lonely-numbers-in-the-array/
 level: medium
 solution:

 **/

public class C {

    public List<Integer> findLonely(int[] nums) {
        HashMap<Integer,Integer> hs = new HashMap<>();
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(!hs.containsKey(nums[i])) hs.put(nums[i], 0);
            hs.put(nums[i], hs.get(nums[i])+1);
        }

        for (int i = 0; i < nums.length; i++) {
            if(hs.get(nums[i]) == 1 && !hs.containsKey(nums[i]-1) && !hs.containsKey(nums[i]+1))
                result.add(nums[i]);
        }

        return result;
    }

    public static void main(String[] args){
        C s = new C();

    }

}
