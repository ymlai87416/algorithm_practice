package Leetcode;

import java.util.*;

/*
url: https://leetcode.com/problems/group-anagrams/
level: medium
solution: group anagrams by looking at the frequency table of words
 */

public class GroupAnagrams {
    public static void main(String[] args){
        String[] input = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        Solution s = new Solution();
        System.out.println(s.groupAnagrams(input));
    }


    static
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<CharFreq, List<String>> bb = new HashMap<>();

            for(int i=0; i<strs.length; ++i){
                CharFreq freq = new CharFreq(strs[i]);

                List<String> bs = bb.get(freq);
                if(bs == null) {
                    List<String> b = new ArrayList<>();
                    b.add(strs[i]);
                    bb.put(freq, b);
                }
                else{
                    bs.add(strs[i]);
                }
            }

            ArrayList<List<String>> result = new ArrayList<List<String>>();
            for(CharFreq a : bb.keySet()){
                List<String> bs = bb.get(a);
                result.add(new ArrayList<String>(bs));
            }

            return result;
        }
    }

    static
    class CharFreq{
        int[] freq;
        public CharFreq(String a){
            freq = new int[26];
            for(int j=0; j<26; ++j) freq[j] = 0;
            for(int j=0; j<a.length(); ++j){
                freq[a.charAt(j)-'a']++;
            }
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 7;
            for(int i=0; i<26; ++i)
                result  = prime * result +freq[i];
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            CharFreq other = (CharFreq) obj;
            for(int i=0; i<26; ++i)
                if(other.freq[i] != freq[i])
                    return false;
            return true;
        }
    }
}
