package Mathematics.GameTheory;


import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by ymlai on 26/4/2017.
 *
 * problem: https://onlinejudge.org/external/8/847.pdf
 * #UVA #game_theory #Lv2
 */
public class UVA847 {
    static final int MAXV = 4096;
    static int[][] winProb = new int[2][MAXV];

    static int minimax(long n, int id, long cur){
        if(cur * 9 >= n)
            return id==0 ? 1 : 0;

        if(id == 0) //assume Stan is the maximizing player
        {
            int bestValue = Integer.MIN_VALUE;
            for(int i=2; i<=9; ++i){
                int v = minimax(n, 1-id, cur*i);
                bestValue = Math.max(v, bestValue);
            }
            return bestValue;
        }
        else
        {
            int bestValue = Integer.MAX_VALUE;
            for(int i=2; i<=9; ++i){
                int v = minimax(n, 1-id, cur*i);
                bestValue = Math.min(v, bestValue);
            }
            return bestValue;
        }
    }

    static void solveSmall(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            long n = sc.nextInt();

            if(minimax(n, 0, 1) == 1)
                System.out.print("Stan wins.\n");
            else
                System.out.print("Ollie wins.\n");
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int[] mul = {9, 2};
        while(sc.hasNextInt()){
            long n = sc.nextInt();

            long temp = 1;
            int pos = 0;
            while(temp < n){
                temp *= mul[pos];
                pos = 1-pos;
            }

            if(pos == 1)
                System.out.println("Stan wins.");
            else
                System.out.println("Ollie wins.");
        }
    }
}
