package RareTopic.TowerOfHanoi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by ymlai on 16/4/2017.
 */
public class UVA10017 {
    static Stack<Integer>[] stacks = new Stack[3];

    static boolean canMoveFrom(int a, int b){
        if(stacks[a].isEmpty()) return false;
        if(stacks[b].isEmpty()) return true;
        int p = stacks[a].peek();
        int q = stacks[b].peek();
        if(p < q) return true;
        return false;
    }

    static void outputStack(){
        if(stacks[0].size() > 0){
            System.out.print("A=>  ");
            for(int j=0; j<stacks[0].size(); ++j)
                System.out.print(" " + stacks[0].get(j));
            System.out.println();
        }
        else
            System.out.println("A=>");


        if(stacks[1].size() > 0){
            System.out.print("B=>  ");
            for(int j=0; j<stacks[1].size(); ++j)
                System.out.print(" " + stacks[1].get(j));
            System.out.println();
        }
        else
            System.out.println("B=>");

        if(stacks[2].size() > 0){
            System.out.print("C=>  ");
            for(int j=0; j<stacks[2].size(); ++j)
                System.out.print(" " + stacks[2].get(j));
            System.out.println();
        }
        else
            System.out.println("C=>");

        System.out.println();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = 0;
        for(int i=0; i<3; ++i)
            stacks[i] = new Stack<>();

        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a ==0 && b==0)
                break;

            ++nc;

            stacks[0].clear();
            for(int i=a; i>0; --i)
                stacks[0].add(i);

            stacks[1].clear();
            stacks[2].clear();

            System.out.format("Problem #%d\n\n", nc);

            outputStack();

            int prev = -1;
            int first = a % 2 == 0? 1: 2;
            int second = a%2==0?2:1;

            for(int i=0; i<b; ++i){
                int k=0;
                int to=0;
                for(; k<3; ++k){
                    if(stacks[k].isEmpty()) continue;
                    if(prev == stacks[k].peek()) continue; //never move the same disk twice.
                    if(canMoveFrom(k, (k+first)%3)) {
                        to = (k+first)%3;
                        break;
                    }
                    if(canMoveFrom(k, (k+second)%3)) {
                        to = (k+second)%3;
                        break;
                    }
                }

                if(k == 3) {
                    System.out.println("shit!!");
                    break;
                }

                prev=stacks[k].pop();
                stacks[to].push(prev);

                outputStack();
            }
        }
    }
}
