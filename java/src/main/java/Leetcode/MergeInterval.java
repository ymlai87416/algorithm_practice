package Leetcode;

import java.util.*;

/*
number: 56
url: https://leetcode.com/problems/merge-intervals/
level: medium
solution: sort the array with the beginning, and then merge.
 */

public class MergeInterval {
    public static void main(String[] args){
        int[][] nums = new int[][] {{1,3}, {2,6}, {8,10}, {15,18}};
        Solution s = new Solution();
        int[][] r = s.merge(nums);
        for(int i=0; i<r.length; ++i)
            System.out.println(r[i][0] + " " + r[i][1]);
    }


    static
    class Solution {
        public int[][] merge(int[][] intervals) {
            ArrayList<Interval> list = new ArrayList<Interval>();

            for(int i=0; i<intervals.length; ++i)
                list.add(new Interval(intervals[i][0], intervals[i][1]));

            Collections.sort(list);

            for(int i=1; i<list.size(); ++i){
                if(list.get(i).canMerge(list.get(i-1))){
                    list.set(i, list.get(i).merge(list.get(i-1)));
                    list.set(i-1, null);
                }
            }

            int fsize =0;
            for(int i=0; i<list.size(); ++i){
                if(list.get(i) != null)
                    fsize++;
            }

            int[][] r = new int[fsize][2];
            int cnt = 0;
            for(int i=0; i<list.size(); ++i){
                if(list.get(i) != null) {
                    r[cnt][0] = list.get(i).start;
                    r[cnt][1] = list.get(i).end;
                    cnt++;
                }
            }

            return r;
        }
    }

    static
    class Interval implements Comparable<Interval>{
        int start;
        int end;

        public Interval(int s, int e){
            this.start = s;this.end= e;
        }

        public boolean canMerge(Interval a){
            return (a.start <= end && start <= a.start) ||    // head within the range
                    (start <= a.end && a.end <= end) ||       // tail within the range
                    (start <= a.start && a.end <= end) ||
                    (a.start <= start && end <= a.end);       // range within the range
        }

        public Interval merge(Interval a){
            if(canMerge(a))
                return new Interval(Math.min(a.start, start), Math.max(a.end, end));
            else
                return null;
        }

        @Override
        public int compareTo(Interval o) {
            return start - o.start;
        }
    }
}
