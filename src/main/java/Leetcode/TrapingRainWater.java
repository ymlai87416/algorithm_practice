package Leetcode;

import java.util.Stack;

public class TrapingRainWater {
    public static void main(String[] args) {
        int[] input = new int[]{10, 2, 0, 0, 0, 3};
        Solution s = new Solution();
        int r = s.trap(input);
        System.out.println(r);
    }

    static
    class Solution {
        //from DP approach, current water = Math.min(max_left, max_right).
        public int trap(int[] height) {
            int left = 0, right = height.length - 1;
            int ans = 0;
            int left_max = 0, right_max = 0;
            while (left < right) {
                if (height[left] < height[right]) {
                    if(height[left] >= left_max)
                        left_max = height[left];
                    else
                        ans += (left_max - height[left]);
                    ++left;
                }
                else {
                    if(height[right] >= right_max)
                        right_max = height[right];
                    else
                        ans += (right_max - height[right]);
                    --right;
                }
            }
            return ans;
        }

        public int trapST(int[] height) {
            int ans = 0, current = 0;
            Stack<Integer> st = new Stack<Integer>();
            while (current < height.length) {
                while (!st.isEmpty() && height[current] > height[st.peek()]) {
                    int top = st.peek();
                    st.pop();
                    if (st.empty())
                        break;
                    int distance = current - st.peek() - 1;
                    /*
                        st.peek() must be the heightest wall just taller than current
                        e.g. 4,2,3. current = 3, top = 2, peek = 4
                        e.g. 4,2,1,3. current = 3, top = 1, peek = 2
                     */
                    int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
                    ans += distance * bounded_height;
                }
                st.push(current++);
            }
            return ans;
        }

        public int trapBF(int[] height){
            int ans=0;
            for(int i=0; i<height.length; ++i){
                //find left, right, add 1xMath.min(max_left-max_right)
                int maxLeft, maxRight;
                maxLeft = maxRight = height[i];
                for(int j=i-1; j>=0; --j) maxLeft = Math.max(maxLeft, height[j]);
                for(int j=i+1; j<height.length; ++j) maxRight = Math.max(maxRight, height[j]);
                ans += Math.min(maxLeft, maxRight) - height[i];
            }
            return ans;
        }

        public int trapDP(int[] height){
            int ans=0;
            int[] maxLeft, maxRight;
            maxLeft = new int[height.length];
            maxRight = new int[height.length];

            int c = 0;
            for(int i=0; i<height.length; ++i)
                maxLeft[i] = c = Math.max(c, height[i]);
            c = 0;
            for(int i=height.length-1; i>=0; --i)
                maxRight[i] = c = Math.max(c, height[i]);

            for(int i=0; i<height.length; ++i){
                //find left, right, add 1xMath.min(max_left-max_right)
                ans += Math.min(maxLeft[i], maxRight[i]) - height[i];
            }
            return ans;
        }
    }


}
