package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class RegularExpressionMatching {
    public static void main(String[] args){
        String s = "";
        String p = ".*";
        RegularExpressionMatching.Solution a = new RegularExpressionMatching.Solution();
        System.out.println(a.isMatch(s, p));
    }


    static
    class Solution {

        String s;
        List<String> tok;

        int[][] dp;

        public boolean isMatch(String s, String p){

            List<String> tok = new ArrayList<String>();

            if(p.length() > 0) {
                String prevChar = p.substring(0, 1), curChar = null;
                for (int i = 1; i < p.length(); ++i) {
                    curChar = p.substring(i, i + 1);
                    if (curChar.compareTo("*") == 0) {
                        tok.add(prevChar + curChar);
                        prevChar = null;
                    } else {
                        if (prevChar != null) tok.add(prevChar);
                        prevChar = curChar;
                    }
                }

                if (prevChar != null)
                    tok.add(prevChar);
            }

            s = s + "\0";
            tok.add("\0");

            this.s = s;
            this.tok = tok;

            dp = new int[s.length()][tok.size()];
            for(int i=0; i<s.length(); ++i)
                for(int j=0; j<tok.size(); ++j)
                    dp[i][j] = -1;

            return match(0, 0);
        }

        private boolean charMatch(char input, char pattern){
            if(pattern == input) return true;
            if(pattern == '.') return input != '\0';
            return false;
        }

        private boolean match(int spos, int tpos){

            System.out.format("Debug %s %s %d %d\n", s, tok, spos, tpos);

            if(spos == s.length() && tpos == tok.size()) return true;
            if(spos == s.length() && tpos != tok.size()) return false;
            if(spos != s.length() && tpos == tok.size()) return false;

            if(dp[spos][tpos] != -1) return dp[spos][tpos] == 1;

            String curTok = tok.get(tpos);
            String curS = s.substring(spos, spos+1);
            if(curTok.contains("*")){
                boolean match1, match2, match3 ;
                match1 = false; match2 = false;

                if(charMatch(curS.charAt(0), curTok.charAt(0))) {
                    //continue to match
                    match1 = match(spos+1, tpos);
                    //match to this char
                    match2 = match(spos+1, tpos+1);
                    //skip this match
                }

                match3 = match(spos, tpos+1);

                boolean result = match1 || match2 || match3;
                if(result) dp[spos][tpos] =1; else dp[spos][tpos] = 0;
                return result;
            }
            else{
                boolean result = charMatch(curS.charAt(0), curTok.charAt(0)) && match(spos+1, tpos+1);
                if(result) dp[spos][tpos] =1; else dp[spos][tpos] = 0;
                return result;
            }
        }
    }
}
