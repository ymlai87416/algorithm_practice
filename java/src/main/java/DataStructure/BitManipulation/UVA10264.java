package DataStructure.BitManipulation;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 15/5/2016.
 *
 * Stop at 11:20, resume at 12:00
 */
public class UVA10264 {
    static int[] weight = new int[32769];
    static int[] potency = new int[32769];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNextInt()) break;

            int n = sc.nextInt();

            for(int i=0; i<Math.pow(2, n); ++i){
               weight[i] = sc.nextInt();
            }

            Arrays.fill(potency, 0);

            for(int i=0; i<Math.pow(2, n); ++i){
                //System.out.format("%d %s: ", i, Integer.toString(i, 2));
                for(int j=0; j<n; ++j){
                    int ti = 1 << j;
                    int v = i  ^ ti;
                    potency[i] += weight[v];
                    //System.out.format("(%d %s) ", v, Integer.toString(v, 2));
                }
                //System.out.println();
            }

            int max = Integer.MIN_VALUE;
            for(int i=0; i<Math.pow(2, n); ++i){
                for(int j=0; j<n; ++j){
                    int ti = 1 << j;
                    int v = i  ^ ti;
                    int tt = potency[i] + potency[v];
                    if(max < tt) max = tt;
                }
            }

            System.out.println(max);
        }
    }

    static boolean IsPowerOfTwo(int x)
    {
        return (x != 0) && ((x & (x - 1)) == 0);
    }
}
