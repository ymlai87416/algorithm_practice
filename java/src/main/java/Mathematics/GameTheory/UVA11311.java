package Mathematics.GameTheory;

import java.util.Scanner;

/**
 * Created by ymlai on 27/4/2017.
 *
 * Try solve this nim game in minimax, very difficult, just apply nim formula.
 *
 * problem: https://onlinejudge.org/external/113/11311.pdf
 * #UVA #game_theory #Lv4
 */
public class UVA11311{

    /*static int minimax(long id, long m, long n, long x, long y, int level){

        if(n == 1 && m == 1) {
            System.out.format("Func: %d %d %d %d %d (%d); retVal:(%d)\n", id, m, n, x, y, level, id == 0 ? 0 : 1);
            return id == 0 ? 0 : 1; //0=Hansel lose, 1=Gretel lose
        }

        if(id == 0) // Hansel turn want 1
        {
            int bestValue = Integer.MIN_VALUE;
            //from (a, b), I can go to (b, a-b) or force you go (b, a-b), just 2 case
            int v;
            //column 2 way
            if(n > 1){
                if(y > 0) {
                    if(y > 1) {
                        v = minimax(1 - id, m, n-(y-1), x, 1, level+1);
                        bestValue = Math.max(v, bestValue);
                    }
                    v = minimax(1 - id, m, n-y, x, 0, level+1);
                    bestValue = Math.max(v, bestValue);
                }
                if(n > y+2){
                    v = minimax(1 - id, m, y+2, x, y, level+1);
                    bestValue = Math.max(v, bestValue);
                }
                if(n > y+1){
                    v = minimax(1 - id, m, y+1, x, y, level+1);
                    bestValue = Math.max(v, bestValue);
                }

                //return bestValue;
            }

            if(m > 1){
                if(x > 0) {
                    if(x > 1) {
                        v = minimax(1 - id, m-(x-1), n, 1, y, level+1);
                        bestValue = Math.max(v, bestValue);
                    }
                    v = minimax(1 - id, m-x, n, 0, y, level+1);
                    bestValue = Math.max(v, bestValue);
                }
                if(m > x+2){
                    v = minimax(1 - id, x+2, n, x, y, level+1);
                    bestValue = Math.max(v, bestValue);
                }
                if(m > x+1){
                    v = minimax(1 - id, x+1, n, x, y, level+1);
                    bestValue = Math.max(v, bestValue);
                }

                //return bestValue;
            }

            System.out.format("Func: %d %d %d %d %d (%d); retVal:(%d)\n", id, m, n, x, y, level, bestValue);
            return bestValue;
        }
        else //Gretel turn, want 0
        {
            //column
            int bestValue = Integer.MAX_VALUE;
            int v;
            if(n > 1){
                if(y > 0) {
                    if(y > 1) {
                        v = minimax(1 - id, m, n-(y-1), x, 1, level+1);
                        bestValue = Math.min(v, bestValue);
                    }
                    v = minimax(1 - id, m, n-y, x, 0, level+1);
                    bestValue = Math.min(v, bestValue);
                }
                if(n > y+2){
                    v = minimax(1 - id, m, y+2, x, y, level+1);
                    bestValue = Math.min(v, bestValue);
                }
                if(n > y+1){
                    v = minimax(1 - id, m, y+1, x, y, level+1);
                    bestValue = Math.min(v, bestValue);
                }

                //return bestValue;
            }

            if(m > 1){
                if(x > 0) {
                    if(x > 1) {
                        v = minimax(1 - id, m-(x-1), n, 1, y, level+1);
                        bestValue = Math.min(v, bestValue);
                    }
                    v = minimax(1 - id, m-x, n, 0, y, level+1);
                    bestValue = Math.min(v, bestValue);
                }
                if(m > x+2){
                    v = minimax(1 - id, x+2, n, x, y, level+1);
                    bestValue = Math.min(v, bestValue);
                }
                if(m > x+1){
                    v = minimax(1 - id, x+1, n, x, y, level+1);
                    bestValue = Math.min(v, bestValue);
                }

                //return bestValue;
            }
            System.out.format("Func: %d %d %d %d %d (%d); retVal:(%d)\n", id, m, n, x, y, level, bestValue);
            return bestValue;
        }

        //return Integer.MAX_VALUE;
    }*/

    static int nim(int m, int n, int x, int y){
        int k1 = x;
        int k2 = y;
        int k3 = m-x-1;
        int k4 = n-y-1;

        return k1^k2^k3^k4;
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int i=0; i<nc; ++i){
            int n, m, r, c;
            m = sc.nextInt();
            n = sc.nextInt();
            r = sc.nextInt();
            c = sc.nextInt();

            if(nim(m, n, r, c) != 0)
                System.out.println("Gretel");
            else
                System.out.println("Hansel");

            /*if(minimax(0, m, n, r, c, 1) == 1)
                System.out.println("Gretel");
            else
                System.out.println("Hansel");*/
        }
    }
}
