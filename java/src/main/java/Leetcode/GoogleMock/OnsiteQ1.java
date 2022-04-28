package Leetcode.GoogleMock;

import java.util.HashMap;
import java.util.*;

public class OnsiteQ1 {

    public boolean isSubsequence(String s, String t) {
        HashMap<Character, List<Integer>> hm = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if(!hm.containsKey(c)) hm.put(c, new ArrayList<>());
            hm.get(c).add(i);
        }

        boolean result = true;
        int prev_pos = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!hm.containsKey(c)) return false;
            List<Integer> clist = hm.get(c);

            int nextpos = Collections.binarySearch(clist, prev_pos+1);
            if(nextpos == -clist.size()-1) return false;
            else if(nextpos >= 0) prev_pos = clist.get(nextpos);
            else if(nextpos < 0) prev_pos = clist.get(-nextpos-1);

            //System.out.println("found " + c + " at " + prev_pos);
        }

        return result;
    }

    public static void main(String[] args){
        OnsiteQ1 s = new OnsiteQ1();
        System.out.println(s.isSubsequence("abc", "ahbgdc"));
        System.out.println(s.isSubsequence("axc", "ahbgdc"));
    }
}
