package Leetcode;

import java.util.Stack;

/*
number: 84
url: https://leetcode.com/problems/largest-rectangle-in-histogram/
level: hard
solution: O(n) finding left and right, and then completed
 */

public class LargestRectangleInHistogram {

    public static void main(String[] args){
        int[] input = {5,5,1,7,1,1,5,2,7,6};
        Solution s = new Solution();
        System.out.println(s.largestRectangleArea(input));
    }

    static
    class Solution {
        public int largestRectangleArea(int[] heights) {

            if(heights.length == 0)
                return 0;

            /*
            SegmentTreeRMQ rmq = new SegmentTreeRMQ();
            rmq.constructST(heights, heights.length);

            return helper(heights, rmq, 0, heights.length-1);
            */

            return helper2(heights);
        }

        private int helper(int[] heights, SegmentTreeRMQ rmq, int ptr1, int ptr2){
            System.out.format("%d %d\n", ptr1, ptr2);
            if(ptr2 < ptr1) return 0;

            /*
            int min = heights[ptr1];
            int minIdx = ptr1;
            for(int i=ptr1; i<=ptr2; ++i) {
                if(min > heights[i]) {
                    min = heights[i];
                    minIdx = i;
                }
            }*/
            int minIdx = rmq.RMQ(heights.length, ptr1, ptr2);

            return Math.max(heights[minIdx] * (ptr2-ptr1+1), Math.max(helper(heights, rmq, ptr1, minIdx-1), helper(heights, rmq,minIdx+1, ptr2)));
        }

        //linear time
        private int helper2(int[] heights){
            //left record the point smaller than current
            int[] left = new int[heights.length];
            int r = 0;

            //int[] right = new int[heights.length];

            left[0] = 0;
            Stack<Integer> st = new Stack<Integer>();
            st.push(0);
            for(int i=1; i<heights.length; ++i){
                left[i]=0;
                while(!st.isEmpty()){
                    int u = st.peek();
                    if(heights[u] < heights[i]) {
                        left[i] = u+1;
                        break;
                    }
                    else
                        st.pop();
                }

                //if nothing left = 0;
                st.push(i);
            }

            st.clear();

            //right[heights.length-1] = heights.length-1;
            r = Math.max(r, heights[heights.length-1] * (heights.length-1 - left[heights.length-1] + 1));
            int right;

            st.push(heights.length-1);
            for(int i=heights.length-1; i>=0; --i){
                right=heights.length-1;

                while(!st.isEmpty()){
                    int u = st.peek();
                    if(heights[u] < heights[i]) {
                        right = u-1;
                        break;
                    }
                    else
                        st.pop();
                }

                //if nothing left = 0;
                st.push(i);

                r = Math.max(r, heights[i] * (right - left[i] + 1));
            }

            /*
            for(int i=0; i<heights.length; ++i)
                System.out.print(heights[i] + " ");
            System.out.println();

            for(int i=0; i<heights.length; ++i)
                System.out.print(left[i] + " ");
            System.out.println();

            for(int i=0; i<heights.length; ++i)
                System.out.print(right[i] + " ");
            System.out.println();
            */

            return r;
        }
    }

    static
    class SegmentTreeRMQ
    {
        int st[]; //array to store segment tree
        int arr[];

        // A utility function to get minimum of two numbers
        int minVal(int arr[],int x, int y) {
            if(x == Integer.MAX_VALUE) return y;
            else if(y == Integer.MAX_VALUE) return x;
            return (arr[x] < arr[y]) ? x : y;
        }

        // A utility function to get the middle index from corner
        // indexes.
        int getMid(int s, int e) {
            return s + (e - s) / 2;
        }

        /*  A recursive function to get the minimum value in a given
            range of array indexes. The following are parameters for
            this function.

            st    --> Pointer to segment tree
            index --> Index of current node in the segment tree. Initially
                       0 is passed as root is always at index 0
            ss & se  --> Starting and ending indexes of the segment
                         represented by current node, i.e., st[index]
            qs & qe  --> Starting and ending indexes of query range */
        int RMQUtil(int[] arr, int ss, int se, int qs, int qe, int index)
        {
            // If segment of this node is a part of given range, then
            // return the min of the segment
            if (qs <= ss && qe >= se)
                return st[index];

            // If segment of this node is outside the given range
            if (se < qs || ss > qe)
                return Integer.MAX_VALUE;

            // If a part of this segment overlaps with the given range
            int mid = getMid(ss, se);
            return st[minVal(arr, RMQUtil(arr, ss, mid, qs, qe, 2 * index + 1),
                    RMQUtil(arr, mid + 1, se, qs, qe, 2 * index + 2))];
        }

        // Return minimum of elements in range from index qs (quey
        // start) to qe (query end).  It mainly uses RMQUtil()
        int RMQ(int n, int qs, int qe)
        {
            // Check for erroneous input values
            if (qs < 0 || qe > n - 1 || qs > qe) {
                System.out.println("Invalid Input");
                return -1;
            }

            return RMQUtil(arr, 0, n - 1, qs, qe, 0);
        }

        // A recursive function that constructs Segment Tree for
        // array[ss..se]. si is index of current node in segment tree st
        int constructSTUtil(int arr[], int ss, int se, int si)
        {
            // If there is one element in array, store it in current
            //  node of segment tree and return
            if (ss == se) {
                st[si] = arr[ss];
                return ss;
            }

            // If there are more than one elements, then recur for left and
            // right subtrees and store the minimum of two values in this node
            int mid = getMid(ss, se);
            st[si] = minVal(arr, constructSTUtil(arr, ss, mid, si * 2 + 1),
                    constructSTUtil(arr, mid + 1, se, si * 2 + 2));
            return st[si];
        }

        /* Function to construct segment tree from given array. This function
           allocates memory for segment tree and calls constructSTUtil() to
           fill the allocated memory */
        void constructST(int arr[], int n)
        {
            // Allocate memory for segment tree

            //Height of segment tree
            int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

            //Maximum size of segment tree
            int max_size = 2 * (int) Math.pow(2, x) - 1;
            st = new int[max_size]; // allocate memory
            this.arr = arr;

            // Fill the allocated memory st
            constructSTUtil(arr, 0, n - 1, 0);
        }

        // Driver program to test above functions
        public static void main(String args[])
        {
            int arr[] = {1, 3, 2, 7, 9, 11};
            int n = arr.length;
            SegmentTreeRMQ tree = new SegmentTreeRMQ();

            // Build segment tree from given array
            tree.constructST(arr, n);

            int qs = 1;  // Starting index of query range
            int qe = 5;  // Ending index of query range

            // Print minimum value in arr[qs..qe]
            System.out.println("Minimum of values in range [" + qs + ", "
                    + qe + "] is = " + tree.RMQ(n, qs, qe));
        }
    }
}
