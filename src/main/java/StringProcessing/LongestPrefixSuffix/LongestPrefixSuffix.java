package StringProcessing.LongestPrefixSuffix;

public class LongestPrefixSuffix {

    public int longestPrefixSuffix(String str){
        int n = str.length();

        if(n < 2) {
            return 0;
        }

        int len = 0;
        int i = n/2;

        while(i < n) {
            if(str.charAt(i) == str.charAt(len)) {
                ++len;
                ++i;
            } else {
                if(len == 0) { // no prefix
                    ++i;
                } else { // search for shorter prefixes
                    --len;
                }
            }
        }

        return len;
    }

    public static void main(String[] args){
        //String s = "blablabla";
        String s = "abcabb";
        LongestPrefixSuffix obj = new LongestPrefixSuffix();
        System.out.println(obj.longestPrefixSuffix(s));
    }

}
