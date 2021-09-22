package ProblemSolving.IterativeTwoLoop;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Tom on 17/4/2016.
 *
 * Start at 14:20 and AC at 14:36
 *
 * problem: https://onlinejudge.org/external/12/1260.pdf
 * #UVA #Lv2 #iterative_search #search_O(n^2)
 */
public class UVA1260 {
    static class Pair implements Comparable<Pair>{
        int a, order;
        public Pair(int a, int order){
            this.a = a;
            this.order = order;
        }

        @Override
        public int compareTo(Pair o) {
            if(a == o.a)
                return order - o.order;
            else
                return a - o.a;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();

        for(int i=0; i<c; ++i){
            int a = sc.nextInt();

            TreeSet<Pair> days= new TreeSet<>();
            int sum = 0;
            for(int j=0; j<a; ++j){
                int aa = sc.nextInt();
                Pair aap = new Pair(aa, j);
                if(j > 0){
                    Set<Pair> headset = days.headSet(aap);
                    sum += headset.size();
                }
                days.add(aap);
            }

            System.out.println(sum);
        }
    }
}
