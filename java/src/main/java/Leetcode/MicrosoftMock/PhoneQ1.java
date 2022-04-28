package Leetcode.MicrosoftMock;

public class PhoneQ1 {

    public String reverseOnlyLetters(String s) {
        int p1=0; int p2 = s.length()-1;
        char[] cs = s.toCharArray();

        while(true) {
            while (p1 < s.length() && !isChar(cs[p1])) ++p1;
            while (p2 >= 0 && !isChar(cs[p2])) --p2;

            if(p1 >= p2) break;

            swap(cs, p1, p2);
            ++p1; --p2;
        }

        return new String(cs);
    }

    private boolean isChar(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private void swap(char[] c, int p1, int p2){
        char t = c[p1];
        c[p1] = c[p2];
        c[p2] = t;
    }


    public static void main(String[] args){
        PhoneQ1 s = new PhoneQ1();
        System.out.println(s.reverseOnlyLetters("1"));
        System.out.println(s.reverseOnlyLetters("a"));
        System.out.println(s.reverseOnlyLetters("ab-cd"));
        System.out.println(s.reverseOnlyLetters("a-bC-dEf-ghIj"));
    }
}
