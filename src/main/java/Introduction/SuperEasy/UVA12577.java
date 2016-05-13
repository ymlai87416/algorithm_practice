package Introduction.SuperEasy;

import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 */
public class UVA12577 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int ci = 0;
        while(true){
            String b = sc.next();
            if(b.trim().compareTo("*") == 0) break;
            if(b.compareTo("Hajj") ==0) System.out.format("Case %d: Hajj-e-Akbar\n", ++ci);
            else System.out.format("Case %d: Hajj-e-Asghar\n", ++ci);
        }
    }
}
