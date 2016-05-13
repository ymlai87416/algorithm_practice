package DataStructure.JavaPriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Tom on 17/4/2016.
 * Start at 19:29 and AC at 19:59, total time: 20 minutes, 10 minutes break.
 */
public class UVA10954 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a  = sc.nextInt();
            if(a == 0) break;

            PriorityQueue<Integer> b = new PriorityQueue<>();
            for(int i=0; i<a; ++i){
                b.add(sc.nextInt());
            }

            long sum = 0;
            while(b.size() > 1){
                int p =  b.poll();
                int q = b.poll();
                b.add(p+q);
                sum += p+q;
            }

            System.out.println(sum);
        }
    }
}
