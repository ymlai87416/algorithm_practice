package Introduction.SuperEasy;

import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 */
public class UVA12403 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();
        sc.nextLine();
        long d = 0;
        for(int i=0; i<nc; ++i){
            String b = sc.next();
            if(b.compareTo("donate") == 0){
                d += sc.nextInt();
            } else
                System.out.println(d);
        }
    }
}
