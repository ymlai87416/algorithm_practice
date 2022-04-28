package Leetcode.Contest276;

/**
 problem: https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/
 level: medium
 solution: pad it to the length of k mulitples and done.

 #string
 **/


public class DivideAStringIntoGroupOfSizeK {

    public String[] divideString(String s, int k, char fill) {
        StringBuilder sb = new StringBuilder(s);
        while(sb.length() %k != 0){
            sb.append(fill);
        }
        String[] r =  new String[sb.length()/k];
        for (int i = 0; i < sb.length(); i+=k) {
            String ss = sb.substring(i, i+k);
            r[i/k] = ss;
        }

        return r;
    }

    public static void main(String[] args){
        DivideAStringIntoGroupOfSizeK s = new DivideAStringIntoGroupOfSizeK();

        s.divideString("abcdefghi",  3, 'x');
                s.divideString("abcdefghij", 3,  'x');
    }
}
