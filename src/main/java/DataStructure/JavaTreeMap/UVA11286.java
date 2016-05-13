package DataStructure.JavaTreeMap;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Tom on 16/4/2016.
 *
 * Start at 15:49 and AC at 16:04, total of 15 minutes.
 */
public class UVA11286 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            if(a == 0) return;
            sc.nextLine();

            TreeMap<String, Integer> result = new TreeMap<String, Integer>();
            int maxPop = -1;

            for(int i=0; i<a; ++i){
                String aa  =sc.nextLine();
                StringTokenizer t = new StringTokenizer(aa);

                int[] five = new int[5];
                for(int j=0; j<5; ++j){
                    five[j] = Integer.parseInt(t.nextToken());
                }

                Arrays.sort(five);
                String fives = String.format("%d %d %d %d %d", five[0], five[1], five[2], five[3], five[4]);

                int b =  result.getOrDefault(fives, 0);
                result.put(fives, ++b);
                if(b > maxPop) maxPop = b;
            }

            int sumx = 0;
            for(Map.Entry<String, Integer> entry: result.entrySet()){
                if(entry.getValue() == maxPop) sumx += maxPop;
            }

            System.out.println(sumx);
        }
    }
}
