package Mathematics.SimplerOne;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Read at  3:27, AC at 3:33
 * Created by Tom on 12/4/2016.
 */
public class UVA11875 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int tcase = sc.nextInt();
        for(int i=0; i<tcase; ++i){
            int player = sc.nextInt();
            ArrayList<Integer> result = new ArrayList<Integer>();
            for(int l =0; l < player; ++l){
                int age = sc.nextInt();
                result.add(age);
            }

            System.out.format("Case %d: %d\n", i+1, result.get(result.size()/2));
        }
    }
}
