package RareTopic.SortingInLinearTime;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Tom on 13/4/2016.
 * 20: 38 Start, 1 WA for forgetting return from 0, and then AC at 20:58
 */
public class UVA11462 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a =sc.nextInt();
            if(a == 0) break;
            sc.nextLine();
            String input = sc.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(input);

            int[] sort = new int[101];
            Arrays.fill(sort, 0);
            for(int i=0; i<a; ++i){
                int b = Integer.parseInt(tokenizer.nextToken());
                sort[b]++;
            }

            boolean first = true;
            for(int i=0; i<sort.length; ++i){
                for(int j=0; j<sort[i]; ++j){
                    if(!first)  System.out.print(" "); else first = false;
                    System.out.print(i);
                }
            }
            System.out.println();
        }
    }
}
