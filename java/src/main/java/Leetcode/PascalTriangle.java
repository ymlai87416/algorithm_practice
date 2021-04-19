package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
number: 118
problem: https://leetcode.com/problems/pascals-triangle/
level: easy
solution: just do the math

 #array

**/

public class PascalTriangle {
    public static void main(String[] args){
        Solution s = new Solution();
        List<List<Integer>> r = s.generate(5);
        System.out.println(r);
    }

    static
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            ArrayList<List<Integer>> r = new ArrayList<>();

            for(int i=0; i<numRows; ++i){
                ArrayList<Integer> st = new ArrayList<>();
                if(i == 0){
                    st.add(1);
                }
                else if(i ==1){
                    st.add(1); st.add(1);
                }
                else {
                    st.add(1);

                    for (int j = 0; j < r.get(i - 1).size() - 1; ++j) {
                        st.add(r.get(i - 1).get(j) + r.get(i - 1).get(j + 1));
                    }

                    st.add(1);
                }
                r.add(st);
            }

            return r;
        }
    }
}
