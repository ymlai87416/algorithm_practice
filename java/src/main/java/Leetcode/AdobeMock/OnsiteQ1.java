package Leetcode.AdobeMock;

public class OnsiteQ1 {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 1) return strs[0];

        StringBuilder sb = new StringBuilder();
        int prefixLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            prefixLen = Math.min(prefixLen, strs[i].length());
        }

        for (int i = 0; i < prefixLen; i++) {
            char c = strs[0].charAt(i);
            boolean allOK = true;
            for (int j = 1; j < strs.length; j++) {
                if(strs[j].charAt(i) != c){
                    allOK = false;
                    break;
                }
            }

            if(!allOK)
                break;
            else
                sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args){

    }
}
