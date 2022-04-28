package Leetcode.GoogleMock;

import java.util.*;

public class OnsiteQ3 {

    public boolean canConvert(String str1, String str2) {
        HashMap<Character, Character> rule = new HashMap<>();

        boolean[] ok = new boolean[26];
        boolean str1Full26= true;
        for (int i = 0; i < 26; i++) {
            ok[i] = false;
        }
        for (int i = 0; i < str1.length(); i++) {
            char s = str1.charAt(i);
            ok[s-'a'] = true;
        }
        for (int i = 0; i < 26; i++) {
            if(!ok[i]){
                str1Full26 = false;
                break;
            }
        }

        boolean str2Full26= true;
        for (int i = 0; i < 26; i++) {
            ok[i] = false;
        }
        for (int i = 0; i < str2.length(); i++) {
            char s = str2.charAt(i);
            ok[s-'a'] = true;
        }
        for (int i = 0; i < 26; i++) {
            if(!ok[i]){
                str2Full26 = false;
                break;
            }
        }

        //if a full mapping, return false if difference
        if(str1Full26 && str2Full26 )
            return str1.compareTo(str2) == 0;


        for (int i = 0; i < str1.length(); i++) {
            char s = str1.charAt(i);
            char t = str2.charAt(i);
            if(!rule.containsKey(s)) rule.put(s, t);

            //check
            if(rule.get(s) != t) return false;
        }

        return true;
    }

    public static void main(String[] args){
        OnsiteQ3 s = new OnsiteQ3();
        System.out.println(s.canConvert("aabcc", "ccdee"));
        System.out.println(s.canConvert("leetcode", "codeleet"));
        System.out.println(s.canConvert("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"));
        System.out.println(s.canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"));
    }
}

