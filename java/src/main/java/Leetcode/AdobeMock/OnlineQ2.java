package Leetcode.AdobeMock;

import java.awt.event.KeyAdapter;
import java.util.TreeSet;

/*
First time to write a heapify
* the smallest left and right must be <= instead of <, if left==right, you got stuck.
* delay building of the heap if there is not enough k.
* forgot the root, left, right, and from (k-1)/2 start to build the heap.

heap can be replace by pirority queue.
 */

public class OnlineQ2 {

    static class KthLargest {

        int[] heap = null;  //this is the min heap
        int k;
        int size;

        public KthLargest(int k, int[] nums) {
            this.heap = new int[k];
            this.k = k;

            if(nums.length > k) {
                for (int i = 0; i < Math.min(k, nums.length); i++) {
                    this.heap[i] = nums[i];
                }

                buildHeap();

                for (int i = k; i < nums.length; ++i) {
                    if (nums[i] > heap[0]) {
                        heap[0] = nums[i];
                        heapify(0);
                    }
                }
                size = k;
            }
            else{
                //delay the building
                size = nums.length;
                for (int i=0; i<nums.length; ++i){
                    heap[i] = nums[i];
                }
            }
        }

        private void buildHeap(){
            int loc = (k-1)/2;
            while(loc >= 0 ){
                heapify(loc);
                loc--;
            }
        }

        private void heapify(int loc){
            int val = heap[loc];

            boolean leftSmallest = left(loc) >= k ? false :
                    (heap[left(loc)] < val && (right(loc) >= k ? true: heap[left(loc)] <= heap[right(loc)]) );
            boolean rightSmallest = right(loc) >= k ? false :
                    (heap[right(loc)] < val && (left(loc) >= k ? true: heap[right(loc)] <= heap[left(loc)]) );

            if(leftSmallest){
                int left = heap[left(loc)];
                heap[loc] = left;
                heap[left(loc)] = val;
                heapify(left(loc));
            }
            else if(rightSmallest){
                int right = heap[right(loc)];
                heap[loc] = right;
                heap[right(loc)] = val;
                heapify(right(loc));
            }
        }

        private int root(int a){return (a-1)/2;}
        private int left(int a){return a*2+1;}
        private int right(int a){return a*2+2;}


        public int add(int val) {
            if(size < k){
                heap[size] = val;
                buildHeap();
                ++size;
                return heap[0];
            }

            if(val > heap[0]){
                heap[0] = val;
                heapify(0);
            }

            return heap[0];
        }
    }

    public static void main(String[] args){
        KthLargest k = new KthLargest(3, new int[]{1,1});
        System.out.println(k.add(1));
        System.out.println(k.add(1));
        System.out.println(k.add(3));
        System.out.println(k.add(3));
        System.out.println(k.add(3));
        System.out.println(k.add(4));
        System.out.println(k.add(4));
        System.out.println(k.add(4));


    }
}
