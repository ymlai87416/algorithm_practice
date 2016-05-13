package DataStructure.OneDArrayManipulation;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Tom on 15/4/2016.
 */
public class UVA11340 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a  = sc.nextInt();

        for(int i=0; i< a; ++i){
            int b = sc.nextInt();

            HashMap<Character, Integer> price  = new HashMap<>();
            for(int j=0; j<b; ++j){
                String c =sc.next();
                int d = sc.nextInt();
                price.put(c.charAt(0), d);
            }

            int e = sc.nextInt(); sc.nextLine();
            int sum = 0;
            for(int j=0; j<e; ++j){
                String c = sc.nextLine();

                for(int k=0; k<c.length(); ++k){
                    sum +=price.getOrDefault(c.charAt(k), 0);
                }
            }

            System.out.format("%.2f$\n", sum/100.0);
        }
    }
}
