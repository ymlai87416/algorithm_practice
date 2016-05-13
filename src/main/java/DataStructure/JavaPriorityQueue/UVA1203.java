package DataStructure.JavaPriorityQueue;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Created by Tom on 17/4/2016.
 */
public class UVA1203 {
    public static class Pair implements Comparable<Pair>{
        int time;
        int query;
        int round;

        public Pair(int a, int b, int r){
            query = a; time  = b;
            round  = r;
        }

        @Override
        public int compareTo(Pair o) {
            if(time < o.time) return -1;
            else if (time > o.time) return 1;
            else {
                if(query < o.query) return -1;
                else if(query > o.query) return 1;
                else return 0;
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        TreeSet<Pair> queries =new TreeSet<>();
        while(true){
            String str = sc.nextLine();
            str = str.trim();
            if(str.compareTo("#") == 0)
                break;

            StringTokenizer st = new StringTokenizer(str);
            st.nextToken();
            int query = Integer.parseInt(st.nextToken());
            int time =Integer.parseInt(st.nextToken());

            queries.add(new Pair(query, time, time));
        }

        int k = sc.nextInt();

        for(int i=0; i<k; ++i){
            Pair p = queries.pollFirst();
            System.out.println(p.query);
            queries.add(new Pair(p.query,p.time+p.round, p.round));
        }
    }
}
