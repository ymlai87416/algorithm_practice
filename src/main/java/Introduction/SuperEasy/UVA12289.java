package Introduction.SuperEasy;

import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 */
public class UVA12289 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();

        for(int i=0; i<a; ++i){
            String b = sc.next();
            if(b.length() == 5) System.out.println(3);
            else{
                int r = 0;
                if(b.charAt(0) == 'o') r++;
                if(b.charAt(1) == 'n') r++;
                if(b.charAt(2) == 'e') r++;

                if(r >=2)System.out.println(1);
                else System.out.println(2);

            }
        }
    }
}
