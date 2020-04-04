package ProblemSolving.GreedyNonClassical;

import java.util.Scanner;

/**
 * Created by Tom on 8/5/2016.
 */
public class UVA10656 {
    static int[] result = new int[1001];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int n = sc.nextInt();
            if(n==0) break;

            int cnt = 0;
            for(int i=0; i<n; ++i){
                int a = sc.nextInt();
                if(a != 0) result[cnt++] = a;
            }

            if(cnt == 0)
                System.out.println("0");
            else{
                for(int i=0; i<cnt; ++i){
                    if(i == 0) System.out.print(result[i]);
                    else System.out.print(" " + result[i]);
                }
                System.out.println();
            }
        }
    }
}
