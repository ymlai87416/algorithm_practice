package Introduction.SuperEasy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 * Start at 21:09 and AC at 21:14
 */
public class UVA11727 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        for(int i=0; i<a; ++i){
            int b1 = sc.nextInt();
            int b2 = sc.nextInt();
            int b3 = sc.nextInt();

            int[] sort = new int[] {b1, b2, b3};
            Arrays.sort(sort);
            System.out.format("Case %d: %d\n", i+1, sort[1]);
        }
    }
}
