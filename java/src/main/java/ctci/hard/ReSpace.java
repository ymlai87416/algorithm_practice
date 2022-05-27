package ctci.hard;
import java.util.*;

public class ReSpace {

    int[][] memo;
    List<String> dictionary;
    String s;
    int n;

    public String reSpace(String s, List<String> dictionary){
        this.dictionary = dictionary;
        this.s = s;

        n = s.length();
        memo = new int[n+1][n+1];

        for(int i=0; i<n+1; ++i){
            Arrays.fill(memo[i], -1);
        }

        int result = helper(0, -1);

        return backtrack();
    }

    private String backtrack(){
        //break it when we see the difference between memo[i+1][i] + (j..i) = memo[i+1][i];
        StringBuilder sb = new StringBuilder();

        int i=0;
        int lastIndex = -1;

        while(i < n){
            //memo[i][last] can either equal to memo[i+1][i] or memo[i+1][last];
            if(memo[i][lastIndex+1] == memo[i+1][lastIndex+1]){
                sb.append(s.charAt(i));
            }
            else{
                sb.append(s.charAt(i));
                sb.append(" ");
                lastIndex = i;
            }
            i++;
        }

        return sb.toString();
    }

    private int helper(int i, int lastIndex){
        if(memo[i][lastIndex+1] != -1){
            return memo[i][lastIndex+1];
        }

        int len = i - (lastIndex+1) +1 ;
        if(i == n){
            memo[i][lastIndex+1] = len-1;
            return len-1;  //!!
        }

        String ss = s.substring(lastIndex+1, i+1);

        memo[i][lastIndex+1] = helper(i+1, i) + (dictionary.contains(ss) ? 0: len);
        memo[i][lastIndex+1] = Math.min(memo[i][lastIndex+1], helper(i+1, lastIndex));

        return memo[i][lastIndex+1];
    }

    public static void main(String[] args) {
        String input = "jesslookedjustliketimherbrother";
        List<String> dictionary = List.of("looked", "just", "like", "her", "brother");

        ReSpace test=  new ReSpace();
        System.out.println(test.reSpace(input, dictionary));

    }
}
