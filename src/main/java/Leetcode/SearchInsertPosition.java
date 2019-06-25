package Leetcode;

public class SearchInsertPosition {
    public static void main(String[] args){
        int[] a = {1,3,5,6};
        Solution s = new Solution();
        System.out.println(s.searchInsert(a, 7));
    }

    static
    class Solution {
        public int searchInsert(int[] nums, int target) {
            int r = searchInsertPosition(nums, target);
            return r;
        }

        private int searchInsertPosition(int[] nums, int target){
            int start = 0;
            int end = nums.length;
            while(start < end){
                int mid = (start+end)/2;
                if(nums[mid] == target) return mid;
                if(nums[mid] < target){
                    start = mid+1;
                }
                else{
                    end = mid;      //different for exact, which end=mid+1;
                }
            }
            return start;
        }
    }
}
