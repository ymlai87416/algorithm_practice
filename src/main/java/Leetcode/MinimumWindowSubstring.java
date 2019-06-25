package Leetcode;

public class MinimumWindowSubstring {
    public static void main(String[] args){
        String S = "aa";
        String T = "aa";

        Solution s = new Solution();
        System.out.println(s.minWindow(S, T));
    }

    static
    class Solution {
        public String minWindow(String s, String t) {
            int[] freq = new int[256];
            int[] minFreq = new int[256];

            /*
            for(int i=0; i<s.length(); ++i){
                char c = s.charAt(i);
                if(t.indexOf(c) != -1)
                    freq[c]++;
            }
            */

            for(int i=0; i<t.length(); ++i){
                char c = t.charAt(i);
                minFreq[c]++;
            }

            //check if init condition match
            //if(!fulfill(freq, minFreq))
            //    return "";

            int ptr1, ptr2;
            ptr1 = 0; ptr2 = 0;
            char c1, c2;
            int minp1, minp2;
            minp1 = -1; minp2 = -1;

            freq[s.charAt(0)]++;
            if(fulfill(freq, minFreq))
                return s.substring(0, 1);

            for(ptr1=1; ptr1<s.length(); ++ptr1){
                freq[s.charAt(ptr1)]++;

                if(fulfill(freq, minFreq)){
                    if((ptr1-ptr2 < minp1 -minp2) || minp1==-1 && minp2==-1) {
                        minp1 = ptr1;
                        minp2 = ptr2;
                    }
                }

                do{
                    if(s.charAt(ptr2) > 0)
                        freq[s.charAt(ptr2)]--;
                    if(fulfill(freq, minFreq)){
                        ptr2++;
                        if((ptr1-ptr2 < minp1 -minp2) || minp1==-1 && minp2==-1) {
                            minp1 = ptr1;
                            minp2 = ptr2;
                        }
                    }
                    else {
                        freq[s.charAt(ptr2)]++;
                        break;
                    }
                }while(ptr2 <= ptr1);
            }

            if(minp1 == -1 || minp2 == -1)
                return "";
            else
                return s.substring(minp2, minp1+1);
        }

        private boolean fulfill(int[] freq, int[] minFreq){
            for(int i=0; i<256; ++i) {
                if (freq[i] < minFreq[i])
                    return false;
            }
            return true;
        }
    }
}
