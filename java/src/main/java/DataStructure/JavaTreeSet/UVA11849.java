package DataStructure.JavaTreeSet;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Tom on 17/4/2016.
 *
 * Start at 2:02, TLE at 2:08 using TreeSet impl and then AC at 2:14, total time 12 minutes.
 */
public class UVA11849 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if( a==0 && b ==0) break;

            int[] jack = new int[a];

            for(int i=0; i<a; ++i){
                jack[i] = sc.nextInt();
            }
            int p=0; int sum=0;
            for(int i=0; i<b; ++i){
                int d = sc.nextInt();
                while(jack[p] < d){
                    if(p==a-1) break;
                    p++;
                }
                if(jack[p] == d) ++sum;
            }

            System.out.println(sum);
        }
    }
}
