package ctci.hard;
import java.util.*;

public class TheMasseuse {

    int[] memo;
    int[] arr;

    public int optimalMasseuse(int[] arr){

        this.arr = arr;
        this.memo = new int[arr.length+1];

        Arrays.fill(memo, -1);

        return optimalMasseuseHelper(0);
    }

    public int optimalMasseuseHelper(int index){
        if(index >= arr.length) return 0;
        if(memo[index] != -1) return memo[index];


        //take it or not
        int ans = Math.max(optimalMasseuseHelper(index+1), optimalMasseuseHelper(index+2) + arr[index]);
        memo[index] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] data = new int[]{30, 15, 60, 75, 45, 15, 15, 45};

        TheMasseuse test = new TheMasseuse();
        System.out.println(test.optimalMasseuse(data));
    }

}
