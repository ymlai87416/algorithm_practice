package Leetcode;

/*
problem: https://leetcode.com/problems/find-all-anagrams-in-a-string/
level: medium
solution:

#hashTable

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
    }
}

