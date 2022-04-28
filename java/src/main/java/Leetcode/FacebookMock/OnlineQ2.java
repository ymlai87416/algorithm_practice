package Leetcode.FacebookMock;

import java.util.HashMap;
import java.util.Stack;

public class OnlineQ2 {


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<Integer>();
        int[] nextGreater = new int[nums2.length];

        for (int i = 0; i < nums2.length; i++) {
            nextGreater[i] = -1;
        }

        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums2.length; i++) {
            hm.put(nums2[i], i);
            int curr = nums2[i];

            while(!st.isEmpty()){
                int v= st.peek();
                int el = nums2[v];
                if(el < curr){
                    nextGreater[v] = curr; //store the element directly
                    st.pop();
                }
                else break;
            }

            st.push(i);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater[hm.get(nums1[i])];
        }

        return result;
    }

    public static void main(String[] args){
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        OnlineQ2 s = new OnlineQ2();
        System.out.println(s.nextGreaterElement(nums1, nums2));

        nums1 = new int[]{2,4};
        nums2 = new int[]{1,2,3,4};

        System.out.println(s.nextGreaterElement(nums1, nums2));
    }
}
