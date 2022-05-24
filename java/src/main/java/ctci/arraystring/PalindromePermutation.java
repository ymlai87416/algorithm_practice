package ctci.arraystring;

import java.util.Arrays;

public class PalindromePermutation {
    public boolean palindromePermutation(String input){

        //or just flip the bit, the initial is 0 so it is even.
        int[] freq = new int[128];
        Arrays.fill(freq, 0);

        for(int i=0; i<input.length(); ++i){
            int ci = getCharacterIndex(input.charAt(i));
            if(ci == -1) continue;
            freq[ci]++;
        }

        int oddCount = 0;
        for(int i=0; i<freq.length; ++i){
            if(freq[i]  % 2 != 0){
                oddCount++;
                if(oddCount > 1) return false;
            }
        }

        return true;

    }

    private int getCharacterIndex(char c){
        //case insensitive
        //other character like space are ignore
        if('A' <= c && c <= 'Z')
            return c-'A';
        else if('a' <= c && c <='z')
            return c-'a';
        else
            return -1;
    }


    public static void main(String[] args) {
        PalindromePermutation sol = new PalindromePermutation();
        System.out.println(sol.palindromePermutation("Tact Coa"));
        System.out.println(sol.palindromePermutation("Tea bag"));
    }
}
