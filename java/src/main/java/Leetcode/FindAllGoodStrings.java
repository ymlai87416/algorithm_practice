package Leetcode;

import java.util.*;

public class FindAllGoodStrings {
    int n;
    String s1;
    String s2;
    String evil;
    int modulo = 1_000_000_007;
    HashMap<String, Integer> dp;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.n = n;
        this.s1 = s1;
        this.s2 = s2;
        this.evil = evil;

        dp = new HashMap<>();

        return helper("");
    }


    public int helper(String prefix){

        if(dp.containsKey(prefix))
            return dp.get(prefix);

        int result = 0;

        if(prefix.contains(evil))
            result = 0;
        else if(prefix.length() == n)
            result = 1;
        else if(s1.startsWith(prefix) || s2.startsWith(prefix)){
            long t = 0;
            for(int i=0; i<26; ++i){
                String nS = prefix+(char)('a'+i);
                if(smallerPrefix(s1, nS)) continue;
                if(largerPrefix(s2, nS)) break;
                t = (t+ helper(nS)) % modulo;
            }
            result = (int) t;
        }
        else{
            int eLen = evil.length();
            long[] dp1 = new long[eLen];
            long[] dp2 = new long[eLen];

            int pLen = prefix.length();

            String s = "";
            Arrays.fill(dp1, 0);
            for(int j=eLen-1; j>=0; --j){
                if(prefix.endsWith(evil.substring(0, j))){
                    dp1[j] = 1;
                    break;
                }
            }

            for(int i=pLen+1; i<=n; ++i){
                dp2[0] = 0;

                for(int j=0; j<eLen; ++j)
                    dp2[0] = (dp2[0] + (dp1[j] * 25 % modulo)) % modulo;

                for(int j=1; j<eLen; ++j)
                    dp2[j] = dp1[j-1];

                long[] t = dp1;
                dp1 = dp2;
                dp2 = t;
            }

            long rr = 0;
            for(int i=0; i<eLen; ++i)
                rr = (rr + dp1[i]) % modulo;

            result = (int) rr;
        }

        System.out.println("D "+ prefix + ": "+ result + " E:" + debug(prefix));
        dp.put(prefix, result);


        return result;
    }

    private boolean smallerPrefix(String bottom, String prefix){
        for(int i=0; i<prefix.length(); ++i){
            if(prefix.charAt(i) > bottom.charAt(i)) return false; //any char > lower bound
            if(prefix.charAt(i) < bottom.charAt(i)) return true;
        }
        return false;
    }

    private boolean largerPrefix(String top, String prefix){
        for(int i=0; i<prefix.length(); ++i){
            if(prefix.charAt(i) < top.charAt(i)) return false;
            if(prefix.charAt(i) > top.charAt(i)) return true;
        }
        return false;
    }

    private int debug(String prefix){
        char[] t = new char[n];
        int cnt = 0;
        Arrays.fill(t, 'a');
        for(int i=0; i<prefix.length(); ++i)
            t[i] = prefix.charAt(i);

        while(true){
            String a = new String(t);
            if(!a.startsWith(prefix)) break;
            if(!a.contains(evil))
                ++cnt;

            int carry=1;
            int pos = n-1;
            while(pos >= 0 && carry > 0){
                t[pos]++;
                if(t[pos] >'z'){
                    t[pos] = 'a';
                    carry = 1;
                }
                else
                    carry=0;

                pos--;
            }
            if(carry > 0) break;
        }

        return cnt;
    }

    public static void main(String[] args){
        FindAllGoodStrings a = new  FindAllGoodStrings();
        a.findGoodStrings(4, "aaaa", "zzzz", "ab");
    }
}
