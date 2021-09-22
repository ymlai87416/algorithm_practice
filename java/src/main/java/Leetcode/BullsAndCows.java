package Leetcode;

/**
problem: https://leetcode.com/problems/bulls-and-cows/
level: easy
solution: this is to give out the answer, it is not to guess

#hash_table

 */
public class BullsAndCows {
    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println(sol.getHint("1123", "0111"));
    }

    static
    class Solution {
        public String getHint(String secret, String guess) {
            int[] freqS = new int[10];
            int[] freqG= new int[10];
            int bull = 0;
            int cow = 0;

            for(int i=0; i<secret.length(); ++i){
                if(guess.charAt(i) == secret.charAt(i)) {
                    bull += 1;
                }
                else{
                    freqS[secret.charAt(i)-'0']++;
                    freqG[guess.charAt(i)-'0']++;
                }
            }

            /*
            for(int i=0; i<guess.length(); ++i){
                if(guess.charAt(i) != secret.charAt(i)){
                    if(freq[guess.charAt(i)-'0'] > 0){
                        freq[guess.charAt(i)-'0']--;
                        cow++;
                    }
                }
            }*/

            for(int i=0; i<10; ++i){
                cow+= Math.min(freqG[i], freqS[i]);
            }

            return String.format("%dA%dB", bull, cow);
        }
    }
}
