package Leetcode;

/**
problem: https://leetcode.com/problems/find-all-anagrams-in-a-string/
level: medium
solution:

#hash_table #sliding_window

 */

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramInAString {
    public static void main(String[] args){
        String w1 = "cbaebabacd";
        String w2 = "abc";

        Solution s = new Solution();
        System.out.println(s.findAnagrams(w1, w2));
    }

    static
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            //return firstImpl(s, p);
            return slidingWindow(s,p);
        }

        private List<Integer> firstImpl(String s, String p){
            ArrayList<Integer> result = new ArrayList<Integer>();
            int[][] freq = new int[s.length()+1][26];
            for(int i=0; i<26; ++i){
                freq[0][i] = 0;
            }
            for(int i=0; i<s.length(); ++i){
                for(int j=0; j<26; ++j){
                    freq[i+1][j] = freq[i][j];
                }
                freq[i+1][s.charAt(i)-'a']+=1;
            }

            for(int i=0; i<s.length()+1; ++i){
                for(int j=0; j<26; ++j){
                    System.out.print(freq[i][j] + " ") ;
                }
                System.out.println();
            }

            int[] pfreq = new int[26];
            for(int i=0; i<26; ++i){
                pfreq[i] = 0;
            }
            for(int i=0; i<p.length();++i)
                pfreq[p.charAt(i)-'a'] += 1;

            int[] qfreq = new int[26];

            //we don't need to look all the 26 characters again
            for(int i=p.length(); i<=s.length(); ++i){
                for(int j=0; j<26; ++j)
                    qfreq[j] = freq[i][j] - freq[i-p.length()][j];

                boolean allM = true;
                for(int j=0; j<26; ++j) {
                    if(qfreq[j] != pfreq[j]){
                        allM = false;
                        break;
                    }
                }

                if(allM)
                    result.add(i-p.length());
            }
            return result;
        }

        private List<Integer> slidingWindow(String s, String p){
            ArrayList<Integer> result = new ArrayList<Integer>();

            if(p.length() > s.length()) return result;

            int[] freq = new int[26];
            int[] maxf = new int[26];
            for (int i = 0; i < p.length(); i++) {
                maxf[p.charAt(i)-'a'] ++;
            }
            
            //now we have run
            int len = 0;            //len only count number of valid char
            for (int i = 0; i < p.length(); i++) {
                int cIdx = s.charAt(i)-'a';
                if(maxf[cIdx] > 0){
                    freq[cIdx]++;
                    if(freq[cIdx] <= maxf[cIdx]) ++len;
                }
            }
            if(len== p.length())
                result.add(0);

            int sIdx = 0;
            for (int i = p.length(); i < s.length(); i++) {
                //now what, we ditch the first and add second
                sIdx +=1;
                int ditchPos = i-p.length();
                int addPos = i;

                //if the ditch index is include in p, --len
                int dcharIdx = s.charAt(ditchPos)-'a';
                if(maxf[dcharIdx] > 0 ){
                    freq[dcharIdx]--;
                    if(freq[dcharIdx] < maxf[dcharIdx]) --len;  //only if deficit reduce length
                }


                int acharIdx = s.charAt(addPos)-'a';
                if(maxf[acharIdx] > 0 ){
                    freq[acharIdx]++;
                    if(freq[acharIdx] <= maxf[acharIdx]) ++len; //only supp back the character increase length
                }

                //System.out.println("debug: " + sIdx + " " + len);
                if(len == p.length())
                    result.add(sIdx);
            }

            return result;
        }
    }
}

