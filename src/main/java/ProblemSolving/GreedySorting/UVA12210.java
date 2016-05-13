package ProblemSolving.GreedySorting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 7/5/2016.
 */
public class UVA12210 {
    static int[] bs = new int[10001];
    static int[] ss = new int[10001];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int cnt = 0;
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a == 0 && b == 0) break;

            for(int i=0; i<a; ++i)
                bs[i] = sc.nextInt();
            for(int i=0; i<b; ++i)
                ss[i] = sc.nextInt();

            Arrays.sort(bs, 0, a);

            if(a > b)
                System.out.format("Case %d: %d %d\n", ++cnt, a-b, bs[0]);
            else
                System.out.format("Case %d: 0\n", ++cnt);
        }
    }
}
