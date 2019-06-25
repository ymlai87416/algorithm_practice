package Leetcode;

import java.util.ArrayList;

public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        Solution sol = new Solution();
        System.out.println(sol.insert(intervals, newInterval));
    }

    static
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {

            ArrayList<int[]> result = new ArrayList<>();
            boolean insert = false;
            for(int i=0; i<intervals.length; ++i){
                if(!insert && newInterval[0] < intervals[i][0]){
                    result.add(newInterval);
                    insert = true;
                }
                result.add(intervals[i]);
            }

            if(!insert)
                result.add(newInterval);

            int end = 0;

            for(int i=1; i<result.size(); ++i){
                int[] last = result.get(end);
                int[] curr = result.get(i);

                if(last[0] <= curr[0] && curr[0] <= last[1]){
                    last[1] = Math.max(last[1], curr[1]);
                }
                else
                    result.set(++end, curr);
            }

            int[][] result_ = new int[end+1][2];
            for(int i=0; i<=end; ++i){
                result_[i][0] = result.get(i)[0];
                result_[i][1] = result.get(i)[1];
            }

            return result_;
        }


    }
}
