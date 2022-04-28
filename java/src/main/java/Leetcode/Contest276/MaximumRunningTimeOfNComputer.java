package Leetcode.Contest276;

/**
 problem:
 level: hard
 solution:
704->735
 #math
 **/

public class MaximumRunningTimeOfNComputer {
    public long maxRunTime(int n, int[] batteries) {
        /*
        we can have a strategy of every minutes, update the battery to the best, but this will depend on battery[i]
        terminate when only battery not charge is < n

        given a time, each battery can only output t power
         */

        long tpower = 0;
        for (int i = 0; i < batteries.length; i++) {
            tpower += batteries[i];
        }

        if(tpower < n) return 0;
        long s = 0;
        long e = (tpower / n)+1;

        while(s < e){
            long mid = (s+e)/2;
            if(mid == s) break;

            //now assume all battery is at most m minutes, can it support all n PC;
            tpower = 0;
            for (int i = 0; i < batteries.length; i++) {
                tpower += Math.min(mid, batteries[i]);
            }

            if(tpower >= mid * n){
                s = mid;
            }
            else{
                e = mid;
            }
        }

        return s;
    }


    public static void main(String[] args){
        MaximumRunningTimeOfNComputer s = new MaximumRunningTimeOfNComputer();

        //System.out.println(s.maxRunTime(2, new int[]{3,3,3}));
        //System.out.println(s.maxRunTime(2,new int[]{1,1,1,1}));
    }
}
