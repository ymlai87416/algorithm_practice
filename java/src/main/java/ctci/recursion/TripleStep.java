package ctci.recursion;
import java.util.*;

public class TripleStep {
    int[] memo;

    public int tripleStep(int n){
        memo = new int[n+1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return tripleStepHelper(n);
    }

    public int tripleStepHelper(int n){
        if(n < 0) return 0;
        if(memo[n] != -1) return memo[n];
        return tripleStepHelper(n-1) + tripleStepHelper(n-2) + tripleStepHelper(n-3);

    }

    public static void main(String[] args) {
        TripleStep test = new TripleStep();
        for(int i=0; i<10; ++i){
            System.out.println(i + ": " + test.tripleStep(i));
        }
    }

}
