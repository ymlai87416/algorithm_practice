package ProblemSolving.IterativeThreeLoopHarder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 20/4/2016.
 * Start at 23:59 and finished at 0:31, total of 32 minutes. Having difficulties to read the problem... waste my time.
 *
 * problem: https://onlinejudge.org/external/106/10660.pdf
 * #UVA #Lv3 #iterative_search #search_O(n^3)
 */
public class UVA10660 {
    public static int minDist(int c, int[] a, int[] d){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<5; ++i){
            d[i] = Math.abs(c/5 - a[i]/5) + Math.abs(c%5 - a[i]%5);
            if(min > d[i]) min = d[i];
        }
        return min;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[] officeLoc = new int[5];
        int[] minD = new int[5];
        int[] pop = new int[25];

        for(int c=0; c<t; ++c){
            int pt = sc.nextInt();
            Arrays.fill(pop, 0);
            for(int p=0; p<pt; ++p){
                int locx = sc.nextInt();
                int locy = sc.nextInt();
                int value =sc.nextInt();
                pop[locx*5+locy] = value;
            }

            int min = Integer.MAX_VALUE;
            int[] minOff = new int[5];
            for(int i=0; i<25; ++i){
                officeLoc[0]=i;
                for(int j=i+1; j<25; ++j){
                    officeLoc[1]=j;
                    for(int k=j+1; k<25; ++k){
                        officeLoc[2]=k;
                        for(int l=k+1; l<25; ++l){
                            officeLoc[3]=l;
                            for(int m=l+1; m<25; ++m){
                                officeLoc[4]=m;
                                int sum = 0;
                                for(int n=0; n<25; ++n){
                                    sum += pop[n] * minDist(n, officeLoc, minD);
                                }
                                if(sum < min){
                                    min = sum;
                                    for(int cp=0; cp<5; ++cp) {
                                        minOff[0] = i;
                                        minOff[1] = j;
                                        minOff[2] = k;
                                        minOff[3] = l;
                                        minOff[4] = m;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.format("%d %d %d %d %d\n", minOff[0],minOff[1],minOff[2],minOff[3],minOff[4] );
        }



    }
}
