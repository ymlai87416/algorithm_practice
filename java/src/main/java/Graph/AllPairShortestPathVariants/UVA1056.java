package Graph.AllPairShortestPathVariants;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 problem: https://onlinejudge.org/external/10/1056.pdf
 level:
 solution:

 #all_pair_shortest_path #Lv3 #UVA

 **/
public class UVA1056 {

    static int[][] AdjMat = new int[101][101];
    static int INF = Integer.MAX_VALUE;

    //the answer is ok, only if consider a case where there are 5 people and all of them are disconnected.

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int ci = 0;
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a == 0 && b == 0) break;

            TreeMap<String, Integer> mapper = new TreeMap<String, Integer>();
            int V=0;

            for(int i=0; i<a; ++i) {
                Arrays.fill(AdjMat[i], INF);
                AdjMat[i][i] = 0;
            }

            for(int i=0; i<b; ++i){
                String as = sc.next();
                String bs = sc.next();

                Integer aidx = mapper.get(as); if(aidx == null) mapper.put(as, V++);
                Integer bidx = mapper.get(bs); if(bidx == null) mapper.put(bs, V++);

                aidx = mapper.get(as);
                bidx = mapper.get(bs);

                AdjMat[aidx][bidx] = 1;
                AdjMat[bidx][aidx] = 1;
            }




            // inside int main()
            // precondition: AdjMat[i][j] contains the weight of edge (i, j)
            // or INF (1B) if there is no such edge
            // AdjMat is a 32-bit signed integer array
            for (int k = 0; k < a; k++) // remember that loop order is k->i->j
                for (int i = 0; i < a; i++)
                    for (int j = 0; j < a; j++)
                        AdjMat[i][j] = Math.min(AdjMat[i][j], capAdd(AdjMat[i][k], AdjMat[k][j]));


            int maxdist = Integer.MIN_VALUE;
            for(int i=0; i<a; ++i){
                for(int j=0; j<a; ++j){
                    maxdist = Math.max(maxdist, AdjMat[i][j]);
                }
            }

            if(maxdist == Integer.MAX_VALUE || maxdist == Integer.MIN_VALUE)
                System.out.format("Network %d: DISCONNECTED\n", ++ci);
            else
                System.out.format("Network %d: %d\n", ++ci, maxdist);
            System.out.println();

        }


    }
    static int capAdd(int a, int b){
        if(a  == INF || b == INF) return INF;
        return a+b;
    }

}
