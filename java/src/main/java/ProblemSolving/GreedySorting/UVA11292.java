package ProblemSolving.GreedySorting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 7/5/2016.
 *
 * problem: https://onlinejudge.org/external/112/11292.pdf
 * #UVA #Lv1 #greedy #greedy_sorting
 */
public class UVA11292 {
    static int[] head = new int[20001];
    static int[] knight = new int[20001];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(a == 0 && b == 0) break;

            for(int i=0; i<a; ++i){
                head[i] = sc.nextInt();
            }

            for(int i=0; i<b; ++i){
                knight[i] = sc.nextInt();
            }

            Arrays.sort(head, 0, a);
            Arrays.sort(knight, 0, b);

            int cur = 0;
            int i = 0;
            int gold = 0;
            for(i=0; i<a; ++i){
                if(cur >= b) break;
                while(knight[cur] < head[i]){
                    ++cur;
                    if(cur >= b) break;
                }
                if(cur >= b) break;
                gold += knight[cur++];
            }

            if(i == a)
                System.out.println(gold);
            else
                System.out.println("Loowater is doomed!");
        }
    }
}
