package ProblemSolving.IterativeOneLoop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tom on 17/4/2016.
 *
 * Start at  21:33 and with 2 time limit exceed, and becuase I forget to use double to calculate x, I got WA 2 times, and finall y accept AC at 22:18, totalt time: 45 minutes.
 *
 * problem: https://onlinejudge.org/external/109/10976.pdf
 * #UVA #Lv2 #iterative_search #linear_scan
 */
public class UVA10976 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            int a = sc.nextInt();
            sc.nextLine();

            int maxx = (a+1) * a;
            maxx++;
            ArrayList<String> result = new ArrayList<String>();
            for(int y=a*2; y>=1; --y){
                long x = y==a ? Long.MAX_VALUE : Math.round( a * 1.0 / (y -a) * y);
                if(y > x) break;
                if(x*y == a*(x+y))
                    result.add(String.format("1/%d = 1/%d + 1/%d", a, x, y));
            }

            System.out.println(result.size());
            for(int i=result.size()-1; i>=0; --i)
                System.out.println(result.get(i));
        }

    }
}
