package Leetcode;

public class KthSmallestElementInASortedMatrix {
    public static void main(String[] args){
        int[][] nums = new int[][] {
                {1,  5,  9},
                {10, 11, 13},
        {12, 13, 15}
        };
        Solution s = new Solution();
        System.out.println(s.kthSmallest(nums, 8));
    }


    static
    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            return helper(matrix, k);
        }

        //heap nlogk => 7ms 64.23%
        private int helper(int[][] matrix, int k){
            int[] t = new int[k];
            for(int i=0; i<k; ++i)
                t[i] = matrix[i/matrix[0].length][i%matrix[0].length];

            MaxHeap mh = new MaxHeap(t, t.length);

            for(int i=0; i<matrix.length; ++i){
                for(int j=0; j<matrix[i].length; ++j){
                    if(i*matrix[0].length + j < k) continue;
                    if(matrix[i][j] < mh.getMax()) {
                        mh.replaceMax(matrix[i][j]);
                    }
                }
            }

            return mh.extractMax();
        }
    }

    static
    class MaxHeap{
        int[] array;
        int capacity;
        int heapSize;

        public MaxHeap(int[] a, int size){
            heapSize = size;
            array =  a;
            int i = (heapSize-1)/2;

            while(i>=0) {
                MaxHeapify(i);
                --i;
            }
        }

        void MaxHeapify(int i){
            int l = left(i);
            int r  = right(i) ;
            int largest = i;
            if(l < heapSize && array[l] > array[i])
                largest = l;
            if(r < heapSize && array[r] > array[largest])
                largest = r;
            if(largest != i){
                swap(i, largest);
                MaxHeapify(largest);
            }
        }

        int right(int i) { return 2*i+2; }
        int left(int i){return 2*i+1; }
        int parent(int i) {return (i-1)/2;}

        void replaceMax(int x){
            array[0] = x; MaxHeapify(0);
        }

        int extractMax(){
            if(heapSize == 0) return Integer.MIN_VALUE;
            int root = array[0];

            if(heapSize > 1){
                array[0] = array[heapSize-1];
                MaxHeapify(0);
            }
            heapSize--;
            return root;
        }

        int getMax(){
            return array[0];
        }

        void swap(int i, int j){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }


    }
}
