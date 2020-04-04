package DataStructure.JavaTreeMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 16/4/2016.
 *
 * Start at 23:24, and end at 23:47, and the total time is 23 minutes..
 */
public class UVA10226 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a  = sc.nextInt();
        sc.nextLine();
        sc.nextLine();

        for(int i=0; i<a; ++i){
            TreeMap<String, Integer> result = new TreeMap<>();
            int tcnt = 0;
            while(true){
                if(!sc.hasNext()) break;
                String s = sc.nextLine();
                if(s.trim().length() == 0) break;

                int b = result.getOrDefault(s, 0);
                result.put(s, b+1);
                tcnt++;
            }

            for(Map.Entry<String, Integer> e : result.entrySet()){
                System.out.format("%s %.4f\n", e.getKey(), e.getValue() * 100.0/tcnt);
            }

            if(i!= a-1) System.out.println();
        }
    }
}
