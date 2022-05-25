package ctci.moderate;
import java.util.*;

public class Rand7FromRand5 {
    public int rand7(){

        int val = 0;

        while(true){
            val = rand5() * 5 + rand5();
            if(val < 21) return val / 3;
        }

    }

    public int rand5(){
        Random r = new Random();
        return r.nextInt(5);
    }

    public static void main(String[] args) {
        Rand7FromRand5 test = new Rand7FromRand5();
        int[] rr = new int[7];
        for(int i=0; i<1000; ++i) {
            int a = test.rand7();
            rr[a]++;
        }

        for (int i = 0; i < 7; i++) {
            System.out.printf("%d ", rr[i]);
        }
        System.out.println();
    }
}
