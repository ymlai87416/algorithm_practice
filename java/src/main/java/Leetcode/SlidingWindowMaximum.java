package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
number: 380
problem: https://leetcode.com/problems/climbing-stairs/
level: easy
solution: fibonacci number

 #dp

 **/
public class SlidingWindowMaximum {
    public static void main(String[] args){
        //int[] nums  = new int[]{1,3,-1,-3,5,3,6,7};
        int[] nums = new int[]{1, -1};
        int k = 1;

        Solution sol = new Solution();
        int[] a = sol.maxSlidingWindow(nums, k);
        for(int i=0; i<a.length; ++i)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    static
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            return pqHelper(nums, k);
        }

        //wow, O(n) = 9ms 82.46%
        public int[] dequeueHelper(int[] nums, int k){
            if(nums.length==0) return new int[0];
            int[] r = new int[nums.length-k+1];
            ArrayDeque<Integer> dq = new ArrayDeque<>();

            for(int i=0; i<k; ++i) {
                int c = nums[i];
                while(!dq.isEmpty() && nums[dq.peekLast()] < c)
                    dq.pollLast();
                dq.offerLast(i);
            }
            r[0] = nums[dq.peekFirst()];

            for(int i=k, j=1; i<nums.length; ++i, ++j){
                int c = nums[i];
                while(!dq.isEmpty() && nums[dq.peekLast()] < c)
                    dq.pollLast();
                dq.offerLast(i);

                while(!dq.isEmpty() && dq.peekFirst() <= i-k)
                    dq.pollFirst();

                r[j] = nums[dq.peekFirst()];
            }

            return r;
        }

        //wow, O(nlogn) = 32ms 25.97%
        public int[] pqHelper(int[] nums, int k){
            if(nums.length==0) return new int[0];
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            int[] r  = new int[nums.length-k+1];
            for(int i=0; i<k; ++i){
                pq.offer(nums[i]);
            }
            r[0] = pq.peek();

            for(int i=k, j=1; i<nums.length; ++i, ++j){
                pq.remove(nums[i-k]);
                pq.offer(nums[i]);
                r[j] = pq.peek();
            }

            return r;
        }
    }
}
