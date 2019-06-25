package Leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class KthLargestElementInAnArray {
    public static void main(String[] args){
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 4;

        Solution s = new Solution();
        System.out.println(s.findKthLargest(nums, k));
    }


    static
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            //return kLargestHelper(nums, 0, nums.length-1, k);
            return maxHeapHelper(nums, k);
        }

        //nlogn: 3ms => 83.04
        private int sortHelper(int[] nums, int k){
            Arrays.sort(nums);
            return nums[nums.length-k];
        }


        private int minHeapHelper(int[] nums, int k){
            /*
            MinHeap mh = new MinHeap(nums, nums.length-k+1);
            //the heap now have have n-k elements, root assume to the k-largest
            for(int i=nums.length-k+1; i<nums.length; ++i){
                if(nums[i] > mh.getMin())
                    mh.replaceMin(nums[i]);
            }
            return mh.getMin();
            */
            return 0;
        }

        //O(n + klogn) 3ms = 83.04%
        private int maxHeapHelper(int[] nums, int k){
            MaxHeap mh = new MaxHeap(nums, nums.length);
            for(int i=0; i<k-1; ++i){
                int b = mh.extractMax();
                //System.out.println(b);
            }

            return mh.extractMax();
        }

        private void swap(int[] nums, int a, int b){
            int t = nums[a];
            nums[a] = nums[b];
            nums[b] = t;
        }

        //randomize pivot amortized O(n) 2ms => 94.15%
        private int kLargestHelper(int[] nums, int l, int r, int k){
            System.out.format("%d %d %d\n", l, r, k);
            if(k > 0 && k <= r-l+1){
                int pos  = randomPartition(nums, l, r);

                if(r-pos == k-1)
                    return nums[pos];
                else if(r-pos < k-1)
                    //recur to left side, now k largest to k - (r - pos) - 1 ) largest
                    //e.g k = 4, pos = 3, r = 5, l=0. 4th largest is the nums[2].
                    //now in left array, l=0, r=2. 4th largest become 4-(5-3)-1 = 1th largest.
                    return kLargestHelper(nums, l, pos-1, k-(r-pos)-1);
                return kLargestHelper(nums, pos+1, r, k);
            }

            return Integer.MAX_VALUE;
        }

        private int randomPartition(int[] nums, int lo, int hi){
            int rIdx = new Random().nextInt(hi-lo+1) + lo;
            swap(nums, rIdx, hi);
            return partition(nums, lo, hi);
        }

        private int partition(int[] nums, int l, int r){
            int x = nums[r], i=l;
            for(int j=l; j<=r-1; ++j) {
                if(nums[j] <= x){
                    swap(nums, i, j);
                    ++i;
                }
            }
            swap(nums, i, r);
            return i;
        }

        //divide by 5, worst case linear time
        private int medianHelper(int[] nums, int k){
            return 0;
        }
    }

    static
    class MinHeap
    {
        int[] array;
        int capacity;
        int heapSize;

        //O(n)
        public MinHeap(int[] a, int size){
            heapSize = size;
            array = a;
            int i = (heapSize-1)/2;
            while(i >= 0 ){
                MinHeapify(i);
                i--;
            }
        }

        void replaceMin(int x) { array[0] = x;  MinHeapify(0); }

        void swap(int[] array, int i, int j){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        void MinHeapify(int i){
            int l = left(i);
            int r = right(i);
            int smallest=i;
            if(l < heapSize && array[l] < array[i])
                smallest = l;
            if(r < heapSize && array[r] < array[smallest])
                smallest = r;
            if(smallest != i){
                swap(array, i, smallest);
                MinHeapify(smallest);
            }
        }

        int parent(int i) { return (i-1)/2;}
        int left(int i) { return 2*i+1;}
        int right(int i) { return 2*i+2;}

        int extractMin(){
            if(heapSize == 0) return Integer.MAX_VALUE;
            int root = array[0];

            //remove the root, get the largest element and heapify again.
            if(heapSize > 1){
                array[0]= array[heapSize-1];
                MinHeapify(0);
            }
            heapSize--;

            return root;
        }

        int getMin() { return array[0]; }
    }

    static
    class MaxHeap{
        int[] array;
        int capacity;
        int heapSize;

        //O(n)
        public MaxHeap(int[] a, int size){
            heapSize = size;
            array = a;
            int i = (heapSize-1)/2;
            while(i >= 0 ){
                MaxHeapify(i);
                i--;
            }
        }

        void replaceMax(int x) { array[0] = x;  MaxHeapify(0); }

        void swap(int[] array, int i, int j){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        void MaxHeapify(int i){
            int l = left(i);
            int r = right(i);
            int largest=i;
            if(l < heapSize && array[l] > array[i])
                largest = l;
            if(r < heapSize && array[r] > array[largest])
                largest = r;
            if(largest != i){
                swap(array, i, largest);
                MaxHeapify(largest);
            }
        }

        int parent(int i) { return (i-1)/2;}
        int left(int i) { return 2*i+1;}
        int right(int i) { return 2*i+2;}

        int extractMax(){
            if(heapSize == 0) return Integer.MAX_VALUE;
            int root = array[0];

            //remove the root, get the largest element and heapify again.
            if(heapSize > 1){
                array[0]= array[heapSize-1];
                MaxHeapify(0);
            }
            heapSize--;

            return root;
        }

        int getMin() { return array[0]; }
    }
}
