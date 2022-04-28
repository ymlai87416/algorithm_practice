package Leetcode.Contest277;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 problem: https://leetcode.com/contest/weekly-contest-277/problems/count-elements-with-strictly-smaller-and-greater-elements/
 level: easy
 solution:

 **/

public class A {

    public int countElements(int[] nums) {
        TreeMap<Integer, Integer> a = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(!a.containsKey(nums[i]))
                a.put(nums[i], 0);
            a.put(nums[i], a.get(nums[i])+1);
        }
        var al = new ArrayList<>(a.entrySet());
        int r = 0;
        for (int i = 1; i < al.size()-1; i++) {
            r += al.get(i).getValue();
        }
        return r;
    }

    public static void main(String[] args){
        A s = new A();

    }
}

