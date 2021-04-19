package Graph.AllPairShortestPathVariants;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 problem: https://onlinejudge.org/external/1/104.pdf
 level:
 solution: AC

 #AllPairShortestPath

 **/

public class UVA104 {

    static double[][] convTable = new double[21][21];
    //static int[][] next = new int[21][21];

    static double[][] prevS = new double[21][21];
    static double[][] curS = new double[21][21];
    static int[][][] path = new int[21][21][21];
    //denote the path from start to end next[start][end] = next element -> start, next, ... , end


    public static void main(String[] args){
        Scanner sc = null;
        try{
            //sc = new Scanner(System.in);
            sc = new Scanner(new FileInputStream("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Graph\\AllPairShortestPathVariants\\UVA104.in"));
        }
        catch(Exception ex){}


        while(true){
            if(!sc.hasNextInt()) break;

            int V = sc.nextInt();

            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    path[i][j][0] = j;

            for(int i=0; i<V; ++i){
                for(int j=0; j<V; ++j){
                    if(i==j)
                        convTable[i][j] = 1;
                    else
                        convTable[i][j]= sc.nextDouble();
                }
            }

            //https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm#Path_reconstruction
            //floyd can make path no longer than

            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    prevS[i][j] = convTable[i][j];
                }
            }

            boolean found = false;

            for (int i = 0; i < V&&!found; i++) {
                for (int j = 0; j < V &&!found; j++) {
                    //System.out.println(i + " " + j + " " + convTable[i][j] * convTable[j][i]);
                    if(convTable[i][j] * convTable[j][i] > 1.01){
                        //now we get the answer
                        System.out.format("%d %d %d\n", i+1, j+1, i+1);
                        found=true;
                    }
                }
            }

            for (int t = 1; t < V && !found; t++) {

                for (int i = 0; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        curS[i][j] = prevS[i][j];

                        for (int k = 0; k < V; k++) {
                            double temp = prevS[i][k] * convTable[k][j];
                            if(temp > curS[i][j] ) {
                                curS[i][j] = temp;
                                //now i .. j path => i....k-> j.
                                //copy the path from i to k and then add j

                                path[i][j][t]=k;
                            }
                        }
                    }
                }

                for (int i = 0; i < V; i++) {
                    if(curS[i][i] > 1.01){
                        //now we get the answer
                        printPath(t, i, i);
                        System.out.println();
                        found=true;
                        break;
                    }
                }

                double[][] temp = curS;
                curS = prevS;
                prevS = temp;

            }

            if(!found)
                System.out.println("no arbitrage sequence exists");


        }

    }


    /*
        Here is the path logic
        original: path[i][j][0] == i=>j
        if I find a path which i... k -> j is bigger than i... j at step S then i assigned path[i][j][S] = k
        so i have to print the path from 1...k for step S-1 then print j
     */
    static void printPath(int t, int i, int j) {
        if(t == 0) {
            System.out.format("%d %d", i + 1, j + 1);
            return;
        }
        printPath(t-1, i, path[i][j][t]);
        System.out.format(" %d", j+1);
    }

}
