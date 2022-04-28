package Leetcode.UberMock;

public class OnsiteQ1 {

    public int singleNumber(int[] nums) {
        int a = 0;
        for(int i=0; i<nums.length; ++i){
            a = a ^ nums[i];
        }

        return a;
    }

    public static void main(String[] args){

    }
}
