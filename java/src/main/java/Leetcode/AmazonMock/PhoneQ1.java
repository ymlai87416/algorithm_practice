package Leetcode.AmazonMock;

import java.util.*;

public class PhoneQ1 {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<Character>();


        for (int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);
            char top;
            switch (c){
                case '(':
                case '[':
                case '{':
                    st.push(c);
                    break;
                case ')':
                    if(st.isEmpty()) return false;
                    top  =st.pop();
                    if(top != '(') return false;
                    break;
                case ']':
                    if(st.isEmpty()) return false;
                    top  =st.pop();
                    if(top != '[') return false;
                    break;
                case '}':
                    if(st.isEmpty()) return false;
                    top  =st.pop();
                    if(top != '{') return false;
                    break;
            }
        }

        return st.isEmpty();
    }


    public static void main(String[] args){
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(";
        PhoneQ1 s = new PhoneQ1();
        System.out.println(s.isValid(s1));
        System.out.println(s.isValid(s2));
        System.out.println(s.isValid(s3));
    }
}
