package ProblemSolving.GreedySorting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 7/5/2016.
 */
public class UVA11100 {
    static int[] bags = new int[10001];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        boolean first= true;
        while(true){
            int a = sc.nextInt();
            if(a == 0) break;
            if(first) first =false; else System.out.println();

            for(int i=0; i<a; ++i){
                bags[i] = sc.nextInt();
            }

            Arrays.sort(bags, 0, a);

            int maxcont = 1;
            int cont = 1;
            for(int i=1; i<a; ++i){
                if(bags[i] == bags[i-1])
                    cont++;
                else{
                    if(maxcont < cont) maxcont = cont;
                    cont = 1;
                }
            }
            if(maxcont < cont) maxcont = cont;

            System.out.println(maxcont);

            for(int i=0; i<maxcont; ++i){
                for(int j=i; j<a; j+= maxcont)
                    if(j == i) System.out.print(bags[j]);
                    else System.out.print(" " + bags[j]);
                System.out.println();
            }
        }
    }
}
