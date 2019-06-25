package Leetcode;

import java.util.*;

public class ContainsDuplicateIII {

    public static void main(String[] args){
        int[] input = {-1, -1};
        Solution s = new Solution();
        System.out.println(s.containsNearbyAlmostDuplicate(input, 1, -1));
    }

    static
    class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            return helper3(nums, k, t);
        }

        //O(nk) algorithm, simple 360ms -> 10%
        private boolean helper3(int[] nums, int k, int t) {
            for(int i=0; i<nums.length; i++) {
                for(int j=0; j<k && i+j+1<nums.length; j++) {
                    if((Math.abs((long)nums[i+j+1]-nums[i]) <= t)) {
                        return true;
                    }
                }
            }
            return false;
        }

        //O(nklog(k)), 22ms
        private boolean helper2(int[] nums, int k, int t) {
            if (k == 0 || t < 0)  // k = 0 => no windows, t < 0 cannot happens
                return false;

            int ptr1, ptr2;
            TreeMap<Long, Integer> winK = new TreeMap<Long, Integer>();

            ptr1 = 0;
            ptr2 = 0;

            for (ptr1 = 1; ptr1 < nums.length; ++ptr1) {
                long cur = nums[ptr1];
                long prev = nums[ptr1-1];

                if(winK.containsKey(prev))
                    winK.put(prev, winK.get(prev)+1);
                else
                    winK.put(prev, 1);

                if(ptr1 - ptr2 > k){
                    long remove = nums[ptr2];
                    if(winK.get(remove) == 1)
                        winK.remove(remove);
                    else
                        winK.put(remove, winK.get(remove)-1);
                    ptr2++;
                }

                if(!winK.subMap(cur-t, true, cur+t, true).isEmpty())
                    return true;
            }
            return false;
        }

        //this is a fucking O(nk)
        private boolean helper(int[] nums, int k, int t) {

            if (k == 0)
                return false;

            int ptr1, ptr2;
            Deque<Long> minMaxK = new ArrayDeque<Long>();

            ptr1 = 0;
            ptr2 = 0;

            for (ptr1 = 1; ptr1 < nums.length; ++ptr1) {
                //add ptr1 -- to k
                long tmp = nums[ptr1 - 1];
                long cur = nums[ptr1];

                System.out.format("Add %d\n", tmp);
                if (minMaxK.isEmpty() || tmp < minMaxK.getFirst())
                    minMaxK.offerFirst(tmp);
                else if (minMaxK.isEmpty() || tmp > minMaxK.getLast())
                    minMaxK.offerLast(tmp);

                //advance ptr2 if ptr1-ptr2 > k
                if (ptr1 - ptr2 > k) {
                    tmp = nums[ptr2];
                    System.out.format("Remove %d\n", tmp);
                    if (tmp == minMaxK.getFirst())
                        minMaxK.pollFirst();
                    else if (tmp == minMaxK.getLast())
                        minMaxK.pollLast();
                    ++ptr2;
                }

                long min = minMaxK.peekFirst();
                long max = minMaxK.peekLast();

                System.out.format("Min Max %d %d\n", min, max);
                //if(nums[ptr1] - t <= min || max <= nums[ptr1] + t)
                if (Math.abs(cur - min) <= t || Math.abs(cur - max) <= t)
                    return true;
                else {
                    //i cannot tell
                    for (int p = 1; p <= k && ptr1 - p >= 0; ++p) {
                        tmp = nums[ptr1 - p];
                        if (Math.abs(tmp - cur) <= t)
                            return true;
                    }
                }
            }

            return false;
        }
    }
}
