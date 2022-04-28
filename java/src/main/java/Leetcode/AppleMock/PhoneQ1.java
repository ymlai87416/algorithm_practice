package Leetcode.AppleMock;

public class PhoneQ1 {

    public int[] sortArrayByParity(int[] nums) {
        int evenPtr = 0;    //point to the next odd
        int oddPtr  = nums.length-1; //point to the next even

        while(evenPtr < nums.length && nums[evenPtr] %2==0)evenPtr++;
        while(oddPtr >= 0 && nums[oddPtr] %2==1)oddPtr--;

        while(evenPtr < oddPtr){
            if(nums[evenPtr] %2==1){
                swap(nums, evenPtr, oddPtr);
                while(evenPtr < nums.length && nums[evenPtr] %2==0)evenPtr++;
                while(oddPtr >= 0 && nums[oddPtr] %2==1)oddPtr--;
            }
        }

        return nums;
    }

    private void swap(int[] nums, int ptr1, int ptr2){
        int t = nums[ptr1];
        nums[ptr1] = nums[ptr2];
        nums[ptr2] = t;
    }

    public static void main(String[] args){
        int[] nums = {3, 1, 2, 4};
        PhoneQ1 p = new PhoneQ1();
        int[] nums2 = p.sortArrayByParity(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums2[i]);
        }
    }
}
