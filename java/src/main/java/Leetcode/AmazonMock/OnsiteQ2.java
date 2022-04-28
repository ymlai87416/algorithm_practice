package Leetcode.AmazonMock;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.*;

public class OnsiteQ2 {

    class FirstUnique {

        Queue<Integer> q = null;
        HashSet<Integer> once = null;
        HashSet<Integer> twice = null;

        public FirstUnique(int[] nums) {
            q= new ArrayDeque<Integer>();
            once = new HashSet<>();
            twice = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                q.add(nums[i]);
                if(once.contains(nums[i])) twice.add(nums[i]);
                else once.add(nums[i]);
            }

        }

        public int showFirstUnique() {

            while(!q.isEmpty()){
                int a = q.peek();
                if(twice.contains(a))
                    q.poll();
                else
                    return a;
            }
            return -1;
        }

        public void add(int value) {
            q.add(value);
            if(once.contains(value)) twice.add(value);
            else once.add(value);
        }
    }

    public static void main(String[] args){

    }

}
