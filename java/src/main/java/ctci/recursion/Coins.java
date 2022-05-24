package ctci.recursion;
import java.util.*;

public class Coins {
    int[][] memo;
    int[] coin = new int[]{25, 10, 5, 1};
    public int coins(int value){

        memo = new int[4][value+1];
        for(int i=0; i<3; ++i)
            Arrays.fill(memo[i], -1);
        Arrays.fill(memo[3], 1);

        return coinsHelper(value, 0);
    }

    private int coinsHelper(int sum, int index){
        if(memo[index][sum] != -1)
            return memo[index][sum];


        int result = 0;

        while(sum >= coin[index]){
            result += coinsHelper(sum, index+1);
            sum -= coin[index];
        }


        memo[index][sum] = result;
        return result;

    }


    public static void main(String[] args) {
        Coins test = new Coins();
        System.out.println("100: " + test.coins(100));
        System.out.println("25: " + test.coins(25));
    }
}
