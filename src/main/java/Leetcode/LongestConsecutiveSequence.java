package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class LongestConsecutiveSequence {
    public static void main(String[] args){
        int[] nums = new int[]{100,4,200,1,3,2};
        Solution sol = new Solution();
        System.out.println(sol.longestConsecutive(nums));
    }

    static
    class Solution {
        public int longestConsecutive(int[] nums) {
            //return disjointSetHelper(nums);
            return sortHelper(nums);
        }

        //O(nlogn) 4ms
        private int sortHelper(int[] nums){
            if(nums.length == 0) return 0;
            Arrays.sort(nums);

            int r = 1;
            int maxR = 1;
            for(int i=1; i<nums.length; ++i){
                if(nums[i] == nums[i-1]) continue;
                if(nums[i] == nums[i-1]+1) {
                    r++;
                    maxR = Math.max(maxR, r);
                }
                else
                    r = 1;
            }

            return maxR;
        }

        // ~ O(n) 8ms
        private int disjointSetHelper(int[] nums){
            HashMap<Integer, Integer> reverseIdx = new HashMap<>();
            DisjointSet djSet = new DisjointSet(nums);
            for(int i=0; i<nums.length; ++i){
                if(reverseIdx.containsKey(nums[i])) continue;
                reverseIdx.put(nums[i], i);

                if(reverseIdx.containsKey(nums[i]-1)){
                    int pIdx = reverseIdx.get(nums[i]-1);
                    djSet.union(pIdx, i);
                }
                if(reverseIdx.containsKey(nums[i]+1)){
                    int nIdx = reverseIdx.get(nums[i]+1);
                    djSet.union(nIdx, i);
                }
            }

            int max = 0;
            for(int i=0; i<djSet.size.length; ++i){
                max = Math.max(max, djSet.size[i]);
            }
            return max;
        }

        //there is a linear solution. study it?

    }


    static
    class DisjointSet{
        public int[] parent;
        public int[] size;
        public int[] nums;
        public DisjointSet(int[] nums){
            parent = new int[nums.length];
            size = new int[nums.length];
            this.nums = nums;
            for(int i=0; i<nums.length; ++i) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int xRoot = find(x);
            int yRoot = find(y);

            if(xRoot  == yRoot) return;
            if(size[xRoot] < size[yRoot]){
                int t = xRoot;
                xRoot = yRoot;
                yRoot = t;
            }

            parent[yRoot] = xRoot;
            size[xRoot] = size[xRoot] + size[yRoot];

        }
    }
}
