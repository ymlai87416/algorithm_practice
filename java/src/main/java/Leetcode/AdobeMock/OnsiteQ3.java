package Leetcode.AdobeMock;

import java.util.Arrays;
import java.util.Comparator;

public class OnsiteQ3 {

    //O(n^2) not work
    //this is 2 ptr approach, and you failed....

    public int maxArea(int[] height) {
        int ptr1 = 0;
        int ptr2 = height.length-1;

        int maxWater = (ptr2-ptr1) * Math.min(height[ptr1], height[ptr2]);

        while(ptr1 != ptr2){
            if(height[ptr1] < height[ptr2])
                ++ptr1;
            else
                --ptr2;
            maxWater  = Math.max(maxWater, (ptr2-ptr1) * Math.min(height[ptr1], height[ptr2]));
        }

        return maxWater;
    }

    public static void main(String[] args){
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        height = new int[]{2,3,4,5,18,17,6};
        OnsiteQ3 s= new OnsiteQ3();
        System.out.println(s.maxArea(height));

    }
}
