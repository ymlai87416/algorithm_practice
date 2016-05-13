package Mathematics.SimplerOne;

import java.util.Scanner;

/**
 * Created by Tom on 12/4/2016.
 * Read 3: 13 , AC at 3:25 total time: 12 mins, somehow read the question wrong ....
 */
public class UVA11723 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(a == 0 || b == 0) break;

            int c = (a) / b;
            if (a % b != 0) ++c;
            c--;

            String result;
            if(c > 26)  result = "impossible";
            else result = String.valueOf(c);

            System.out.format("Case %d: %s\n", ++cnt,result);
        }
    }
}
