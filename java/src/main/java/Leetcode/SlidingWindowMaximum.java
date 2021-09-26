package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
number: 239
problem: https://leetcode.com/problems/sliding-window-maximum/
level: hard
solution: 1. monotonic queue 9ms
        2. priority queue O(nlogn) 32ms
        3. rmq O(nlogn) with more space requirement. 265ms

 #queue #sliding_window #heap #monotonic_queue #rmq

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
            //return pqHelper(nums, k);
            return rmqHelper(nums, k);
        }

        //this is to implement sparse table
        int MAXN = 100005;
        int K=15;
        int[] log = new int[MAXN+1];
        int[][] st = new int[MAXN][K + 1];

        public int[] rmqHelper(int[] nums, int k){
            if(nums.length==0) return new int[0];
            int[] r = new int[nums.length-k+1];

            //precompute
            log[1] = 0;
            for (int i = 2; i <= MAXN; i++)
                log[i] = log[i/2] + 1;

            //build, using O(n^2)
            for (int i = 0; i < nums.length; i++)
                st[i][0] = nums[i];

            for (int j = 1; j <= K; j++) {
                for (int i = 0; i + (1 << j) <= nums.length; i++)
                    st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }

            //O(1) for each query, total O(L-k+1)
            for(int R=k-1, L=0; R<nums.length; ++R, ++L){
                int j = log[R - L + 1];
                r[L] = Math.max(st[L][j], st[R - (1 << j) + 1][j]);
            }

            return r;
        }

        //wow, O(n) = 9ms 82.46%
        public int[] dequeueHelper(int[] nums, int k){
            if(nums.length==0) return new int[0];
            int[] r = new int[nums.length-k+1];
            ArrayDeque<Integer> dq = new ArrayDeque<>();

            for(int i=0; i<k; ++i) {
                int c = nums[i];
                while(!dq.isEmpty() && nums[dq.peekLast()] < c)  //from bottom, pull all the element smaller than c and place c at the end (monotonic queue)
                    dq.pollLast();
                dq.offerLast(i);
            }
            r[0] = nums[dq.peekFirst()];

            for(int i=k, j=1; i<nums.length; ++i, ++j){
                int c = nums[i];
                while(!dq.isEmpty() && nums[dq.peekLast()] < c)
                    dq.pollLast();
                dq.offerLast(i);

                while(!dq.isEmpty() && dq.peekFirst() <= i-k)  //remove all element pass the sliding windows
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
