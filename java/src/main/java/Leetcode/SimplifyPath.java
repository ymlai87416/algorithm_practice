package Leetcode;

import java.util.Stack;

/**
number: 380
problem: https://leetcode.com/problems/simplify-path/
level: medium
solution: just code the procedure

 #string #stack

 **/

public class SimplifyPath {
    public static void main(String[] args){
        String w1 = "/../";

        Solution s = new Solution();
        System.out.println(s.simplifyPath(w1));
    }


    static
    class Solution {
        public String simplifyPath(String path) {
            String[] pathFragments = path.split("/");
            Stack<String> a = new Stack<String>();
            for(int i=0; i<pathFragments.length; ++i){
                if(pathFragments[i].compareTo(".") == 0){}
                else if(pathFragments[i].compareTo("..") == 0){
                    if(a.size() > 0)
                        a.pop();
                }
                else if(pathFragments[i].isEmpty()){}
                else{
                    a.push(pathFragments[i]);
                }
            }

            StringBuilder result = new StringBuilder();
            for(String s: a){
                result.append(s); result.append("/");
            }
            result.setLength(result.length() > 0 ?result.length()-1: 0);
            return "/" + result.toString();
        }
    }
}
