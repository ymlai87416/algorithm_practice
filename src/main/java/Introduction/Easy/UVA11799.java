package Introduction.Easy;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Tom on 13/4/2016.
 * Start  at 14:22, end at 14:29, a very simple max finding
 */
public class UVA11799 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<a; ++i){
            String aa = sc.nextLine();
            StringTokenizer t = new StringTokenizer(aa);
            int max = Integer.MIN_VALUE;
            while(t.hasMoreTokens()){
                int b = Integer.parseInt(t.nextToken());
                if(b > max) max = b;
            }

            System.out.format("Case %d: %d\n", i+1, max);
        }
    }
}
