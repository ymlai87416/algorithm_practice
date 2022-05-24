package ctci.arraystring;

import java.util.Arrays;

public class HaveAllUniqueChar {

    int setN = 128;
    boolean[] exists = new boolean[setN];
    public boolean haveAllUniqueCharacters(String s){

        if(s.length() > setN) return false;
        Arrays.fill(exists, false);

        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            int ci = (int) c;
            if(exists[ci]) return false;
            else exists[ci] = true;
        }

        return true;

    }

    //for sorting
    public boolean haveAllUniqueCharacters2(String a){
        if(a.length() > setN) return false;
        Arrays.fill(exists, false);
        char[] carr = a.toCharArray();
        Arrays.sort(carr);

        for(int i=1; i<carr.length; ++i){
            if(carr[i] == carr[i-1]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        HaveAllUniqueChar test = new HaveAllUniqueChar();
        System.out.println( test.haveAllUniqueCharacters2("aabc"));
        System.out.println( test.haveAllUniqueCharacters2("abc"));
    }
}
