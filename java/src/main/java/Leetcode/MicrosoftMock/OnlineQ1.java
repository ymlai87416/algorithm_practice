package Leetcode.MicrosoftMock;

public class OnlineQ1 {

    public void sortColors(int[] nums) {
        int p0= 0;
        int p2 = nums.length-1;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                p0++;
            }
            else if(nums[i] == 2){
                p2--;
            }
        }

        for(int i=0; i<nums.length; ++i){
            if(i< p0) nums[i] = 0;
            else if(i > p2) nums[i] = 2;
            else nums[i] = 1;
        }

    }

    private void swap(int[] nums, int p1, int p2){
        int t = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = t;
    }

    public static void main(String[] args){

    }
}
