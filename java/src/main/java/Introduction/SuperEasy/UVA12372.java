package Introduction.SuperEasy;

import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 */
public class UVA12372 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc =sc.nextInt();


        for(int i=0; i<nc; ++i){
            boolean result =true;
            for(int j=0; j<3; ++j)
                if(sc.nextInt() > 20) result = false;

            if(result)System.out.format("Case %d: good\n", i+1);
            else System.out.format("Case %d: bad\n", i+1);
        }

    }
}
