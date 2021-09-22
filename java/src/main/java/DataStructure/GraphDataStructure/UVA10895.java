package DataStructure.GraphDataStructure;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tom on 15/5/2016.
 * problem: https://onlinejudge.org/external/108/10895.pdf
 *
 * #Lv3 #UVA #graph
 */
public class UVA10895 {
    static ArrayList<Pair>[] result = new ArrayList[10001];
    static int[] stageIdx = new int[1001];
    static int[] stageVal = new int[1001];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNextInt()) break;
            int m = sc.nextInt();
            int n = sc.nextInt();

            for(int i=1; i<=n; ++i)
                if(result[i] == null) result[i] = new ArrayList<>();
                else result[i].clear();

            for(int i=0; i<m; ++i){
                int q = sc.nextInt();
                for(int j=0; j<q; ++j){
                    stageIdx[j] = sc.nextInt();
                }
                for(int j=0; j<q; ++j){
                    stageVal[j] = sc.nextInt();
                }

                for(int j=0; j<q; ++j)
                    result[stageIdx[j]].add(new Pair(i+1, stageVal[j]));
            }

            System.out.println(n + " " + m);
            for(int i=1; i<=n; ++i) {
                System.out.print(result[i].size());
                for(int j=0; j<result[i].size(); ++j){
                    System.out.print(" ");
                    System.out.print(result[i].get(j).first);
                }
                System.out.println();
                for(int j=0; j<result[i].size(); ++j){
                    if(j != 0) System.out.print(" ");
                    System.out.print(result[i].get(j).second);
                }
                System.out.println();
            }
        }
    }

    static class Pair{
        int first, second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
}
