package DataStructure.JavaPriorityQueue;

import java.util.*;

/**
 * Created by Tom on 17/4/2016.
 * Start at 19:50, WA at 20:07 and 20:08, AC at 20:11, total minutes 21 minutes.
 */
public class UVA11995 {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            int a = sc.nextInt();

            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new ArrayDeque<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            boolean is, iq, ipq; is=true; iq=true; ipq=true;

            for(int i=0 ;i<a; ++i){
                int op =sc.nextInt();
                int r = sc.nextInt();

                if(op == 1){
                    stack.push(r);
                    queue.offer(r);
                    priorityQueue.offer(r);
                } else if(op == 2){
                    int sr, qr, pqr;
                    try {
                        sr = stack.pop();
                        if(sr != r) is = false;
                    } catch(Exception ex) { is = false;}
                    try{
                        qr = queue.poll();
                        if(qr != r) iq = false;
                    } catch(Exception ex) {iq=false;}
                    try {
                        pqr = priorityQueue.poll();
                        if(pqr != r) ipq = false;
                    } catch (Exception ex) {ipq = false;}
                }
            }

            if(is && iq || is && ipq || iq && ipq)
                System.out.println("not sure");
            else if( !is && !iq && !ipq)
                System.out.println("impossible");
            else if(is)
                System.out.println("stack");
            else if(iq)
                System.out.println("queue");
            else if(ipq)
                System.out.println("priority queue");

            if(!sc.hasNext()) break;
            sc.nextLine();
        }
    }
}
