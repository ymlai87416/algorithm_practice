package Leetcode.UberMock;

import java.util.Arrays;

public class PhoneQ2 {

    public boolean canConstruct(String s, int k) {
        //anagram: ferq: [optional: 1 odd], other even
        if(s.length() < k) return false;

        int[] charF = new int[26];
        Arrays.fill(charF, 0);
        for(int i=0; i<s.length(); ++i){
            charF[s.charAt(i)-'a']++;
        }

        //so check if any odd number, just make it as a annagram with 1 word.
        int singleCharCnt= 0;
        for (int i = 0; i < 26; i++) {
            if(charF[i] %2==1) singleCharCnt++;
        }

        if(singleCharCnt > k)
            return false;
        return true;
    }

    public static void main(String[] args){
        PhoneQ2 q = new PhoneQ2();
        System.out.println(q.canConstruct("leetcode", 3));
    }
}
