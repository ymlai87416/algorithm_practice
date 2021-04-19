package ProblemSolving.GreedyClassical;

import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/124/12405.pdf
 level:
 solution:

 Start at 0:41 and AC at 0:58, total time spent: 17 mins.

 #greedy

 **/

public class UVA12405 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i=0; i<t; ++i){
            int len = sc.nextInt();
            sc.nextLine();

            String f = sc.nextLine();

            int result=  0;
            char prev = '#';
            for(int j=0; j<f.length(); ++j){
                if(f.charAt(j) == '.'){
                    prev = f.charAt(Math.min(f.length()-1, j+2));
                    j+=2; //next will add 1 also
                    result++;
                } else
                    prev = f.charAt(j);
            }
            System.out.format("Case %d: %d\n", i+1, result);
        }
    }
}
