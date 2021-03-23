package Leetcode;

import java.util.Arrays;

public class NextPermutation {
    class Solution {
        public void nextPermutation(int[] nums) {

            if(nums.length == 1) return;

            // Find longest non-increasing suffix
            int i = nums.length - 1;
            while (i > 0 && nums[i - 1] >= nums[i])
                i--;
            // Now i is the head index of the suffix

            // Are we at the last permutation already?
            if (i <= 0){
                Arrays.sort(nums);
                return;
            }

            // Let array[i - 1] be the pivot
            // Find rightmost element that exceeds the pivot
            int j = nums.length - 1;
            while (nums[j] <= nums[i - 1])
                j--;
            // Now the value array[j] will become the new pivot
            // Assertion: j >= i

            // Swap the pivot with j
            int temp = nums[i - 1];
            nums[i - 1] = nums[j];
            nums[j] = temp;

            // Reverse the suffix
            j = nums.length - 1;
            while (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }

            // Successfully computed the next permutation
            //return true;
        }
    }
}
