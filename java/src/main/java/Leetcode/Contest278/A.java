package Leetcode.Contest278;

import java.util.*;

public class A {

    public int findFinalValue(int[] nums, int original) {
        HashSet<Integer> a = new HashSet<>();
        for(int i=0; i<nums.length; ++i)
            a.add(nums[i]);

        while(true){
            if(a.contains(original))
                original = original * 2;
            else
                break;
        }

        return original;
    }

    public static void main(String[] args){

    }

}
