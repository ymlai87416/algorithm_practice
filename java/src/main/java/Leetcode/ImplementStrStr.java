package Leetcode;

import java.util.ArrayList;

/*
problem: https://leetcode.com/problems/implement-strstr/
level: easy
solution: kmp search O(n) 4ms, simple O(m(n-m+1)) 1ms
kmp intro: https://www.youtube.com/watch?v=V5-7GzOfADQ

#twoPointer #string
 */

public class ImplementStrStr {
    public static void main(String[] args) {
        String a = "hello", b = "ll";
        Solution s = new Solution();
        System.out.println(s.strStr(a, b));
    }

    static
    class Solution {
        public int strStr(String haystack, String needle) {
            //return strStrKMP(haystack, needle);
            return strStrSimple(haystack, needle);
        }

        void computeLPSArray(String pat, int M, int lps[]) {
            // length of the previous longest prefix suffix
            int len = 0;
            int i = 1;
            lps[0] = 0; // lps[0] is always 0

            // the loop calculates lps[i] for i = 1 to M-1
            while (i < M) {
                if (pat.charAt(i) == pat.charAt(len)) {
                    len++;
                    lps[i] = len;
                    i++;
                } else // (pat[i] != pat[len])
                {
                    // This is tricky. Consider the example.
                    // AAACAAAA and i = 7. The idea is similar
                    // to search step.
                    if (len != 0) {
                        len = lps[len - 1];

                        // Also, note that we do not increment
                        // i here
                    } else // if (len == 0)
                    {
                        lps[i] = len;
                        i++;
                    }
                }
            }

        }

        int KMPSearch(String pat, String txt)
        {
            int M = pat.length();
            int N = txt.length();

            // create lps[] that will hold the longest
            // prefix suffix values for pattern
            int lps[] = new int[M];
            int j = 0; // index for pat[]

            // Preprocess the pattern (calculate lps[]
            // array)
            computeLPSArray(pat, M, lps);

            int i = 0; // index for txt[]
            while (i < N) {
                if (pat.charAt(j) == txt.charAt(i)) {
                    j++;
                    i++;
                }
                if (j == M) {
                    /*
                    System.out.println("Found pattern "
                            + "at index " + (i - j));
                            */
                    return i-j;

                    //j = lps[j - 1];
                }

                // mismatch after j matches
                else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                    // Do not match lps[0..lps[j-1]] characters,
                    // they will match anyway
                    if (j != 0)
                        j = lps[j - 1];
                    else
                        i = i + 1;
                }
            }

            return -1;
        }

        //Some case, it is just like O(mn) solution...
        //4ms beat 33.08%
        public int strStrKMP(String haystack, String needle){
            if(needle.length() == 0) return 0;
            return KMPSearch(needle, haystack);
        }

        //this is 1ms > 86.47%. fuck!!!
        public int strStrSimple(String haystack, String needle){
            if(needle.length() == 0) return 0;

            char[] _haystack = haystack.toCharArray();
            char[] _needle = needle.toCharArray();

            for(int i=0; i<_haystack.length-_needle.length+1; ++i){
                boolean found = true;
                for( int j=0; j<_needle.length; ++j){
                    if(_haystack[i+j] != _needle[j]) {
                        found =false;
                        break;
                    }
                }
                if(found)return i;
            }

            return -1;
        }

        public int strStrTLE(String haystack, String needle){
            if(needle.length() == 0) return 0;

            ArrayList<Integer> states = new ArrayList<>();
            ArrayList<Integer> next;

            for(int i=0; i<haystack.length(); ++i){
                char c = haystack.charAt(i);
                next = new ArrayList<Integer>();

                for(Integer s: states){
                    if(needle.charAt(s) == c){
                        int ts = s+1;
                        if(ts < needle.length())
                            next.add(s+1);
                        else
                            return i-ts+1;
                    }
                }

                if(c == needle.charAt(0)) {
                    if(needle.length() == 1)
                        return i;
                    else
                        next.add(1);
                }

                states = next;
            }

            return -1;
        }
    }
}
