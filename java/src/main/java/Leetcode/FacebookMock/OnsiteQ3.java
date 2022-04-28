package Leetcode.FacebookMock;

import java.util.Arrays;

public class OnsiteQ3 {

    int[] nums;
    char[][] dp;

    public boolean canPartition(int[] nums) {
        this.nums = nums;
        this.dp = new char[nums.length][200*100+4];
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], 'X');
        }

        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            target += nums[i];
        }
        if(target %2 != 0) return false;
        return canReach(0, target/2);
    }

    private boolean canReach(int pos, int target){

        if(target == 0)
            return true;
        if(pos >= nums.length)
            return false; //run out of number

        if(dp[pos][target] != 'X')
            return dp[pos][target] == 'T' ? true: false;

        //System.out.println("D" + pos + " " + target);

        boolean a;
        if(nums[pos] <= target) {
            a = canReach(pos + 1, target - nums[pos]);
            if(a){
                dp[pos][target] = 'T';
                return true;
            }
        }

        a = canReach(pos+1, target);
        dp[pos][target] = a ? 'T' : 'F';
        return a;
    }

    public static void main(String[] args){
        OnsiteQ3 o = new OnsiteQ3();
        int[] nums = {1,5,11,5};
        System.out.println(o.canPartition(nums));

        nums = new int[]{1,2,100,5};
        System.out.println(o.canPartition(nums));
    }
}
