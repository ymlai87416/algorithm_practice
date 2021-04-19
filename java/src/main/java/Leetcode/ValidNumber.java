package Leetcode;

/**
number: 65
problem: https://leetcode.com/problems/valid-number/
level: hard
solution: testing bounding case.

#math #string

 **/

public class ValidNumber {
    public static void main(String[] args) {
        String s = "";
        Solution sol = new Solution();
        //System.out.println(sol.isNumber("0"));
        //System.out.println(sol.isNumber(" 0.1 "));
        //System.out.println(sol.isNumber("abc"));
        //System.out.println(sol.isNumber("1 a"));
        //System.out.println(sol.isNumber("2e10"));
        //System.out.println(sol.isNumber("-90e3"));
        //System.out.println(sol.isNumber("1e"));
        //System.out.println(sol.isNumber("e3"));
        //System.out.println(sol.isNumber("6e-1"));
        //System.out.println(sol.isNumber("99e2.5"));
        //System.out.println(sol.isNumber("53.5e93"));
        //System.out.println(sol.isNumber("--6"));
        //System.out.println(sol.isNumber("-+3"));
        //System.out.println(sol.isNumber("95a54e53"));
        //System.out.println(sol.isNumber(" "));
        System.out.println(sol.isNumber("0.."));
    }

    static
    class Solution {
        public boolean isNumber(String s) {
            //gramma rules
            /*
                number = sign [0-9].[0-9] e [0-9]
             */

            s = s.trim();

            return processSignNumber(s) != null;
        }

        private Double processSignNumber(String s){
            if(s.length() == 0) return null;
            if(s.charAt(0) == '+')
                return processSciNumber(s.substring(1));
            else if(s.charAt(0) == '-') {
                Double r = processSciNumber(s.substring(1));
                return r != null ? -r : null;
            }
            else if('0' <= s.charAt(0) && s.charAt(0) <= '9' || s.charAt(0) == '.')
                return processSciNumber(s);
            else
                return null;
        }

        private Double processSciNumber(String s){
            if(s.contains("e")){
                int ep = s.indexOf("e");
                Double a = processDecimal(s.substring(0, ep));
                Double b = processSignedInteger(s.substring(ep+1));

                if(a==null || b==null)
                    return null;

                return a * Math.pow(10, b);
            }
            else
                return processDecimal(s);
        }

        private Double processDecimal(String s){
            if(s.contains(".")){
                int dp = s.indexOf(".");
                Double a, b;
                boolean c;
                c = false;
                if(s.substring(0,dp).length() > 0) {
                    c = true;
                    a = processInteger(s.substring(0, dp));
                }
                else
                    a = 0.;

                if(s.substring(dp+1).length() > 0) {
                    c = true;
                    b = processInteger(s.substring(dp + 1));
                }
                else
                    b = 0.;

                if(!c)
                    return null;

                if(a == null || b == null)
                    return null;

                while(b > 1)
                    b = b/10.0;
                return a+b;
            }
            else
                return processInteger(s);
        }

        private Double processSignedInteger(String s){
            if(s.length() == 0) return null;
            if(s.charAt(0) == '+')
                return processInteger(s.substring(1));
            else if(s.charAt(0) == '-') {
                Double r = processInteger(s.substring(1));
                return r != null ? -r : null;
            }
            else if('0' <= s.charAt(0) && s.charAt(0) <= '9')
                return processInteger(s);
            else
                return null;
        }

        private Double processInteger(String s){
            if(s.length() == 0) return null;

            Double a = 0.0;
            for(int i=0; i<s.length(); ++i) {
                if('0' <= s.charAt(i) && s.charAt(i) <= '9')
                    a = a * 10 + (s.charAt(i) - '0');
                else
                    return null;
            }

            return a;
        }
    }
}
