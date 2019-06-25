package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args){
        String w = "aab";

        Solution s = new Solution();
        System.out.println(s.partition(w));
    }


    static
    class Solution {
        HashMap<String, List<List<String>>> dp;
        public List<List<String>> partition(String s) {
            dp = new HashMap<String, List<List<String>>>();
            return partitionHelper(s);
        }

        private List<List<String>> partitionHelper(String s){
            if(s.length() == 0) {
                ArrayList<List<String>> r = new ArrayList<List<String>>();
                r.add(new ArrayList<String>());
                return r;
            }

            if(dp.containsKey(s))
                return dp.get(s);
            else{
                ArrayList<List<String>> result = new ArrayList<List<String>>();
                for(int i=1; i<=s.length(); ++i){
                    String subStr = s.substring(0, i);
                    if(isPalindrome(subStr)) {
                        List<List<String>> tr = partitionHelper(s.substring(i));

                        //make a clone of the list
                        for(List<String> trl: tr){
                            ArrayList<String> temp = new ArrayList<>();
                            temp.add(subStr);
                            temp.addAll(trl);
                            result.add(temp);
                        }
                    }
                }

                dp.put(s, result);

                return result;
            }
        }

        private boolean isPalindrome(String s){
            for(int i=0,j=s.length()-1;i<j; ++i,--j){
                if(s.charAt(i) != s.charAt(j)) return false;
            }
            return true;
        }
    }
}
