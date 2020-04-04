package DataStructure.JavaQueueAndDeque;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 17/4/2016.
 * Started at 12:21 and end at 12:49 total minutes = 28 minutes.
 */
public class UVA10901 {
    static class Pair{
        int time;
        int order;
        public Pair(int time, int order){
            this.time = time; this.order= order;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();

        for(int i=0; i<c; ++i){
            int n = sc.nextInt();
            int t = sc.nextInt();
            int m = sc.nextInt();

            int[] mr=  new int[m];

            Queue<Pair> left = new ArrayDeque<Pair>();
            Queue<Pair> right = new ArrayDeque<Pair>();

            for(int j=0; j<m; ++j){
                int d = sc.nextInt();
                String side = sc.next();

                if(side.compareTo("left") == 0){
                    left.offer(new Pair(d, j));
                } else if(side.compareTo("right") == 0){
                    right.offer(new Pair(d, j));
                }
            }

            Queue<Pair> dir = left;
            int curTime = 0;
            while(left.size() > 0 || right.size() > 0){
                while(true){
                    int size = 0;
                    while(dir.size() > 0 && dir.peek().time <= curTime && size < n){
                        Pair cx = dir.poll();
                        mr[cx.order] = curTime + t;
                        size++;
                    }
                    if(size == 0) {
                        if(dir == left && right.size() > 0 && right.peek().time <= curTime)
                            break;
                        else if(dir==right && left.size() > 0 && left.peek().time <= curTime)
                            break;
                        else
                            curTime++;
                    }
                    else break;
                }
                curTime += t;

                if(dir == left) dir=right;
                else if(dir==right) dir=left;
            }
            for(int j=0 ;j<m; ++j)
                System.out.println(mr[j]);

            if(i != c-1) System.out.println();
        }



    }
}
