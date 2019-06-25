package Leetcode;

public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args){
        int[] nums= new int[]{4,5,6,7,0,1,2};

        Solution s = new Solution();
        System.out.println(s.findMin(nums));
    }

    static
    class Solution {
        public int findMin(int[] nums) {
            return iterativeHelper(nums);
        }

        private int iterativeHelper(int[] nums){
            int start, end, mid, pivot=0;
            start = 0; end= nums.length-1;

            while(start <= end){
                mid = (start+end)/2;

                if(mid > 0 && nums[mid-1] > nums[mid]) {
                    pivot = mid-1;
                    break;
                }
                else if(mid < nums.length-1 && nums[mid] > nums[mid+1]){
                    pivot = mid;
                    break;
                }
                else if(nums[start] == nums[mid] && nums[mid] == nums[end]){
                    pivot = -1;
                    for(int i=start+1; i<end; ++i){
                        if(nums[i-1] > nums[i]) {
                            pivot = i - 1;
                            break;
                        }
                    }
                    break;
                }
                else if(nums[start] > nums[mid])
                    end = mid-1;
                else
                    start = mid+1;

            }

            if(start > end) pivot = -1;

            //System.out.println("pivot point: " + pivot);

            return nums[pivot+1];
        }
    }

}
