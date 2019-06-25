package Leetcode;

public class FindPeekElement {
    public static void main(String[] args){
        int[] nums= new int[]{3,4,3,2,1};

        Solution s = new Solution();
        System.out.println(s.findPeakElement(nums));
    }


    static
    class Solution {
        public int findPeakElement(int[] nums) {
            return logHelper(nums);
        }

        private int linearHelper(int[] nums){
            int r = 0; int v = nums[0];
            for(int i=1; i<nums.length; ++i){
                if(nums[i] > v){
                    r = i;
                    v = nums[i];
                }
            }
            return r;
        }

        private int logHelper(int[] nums){
            if(nums.length < 3)
                return linearHelper(nums);

            int start =0;
            int end = nums.length-1;
            int mid;

            while(start <= end){
                mid = (start+end)/2;
                //System.out.format("%d %d %d\n", start, end, mid);

                if(mid == 0) {
                    if(nums[0] > nums[1])
                        return mid;
                    else
                        start = mid+1;
                }
                else if(mid == nums.length-1) {
                    if(nums[nums.length-2] < nums[nums.length-1])
                        return mid;
                    else
                        end = mid-1;
                }
                else if(nums[mid-1] < nums[mid] && nums[mid] > nums[mid+1]){
                    return mid;
                }
                else if(nums[mid-1] < nums[mid] && nums[mid] < nums[mid+1]){
                    //ascending, the peek should be after mid
                    start = mid+1;
                }
                else{
                    //descending, the peek should be before mid
                    end = mid-1;
                }
            }

            //should not be here.
            return start;
        }
    }
}
