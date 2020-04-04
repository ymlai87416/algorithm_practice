package Leetcode;

/*
number: 75
url: https://leetcode.com/problems/sort-colors/
level: easy
solution: just 2 ptr to in place sort 0 and 2. done
 */

public class SortColors {
    public static void main(String[] args){
        int[] a = new int[] {2,0,1} ;
        Solution s = new Solution();
        s.sortColors(a);

        for(int i=0; i<a.length; ++i)
            System.out.print(a[i]);
        System.out.println();
    }

    static
    class Solution {
        public void sortColors(int[] nums) {
            int ptr = 0;
            int ptr0 = 0;
            int ptr2 = nums.length-1;

            while(ptr<= ptr2){
                while(nums[ptr] == 2 && ptr < ptr2) {
                    swap(nums, ptr, ptr2);
                    --ptr2;
                }
                while(nums[ptr] == 0 && ptr > ptr0) {
                    swap(nums, ptr, ptr0);
                    ++ptr0;
                }
                ++ptr;
            }

        }

        private void swap(int[] nums, int a, int b){
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
