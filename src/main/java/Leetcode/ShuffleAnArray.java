package Leetcode;

import java.util.Random;

public class ShuffleAnArray {

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3};
        Solution solution = new Solution(nums);

        // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
        solution.shuffle();

        // Resets the array back to its original configuration [1,2,3].
        solution.reset();

        // Returns the random shuffling of array [1,2,3].
        solution.shuffle();
    }

    static
    class Solution {
        int[] array, temp;
        Random r;
        public Solution(int[] nums) {
            array = nums;
            temp = new int[array.length];
            for(int i=0; i<array.length; ++i)
                temp[i] = array[i];
            r = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return array;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for(int i=temp.length-1; i>=1; --i){
                int j=  r.nextInt(i+1);
                swap(temp, i, j);
            }

            return temp;
        }

        private void swap(int[] a, int i, int j){
            int temp = a[i];
            a[i]= a[j];
            a[j] = temp;
        }
    }
}
