package StringProcessing.DPClassic;

import java.util.*;

/**
 * Created by Tom on 14/5/2016.
 *
 * Longest common substring on 62.5k, which need nlogn algorithm
 *
 * problem: https://onlinejudge.org/external/106/10635.pdf
 * #UVA #Lv3 #string #longest_common_substring #dp
 */
public class UVA10635 {
    static int[] pba = new int[62501];
    static int[] pga = new int[62501];

    static int[] p = new int[62501];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int nc = sc.nextInt();
        for(int ci=0; ci<nc; ++ci){
            int n = sc.nextInt();
            int pb = sc.nextInt();
            int pg = sc.nextInt();
            pb++;
            pg++;

            for(int i=0; i<pb; ++i)
                pba[i] = sc.nextInt();

            for(int i=0; i<pg; ++i)
                pga[i] = sc.nextInt();

            Arrays.fill( p, -1);
            for(int i=0; i<pg; ++i)
                p[pga[i]] = i;

            TreeSet<Integer> v = new TreeSet<Integer>();
            v.add(-1);

            for(int i=0; i<pb; ++i){
                int j = p[pba[i]];
                if(j == -1) continue;

                if(j > v.last())
                    v.add(j);
                else {
                    int ceil = v.ceiling(j);
                    if(ceil != j){
                        v.remove(ceil);
                        v.add(j);
                    }
                }
            }

            System.out.format("Case %d: %d\n", ci+1, v.size()-1);
        }
    }
}
