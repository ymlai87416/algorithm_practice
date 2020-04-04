package Graph.AllPairShortestPathVariants;

import java.util.Scanner;

/**
 * Created by Tom on 13/5/2016.
 */
public class UVA104 {

    static double[][][] AdjMat = new double[21][21][21];
    static int[][] p = new int[21][21];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNextInt()) break;

            int V = sc.nextInt();

            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    p[i][j] = i;


            for(int i=0; i<V; ++i){
                for(int j=0; j<V; ++j){
                    if(i==j)
                        AdjMat[i][j][1] = 1;
                    else
                        AdjMat[i][j][1] = sc.nextDouble();
                }
            }

            for (int k = 0; k < V; k++) // remember that loop order is k->i->j
                for (int i = 0; i < V; i++)
                    for (int j = 0; j < V; j++) {
                        if (AdjMat[i][k][k-1] * AdjMat[k][j][1] > AdjMat[i][j][k-1]) {
                            AdjMat[i][j][k] = AdjMat[i][k][k-1] * AdjMat[k][j][1];
                            p[i][j] = p[k][j];
                        }
                    }

            int maxR = 1;
            int arb = -1;
            for(int i=0; i<V; ++i) {
                if(maxR > p[i][i]){
                    maxR = p[i][i];
                    arb = i;
                }
            }

            for(int i=0; i<V; ++i){
                for(int j=0; j<V; ++j){
                    System.out.print(AdjMat[i][j]+ " ");
                }
                System.out.println();
            }

            if(arb == -1)
                System.out.println("no arbitrage sequence exists");
            else {
                printPath(arb, arb);
                System.out.println();
            }
        }

    }

    static void printPath(int i, int j) {
        if (i != j) printPath(i, p[i][j]);
        System.out.format(" %d", j+1);
    }
}
