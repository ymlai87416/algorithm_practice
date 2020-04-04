package DataStructure.JavaQueueAndDeque;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 17/4/2016.
 * 11:37, AC at 11:50, totla time 13 minutes.
 */
public class UVA11034 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        for(int i=0; i<a; ++i){
            int l  =sc.nextInt();
            l = l * 100;
            int m = sc.nextInt();

            Queue<Integer> left = new ArrayDeque<>();
            Queue<Integer> right = new ArrayDeque<>();
            for(int j=0; j<m; ++j){
                int lc = sc.nextInt();
                String dir = sc.next();
                if(dir.compareTo("left") == 0) left.offer(lc);
                else if(dir.compareTo("right") == 0) right.offer(lc);
            }

            int round=0; Queue<Integer> dir=left;
            while(left.size() != 0 || right.size() != 0){
                round+=1;
                int llc = 0;
                while(dir.size() > 0){
                    int next = dir.peek();
                    if(llc + next > l) break;
                    llc += dir.poll();
                }

                if(dir == left) dir = right;
                else if(dir == right) dir = left;
            }
            System.out.println(round);
        }
    }
}
