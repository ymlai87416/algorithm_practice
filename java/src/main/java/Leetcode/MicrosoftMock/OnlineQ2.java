package Leetcode.MicrosoftMock;

import java.math.BigInteger;
import java.util.Stack;

public class OnlineQ2 {

    String num = null;

    public String removeKdigits(String num, int k) {
        if(num.length() == k) return "0";
        Stack<Character> st = new Stack<Character>();
        st.push(num.charAt(0));
        int rk = k;
        for (int i = 1; i < num.length(); i++) {
            char u = num.charAt(i);
            while(!st.isEmpty() && rk > 0){
                char x = st.peek();
                if(x > u) {
                    st.pop();
                    --rk;
                }
                else break;
            }
            st.push(u);
        }

        StringBuilder sb = new StringBuilder();
        boolean trailZ = true;
        for(int i=0; i<Math.min(st.size(), num.length()-k); ++i){
            if(st.get(i) == '0' && trailZ) continue;
            else if(st.get(i)!='0') trailZ  =false;
            sb.append(st.get(i));
        }
        if(sb.length() == 0) return "0";
        else return sb.toString();

    }

    public String helper( int k, int idx) {
        String b1 = num.charAt(idx) + helper(k-1, idx+1);
        String b2 = helper(k, idx+1);
        if(b1.compareTo(b2) < 0) return b1;
        else return b2;
    }

    public String removeKdigits2(String num, int k) {
        String smallest = null;
        for(int i=0; i<k; ++i){
            smallest = num.substring(1);
            for (int j = 1; j < num.length(); j++) {
                String newS = num.substring(0, j) + num.substring(j+1);
                if(smallest.compareTo(newS) > 0)
                    smallest = newS;
            }
            num = smallest;
        }
        //remove all the trailing
        int zeroptr = -1;
        for (int i = 0; i < num.length(); i++) {
            if(num.charAt(i)== '0')
                zeroptr = i;
            else
                break;
        }
        if(zeroptr == num.length()-1) return "0";
        else if(zeroptr == -1) return num;
        else return num.substring(zeroptr+1);

    }

    public static void main(String[] args){
        OnlineQ2 s = new OnlineQ2();
        System.out.println(s.removeKdigits("9", 1));
    }
}
