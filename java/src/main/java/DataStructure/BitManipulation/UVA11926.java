package DataStructure.BitManipulation;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by Tom on 15/5/2016.
 *
 * problem: https://onlinejudge.org/external/119/11926.pdf
 *
 * #bit_manipulation #UVA #Lv3
 */
public class UVA11926 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int n = sc.nextInt();
            int m = sc.nextInt();
            if(n == 0 && m == 0) break;

            PriorityQueue<Pair> queue = new PriorityQueue<>();
            int curTime = 0;

            for(int i=0; i<n; ++i){
                int start = sc.nextInt();
                int end = sc.nextInt();
                queue.offer(new Pair(start, end, -1));
            }

            for(int i=0; i<m; ++i){
                int start = sc.nextInt();
                int end = sc.nextInt();
                int rep = sc.nextInt();
                queue.offer(new Pair(start, end, rep));
            }

            boolean conflict = false;
            while(!queue.isEmpty()){
                Pair  u = queue.poll();
                if(u.first > 1000000) break;

                if(u.first < curTime){
                    conflict = true;
                    break;
                }

                curTime = u.second;

                if(u.third !=-1){
                    queue.offer(new Pair(u.first+u.third, u.second+u.third, u.third));
                }

            }

            if(conflict)
                System.out.print("CONFLICT\n");
            else
                System.out.print("NO CONFLICT\n");
        }
    }

    static class Pair implements  Comparable<Pair>{
        int first;
        int second;
        int third;

        public Pair(int first, int second, int third){
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public int compareTo(Pair o) {
            if(first < o.first) return -1;
            else if(first > o.first) return 1;
            else return second - o.second;
        }
    }
}
