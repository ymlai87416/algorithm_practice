package DataStructure.GraphDataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 15/5/2016.
 *
 *   TLE, try BufferedInput and output. cannot
 */
public class UVA11991 {
    static ArrayList<Integer>[] input = new ArrayList[1000010];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            //TreeMap<Integer, ArrayList<Integer>> input = new TreeMap<>();

            int n = sc.nextInt();
            int m = sc.nextInt();
            Arrays.fill(input, null);

            for(int i=0; i<n; ++i){
                int s = sc.nextInt();
                ArrayList<Integer> alist = input[s];
                if(alist  == null){
                    input[s] = new ArrayList<Integer>();
                    input[s].add(i+1);
                } else
                    alist.add(i+1);
            }

            ArrayList<Integer> alist;
            for(int i=0; i<m; ++i){
                int k = sc.nextInt();
                int v = sc.nextInt();

                alist = input[v];
                if(alist == null)
                    System.out.println(0);
                else if(alist.size() < k)
                    System.out.println(0);
                else
                    System.out.println(alist.get(k-1));
            }
        }
    }
}
