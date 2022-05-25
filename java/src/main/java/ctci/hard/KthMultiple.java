package ctci.hard;
import java.util.*;

public class KthMultiple {

    public int findKMultiple(int k){

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        if(k == 0) return 0;

        pq.offer(1);

        while(!pq.isEmpty()){
            int u = pq.poll();
            if(!pq.isEmpty() && u == pq.peek()) continue;
            --k;

            if(k == 0) return u;

            pq.offer(u * 3);
            pq.offer(u * 5);
            pq.offer(u * 7);
        }
        return -1;
    }

    public static void main(String[] args) {
        KthMultiple t = new KthMultiple();
        for (int i = 1; i < 20; i++) {
            System.out.println(t.findKMultiple(i));
        }

    }
}
