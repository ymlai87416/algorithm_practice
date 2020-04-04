package Leetcode;

import java.util.*;

/*
number: 347
url: https://leetcode.com/problems/top-k-frequent-elements/
level: medium
solution: simple counting using hashmap
 */

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = new int[]{4,1,-1,2,-1,2,3};
        int k = 2;
        Solution sol = new Solution();
        List<Integer> r = sol.topKFrequent(nums, k);
        System.out.println(r);
    }

    static
    class Solution {
        public List<Integer> topKFrequent(int[] nums, int k) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            for(int i=0; i<nums.length; ++i){
                hm.put(nums[i], hm.getOrDefault(nums[i], 0)+1);
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return hm.get(o1).compareTo(hm.get(o2));
                }
            });

            for(int i : hm.keySet()){
                if(pq.size() < k || hm.get(pq.peek()) < hm.get(i)){
                    //System.out.format("Add: %d, %d\n", i, hm.get(i));
                    pq.add(i);
                    if(pq.size() > k) {
                        //System.out.format("full, pop out: %d\n", pq.peek());
                        pq.poll();
                    }
                }
            }

            return new ArrayList<Integer>(pq);
        }
    }
}
