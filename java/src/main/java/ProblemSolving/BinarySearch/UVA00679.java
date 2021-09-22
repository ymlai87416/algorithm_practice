package ProblemSolving.BinarySearch;

import java.util.Scanner;

/**
 *
 * problem: https://onlinejudge.org/external/6/679.pdf
 * #UVA #Lv2 #binary_search
 */

public class UVA00679 {

    /* inspect the binary pattern of the number and you will know where it goes */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nt = sc.nextInt();
        for(int t=0; t<nt; ++t){
            int d = sc.nextInt();
            int i = sc.nextInt();

            int n = 1;

            for(int p=0; p<d-1; p++){
                if(((i-1) & (1 << p)) == 0 ){
                    //System.out.println("move left");
                    n = 2*n;
                }
                else{
                    //System.out.println("move right");
                    n = 2*n+1;
                }
            }

            System.out.println(n);
        }

        sc.nextInt();
    }

}
