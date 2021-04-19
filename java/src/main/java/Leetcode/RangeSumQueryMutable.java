package Leetcode;

/**
number: 307
problem: https://leetcode.com/problems/range-sum-query-mutable/
level: medium
solution: just a range sum query

 #binaryIndexedTree #segmentTree

 **/

public class RangeSumQueryMutable {
    public static void main(String[] args){
        int[] nums = new int[]{};
        NumArray a = new NumArray(nums);
        System.out.println(a.sumRange(0, 2));
        a.update(1, 2);
        System.out.println(a.sumRange(0, 2));
    }

    static
    class NumArray {
        int[] st;
        int[] nums;

        public NumArray(int[] nums) {
            if(nums.length == 0) return;

            st = new int[nums.length*4];
            this.nums = nums;

            build(1, 0, nums.length-1);
        }

        private void build(int p, int l, int r){
            if(l==r)
                st[p] = nums[l];
            else{
                int mid = (l+r)/2;
                build(2*p, l , mid);
                build(2*p+1, mid+1, r);
                st[p] = st[2*p] + st[2*p+1];
            }
        }

        public void update(int i, int val) {
            //find p position and from there update
            update(1, 0, nums.length-1, i, val);
        }

        private void update(int p, int l, int r, int idx, int val){
            if(l==r) {
                nums[idx] = val;
                st[p] = val;
            }
            else{
                int mid = (l+r)/2;
                if(l <= idx && idx <= mid)
                    update(2*p, l, mid, idx, val);
                else
                    update(2*p+1, mid+1, r, idx, val);

                st[p] = st[2*p] + st[2*p+1];
            }
        }

        public int sumRange(int i, int j) {
            return rsq(1, 0, nums.length-1, i, j);
        }

        private int rsq(int p, int l, int r, int i, int j){
            if(i > r || j < l) return 0;
            if(i == l && j == r) return st[p];
            else{
                int mid = (l+r)/2;
                return rsq(2*p, l, mid, i, Math.min(j, mid)) + rsq(2*p+1, mid+1, r, Math.max(i, mid+1), j);
            }
        }
    }

}
