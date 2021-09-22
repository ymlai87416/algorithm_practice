package DataStructure.JavaTreeMap;

import java.util.*;

/**
 * Created by Tom on 17/4/2016.
 * Start at  0:04 and AC at 0:15, new algorithm for finding the maximum length of string having no repeating characters.
 *
 * problem: https://onlinejudge.org/external/115/11572.pdf
 *
 * #Lv2 #hash_table #UVA
 */
public class UVA11572 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a  = sc.nextInt();
        for(int i=0; i<a; ++i){
            int b = sc.nextInt();

            TreeSet<Integer> here = new TreeSet<Integer>();
            ArrayList<Integer> read = new ArrayList<Integer>();
            int p = 0; int q = 0;
            int maxLen = 0;
            for(int j=0; j<b; ++j){
                int c = sc.nextInt();
                read.add(c);
                if(here.contains(c)){
                    maxLen = Math.max(maxLen, q-p);
                    while(read.get(p).compareTo(read.get(q)) != 0){
                        here.remove(read.get(p));
                        ++p;
                    }
                    ++p;
                    ++q;
                } else{
                    here.add(c);
                    ++q;
                }
            }
            maxLen = Math.max(maxLen, b-p);

            System.out.println(maxLen);
        }
    }
}
