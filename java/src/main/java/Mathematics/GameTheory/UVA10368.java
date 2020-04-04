package Mathematics.GameTheory;

import java.util.Scanner;

/**
 * Created by ymlai on 26/4/2017.
 */
public class UVA10368 {

    static int minimax(long id, long a, long b){
        if(a % b == 0)
            return id==0?1:0; //1=Stan win, 0=Ollie win

        if(id == 0) // Stan is the maximizing player
        {
            int bestValue = Integer.MIN_VALUE;
            //from (a, b), I can go to (b, a-b) or force you go (b, a-b), just 2 case

            int v = minimax(1-id, b, a%b);
            bestValue = Math.max(v, bestValue);
            if(a > 2*b) {
                v = minimax(1 - id, a % b + b, b);
                bestValue = Math.max(v, bestValue);
            }
            return bestValue;
        }
        else
        {
            int bestValue = Integer.MAX_VALUE;
            int v = minimax(1-id, b, a%b);
            bestValue = Math.min(v, bestValue);
            if(a > 2*b) {
                v = minimax(1 - id, a % b + b, b);
                bestValue = Math.min(v, bestValue);
            }
            return bestValue;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            long a = sc.nextLong();
            long b = sc.nextLong();

            if(a==0 && b ==0) break;

            if(minimax(0, Math.max(a, b), Math.min(a,b))== 1)
                System.out.println("Stan wins");
            else
                System.out.println("Ollie wins");
        }
    }
}
