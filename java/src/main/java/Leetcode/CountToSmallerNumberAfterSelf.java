package Leetcode;

/**
problem: https://leetcode.com/problems/count-of-smaller-numbers-after-self/
level: hard
solution:

 BIT approach: using space of 2*10^4 to store a frequency table. 26ms
 tree approach: scan from the right. 4ms
 merge sort approach: do a merge sort, if there is a reversion (smaller element at right). +1 to all remaining of left array. 290ms

#binary_search #divide_and_conquer #sort #binary_indexed_tree #segment_tree

 */

import java.util.*;
import java.util.stream.Collectors;

public class CountToSmallerNumberAfterSelf {
    public static void main(String[] args){
        int[] nums = new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};

        Solution sol = new Solution();
        System.out.println(sol.countSmaller(nums));
    }

    static
    class Solution {
        int[] result;

        public List<Integer> countSmaller(int[] nums) {
            //return treeHelper(nums);
            return fenwickHelper(nums);
        }

        private List<Integer> treeMapHelper(int[] nums){
            result = new int[nums.length];

            TreeMap<Integer, Integer> map = new TreeMap<>();

            for(int i=nums.length-1; i>=0; --i){
                int c = nums[i];
                Map<Integer, Integer> subMap = map.subMap(Integer.MIN_VALUE, c);

                int rr = 0;
                for(Map.Entry<Integer, Integer> e: subMap.entrySet()){
                    rr += e.getValue();
                }
                map.put(c, map.getOrDefault(c, 0)+1);

                result[i] = rr;
            }

            return intArrayToList(result);
        }

        private List<Integer> sortHelper(int[] nums){
            result = new int[nums.length];

            int[] idxs= new int[nums.length];
            for(int i=0; i<idxs.length; ++i)
                idxs[i] = i;

            mergeSort(nums, idxs, 0, nums.length-1);

            return intArrayToList(result);
        }

        private void mergeSort(int[] nums, int[] idx, int i, int j){
            if(i >= j) return;

            int mid = (i+j)/2;
            mergeSort(nums, idx, i, mid);
            mergeSort(nums, idx, mid+1, j);

            int ptr1 = 0;
            int ptr2 = 0;
            int ptr = i;

            int[] arr1 = Arrays.copyOfRange(idx, i, mid+1);
            int[] arr2 = Arrays.copyOfRange(idx, mid+1, j+1);

            while(ptr1 < arr1.length || ptr2 < arr2.length){
                if(ptr1 >= arr1.length)
                    idx[ptr++] = arr2[ptr2++];
                else if(ptr2 >= arr2.length)
                    idx[ptr++] = arr1[ptr1++];
                else if(nums[arr1[ptr1]] <= nums[arr2[ptr2]])
                    idx[ptr++] = arr1[ptr1++];
                else{
                    //now we need to add all count in the first array
                    idx[ptr++] = arr2[ptr2++];
                    for(int p=ptr1; p<arr1.length; ++p){
                        System.out.format("Adding %d\n", arr1[p]);
                        result[arr1[p]]++;
                    }
                }
            }
        }

        private List<Integer> treeHelper(int[] nums){
            if(nums.length ==0) return Collections.emptyList();
            result = new int[nums.length];
            TreeNode root = new TreeNode(nums[nums.length-1]);
            for(int i=nums.length-2; i>=0; --i){
                insert(root, result, nums, i, 0);
            }

            return intArrayToList(result);
        }

        private List<Integer> intArrayToList(int[] result){
            ArrayList<Integer> a = new ArrayList<Integer>();
            for(int b: result)
                a.add(b);
            return a;
        }

        private void insert(TreeNode root, int[] res, int[] nums, int idx, int preSum){
            System.out.format("root val: %d, val: %d, preSum: %d\n", root.val, nums[idx], preSum);
            if(root.val == nums[idx]) {
                root.dup++;
                res[idx] = preSum+root.sum;
            }
            else if(root.val > nums[idx]) {
                root.sum++;
                if (root.left != null)
                    insert(root.left, res, nums, idx, preSum);
                else {
                    root.left = new TreeNode(nums[idx]);
                    res[idx] =preSum;
                }
            }
            else {
                if (root.right != null)
                    insert(root.right, res, nums, idx, preSum+root.sum+root.dup);
                else {
                    root.right = new TreeNode(nums[idx]);
                    res[idx] = preSum+root.sum+root.dup;
                }
            }
        }

        private List<Integer> fenwickHelper(int[] nums){
            FenwickTree ft = new FenwickTree(10000+10001);
            ArrayList<Integer> r = new ArrayList<>(nums.length);
            for (int i = nums.length-1; i >=0 ; i--) {
                r.add(ft.rsq(nums[i]+10000));
                ft.adjust(nums[i]+10001, 1);
            }
            Collections.reverse(r);
            return r;
        }
    }

    static
    class TreeNode{
        public int val, sum, dup;
        public TreeNode left, right;
        public TreeNode(int val){
            this.val = val;
            this.dup = 1;
        }
    }

    static
    class FenwickTree{
        private int[] ft;
        public FenwickTree(int n){
            ft = new int[n+1];
            Arrays.fill(ft, 0);
        }
        private int LSOne(int S){
            return S & (-S);
        }
        public int rsq(int b){
            int sum = 0;
            for (; b!=0 ; b -=LSOne(b))
                sum += ft[b];
            return sum;
        }
        public int rsq(int a, int b){
            return rsq(b) - (a == 1 ? 0: rsq(a-1));
        }
        public void adjust(int k, int v){
            for (; k<(int) ft.length; k+=LSOne(k))
                ft[k] +=v;
        }
    }
}
