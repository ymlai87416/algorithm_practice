package Leetcode;

import java.util.*;

/*
number: 45
url: https://leetcode.com/problems/jump-game-ii/
level: hard
solution: bfs with dist => 226, but can be improved by scanning range, the first range is the starting point,
    the next range is from the element following the range to the element that can be reached from the range.
 */

public class JumpGameII {
    public static void main(String[] args){
        int[] nums = new int[] {1,1,1,1};
        Solution s = new Solution();
        System.out.println(s.jump(nums));
    }

    static
    class Solution {
        public int jump(int[] nums) {
            return searchRangeHelper(nums);
        }

        //O(n) bfs complexity is O(n2) 262ms rank at low
        private int bfsHelper(int[] nums){
            int[] dist = new int[nums.length];
            Queue<Integer> q = new LinkedList<Integer>();

            q.offer(0);

            while(!q.isEmpty()){
                int u = q.poll();

                for(int i=1; i<=nums[u]; ++i){
                    if(u+i < nums.length && dist[u+i] == 0) {
                        dist[u+i] = dist[u] + 1;
                        q.offer(u+i);
                    }
                }
            }

            return dist[nums.length-1];
        }

        //search range
        private int searchRangeHelper(int[] nums){
            int start=0;
            int end=0;
            int maxIdx = 0;
            int cnt = 0;

            while(end < nums.length-1){
                System.out.format("%d %d\n", start, end);
                for(int i=start; i<=end; ++i){
                    maxIdx = Math.max(nums[i] + i, maxIdx);
                }
                start = end+1;
                end= maxIdx;
                cnt++;
            }

            return cnt;
        }
    }
}
