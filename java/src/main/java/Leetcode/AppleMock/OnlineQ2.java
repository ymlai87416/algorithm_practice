package Leetcode.AppleMock;

import java.util.ArrayList;
import java.util.List;

public class OnlineQ2 {

    List<String> result = null;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        char[] ca = new char[n*2];
        helper(n, 0, ca, 0);
        return result;
    }

    private void helper(int open, int close, char[] c, int ptr){
        if(ptr == c.length){
            result.add(new String(c));
        }
        if(open > 0){
            c[ptr] = '(';
            helper(open-1, close+1, c, ptr+1);
        }
        if(close > 0){
            c[ptr] = ')';
            helper(open, close-1, c, ptr+1);
        }
    }

    public static void main(String[] args){
        OnlineQ2 s = new OnlineQ2();
        List<String> aa = s.generateParenthesis(1);

        for (int i = 0; i < aa.size(); i++) {
            System.out.println(aa.get(i));
        }
    }
}
