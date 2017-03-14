package MoreAdvanceTopic.ChallengingBacktrackProblem;

import java.util.Scanner;

/**
 * Created by Tom on 13/3/2017.
 *
 * For long bitwise opeartion, please add 1L.. shit this.
 */
public class UVA11065 {

    static long[] adjMatrix = new long[65];
    static int vertex;
    static int edge;
    static int ans;
    static int nS;

    static void findSet(int i, long used, int depth){
        if(i == vertex){
            if(used == (1L << vertex) -1){
                nS++;
                ans = Math.max(ans, depth);
            }
        }
        else{
            if((used & (1L << i)) == 0){
                findSet(i+1, used | adjMatrix[i], depth+1);
                findSet(i+1, used, depth);
            }
            else
                findSet(i+1, used, depth);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nCase = sc.nextInt();

        for(int i=0; i<nCase; ++i){
            vertex = sc.nextInt();
            edge = sc.nextInt();

            int start, end;

            for(int j=0; j<vertex; ++j) adjMatrix[j] = 1L << j;

            for(int j=0; j<edge; ++j){
                start = sc.nextInt();
                end = sc.nextInt();
                adjMatrix[start] = adjMatrix[start] | (1L << end);
                adjMatrix[end] = adjMatrix[end] | (1L << start);
            }

            ans = 0;
            nS=0;

            findSet(0, 0, 0);

            System.out.println(nS);
            System.out.println(ans);
        }
    }
}
