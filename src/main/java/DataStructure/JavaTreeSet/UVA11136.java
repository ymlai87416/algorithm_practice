package DataStructure.JavaTreeSet;

import java.util.*;

/**
 * Created by Tom on 17/4/2016.
 *
 * Start at 2:30 and WA at 2:40 deal to the sum is not long (shit), get AC by 3:02. total minutes: 32 minutes. Need to create a multiset implementation. or use Guva. let check it out.
 */
public class UVA11136 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            if(a == 0) break;
            long sum = 0;
            TreeMap<Integer, Integer> bag = new TreeMap<Integer, Integer>();
            for(int i=0; i<a; ++i){
                int b = sc.nextInt();
                for(int j=0; j<b; ++j){
                    int c = sc.nextInt();
                    int x = bag.getOrDefault(c, 0);
                    ++x;
                    bag.put(c, x);
                }
                //at the end
                boolean spec=false;
                Map.Entry<Integer, Integer> last = bag.pollLastEntry();
                Map.Entry<Integer, Integer> first =bag.pollFirstEntry();
                if(first  == null) {
                    first = last;
                    spec=true;
                }
                sum += last.getKey()-first.getKey();
                if(!spec){
                    if(last.getValue()> 1) bag.put(last.getKey(), last.getValue()-1);
                    if(first.getValue()> 1) bag.put(first.getKey(), first.getValue()-1);
                }
                else
                    if(first.getValue() > 2)bag.put(first.getKey(), first.getValue()-2);
            }

            System.out.println(sum);
        }
    }
}
