package Leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LongestDuplicateSubstring {

    char[] T = new char[30001];
    Integer[] SA = new Integer[30001];
    int len = 0;

    public String longestDupSubstring(String s) {
        s = s + "$";
        len = s.length();
        for (int i = 0; i < len; ++i) {
            T[i] = s.charAt(i);
        }

        //sort SA
        for (int i = 0; i < s.length(); ++i) {
            SA[i] = i; //i here mean starting index of suffix
        }

        //This consume O(n^2 * logn)
        Arrays.sort(SA, 0, s.length(), new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                int ptr1 = o1;
                int ptr2 = o2;
                while (true) {
                    if (ptr1 == len) return -1;
                    else if (ptr2 == len) return 1;
                    else if (T[ptr1] != T[ptr2]) return T[ptr1] - T[ptr2];
                    ptr1++;
                    ptr2++;
                }
            }
        });

        /*
        for (int i = 0; i < len; ++i) {
            System.out.println(s.substring(SA[i]));
        }*/


        //now calculate the longest common suffix
        int[] LCP = new int[len];
        LCP[0] = 0;
        for (int i = 1; i < len; ++i){
            int L = 0;
            while(T[SA[i] + L] == T[SA[i-1] + L]) ++L;
            LCP[i] = L;
        }

        //now then we can loop through the array and check for the highest LCP
        int highestIdx = 0;
        for(int i=1; i<len; ++i){
            if(LCP[i] > LCP[highestIdx]){
                highestIdx = i;
            }
        }

        String result = s.substring(SA[highestIdx], SA[highestIdx]+ LCP[highestIdx]);
        //System.out.println("Test "+ result );
        return result;
    }

    public static void main(String[] args){
        String degrade = "9".repeat(10);
        System.out.println(degrade);
        LongestDuplicateSubstring c  = new LongestDuplicateSubstring();
        c.longestDupSubstring("b anana");
    }
}
