package Leetcode.GoogleMock;

import java.util.HashMap;

/**
 problem: https://leetcode.com/problems/my-calendar-ii/
 level: medium
 solution: panic and give a hard to implement solution, no way.

 #segment_tree #tree_map #google_online_mock
 **/

public class OnlineQ1 {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        HashMap<Integer, Integer> hs = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            hs.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = hs.get(nums1[i]);
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums1 = new int[]{12,28,46,32,50};
        int[] nums2 = new int[]{50,12,32,46,28};
        OnlineQ1 q = new OnlineQ1();
        System.out.println(q.anagramMappings(nums1, nums2));
    }
}
