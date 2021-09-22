package Leetcode;

/**
problem: https://leetcode.com/problems/count-and-say/
level: medium
solution:

#string

 */

public class CountAndSay {
    public static void main(String[] args){
        int a = 5;
        Solution s = new Solution();
        System.out.println(s.countAndSay(a));
    }

    static
    class Solution {
        public String countAndSay(int n) {
            String r = "1";

            for(int i=1; i<n; ++i){
                r = getSay(r);
            }

            return r;
        }

        private String getSay(String s){
            s = s + " ";
            StringBuilder r = new StringBuilder();
            int cur = 1;
            char p = s.charAt(0);
            int cnt = 1;

            while(cur < s.length()) {
                while (cur < s.length() && p == s.charAt(cur)) {
                    cnt++;
                    ++cur;
                }

                r.append(String.valueOf(cnt)).append(p);

                if(cur >= s.length()-1) break;
                p = s.charAt(cur);
                cnt = 1;
                ++cur;
            }

            return r.toString();
        }
    }
}
