package ProblemSolving.IterativeThreeLoop;

import java.util.*;

/**
 * Created by Tom on 17/4/2016.
 * Start at 23:19, AC at 0:20, total time 61 mintues... for permutation problem.. combination, maintain a order is ok, for permutation, find tthe distinct elements..
 */
public class UVA735 {

    static TreeSet<Integer> possible = new TreeSet<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        for(int i=1; i<=3; ++i){
            for(int j=1; j<=20; ++j)
                possible.add(i*j);
        }
        possible.add(50);
        possible.add(0);

        //Integer[] debug = possible.toArray(new Integer[possible.size()]);

        //the max score is 60 * 3 = 180;
        int[] combin = new int[181];
        int[] permu = new int[181];

        Arrays.fill(combin, 0);
        Arrays.fill(permu, 0);

        int[] pathtemp = new int[3];
        for(int i=0; i<=180; ++i){
            Arrays.fill(pathtemp, -1);
            List<int[]> result = gen(i, 0, pathtemp);

            combin[i] = result.size();

            for(int j=0; j<result.size(); ++j){
                int[] arr = result.get(j);
                Arrays.sort(arr);
                int distinct=1;
                for(int k=1; k<3; ++k){
                    if(arr[k-1] != arr[k])
                        distinct++;
                }
                if(distinct==1)
                    permu[i] += 1;
                else if(distinct ==2)
                    permu[i] += 3;
                else
                    permu[i] += 6;
            }
        }

        while(true){
            int a = sc.nextInt();
            if(a <= 0) break;

            if(a > 180){
                System.out.format("THE SCORE OF %d CANNOT BE MADE WITH THREE DARTS.\n", a);
            }
            else{
                if(combin[a]  == 0){
                    System.out.format("THE SCORE OF %d CANNOT BE MADE WITH THREE DARTS.\n", a);
                }
                else{
                    System.out.format("NUMBER OF COMBINATIONS THAT SCORES %d IS %d.\n", a, combin[a]);
                    System.out.format("NUMBER OF PERMUTATIONS THAT SCORES %d IS %d.\n", a, permu[a]);
                }
            }
            System.out.println("**********************************************************************");
        }
        System.out.println("END OF OUTPUT");
    }


    public static List<int[]> gen(int a, int dart, int[] path){
        if(dart == 2){
            ArrayList<int[]> result = new ArrayList<int[]>();
            if(possible.contains(a) && a <= path[1]){
                int[] resultx = new int[3];
                resultx[0] = path[0]; resultx[1] = path[1]; resultx[2] = a;
                result.add(resultx); return result;
            }
            else return result;
        }
        else{
            ArrayList<int[]> result=  new ArrayList<int[]>();
            for(int aa : possible) {
                if(aa > a) continue;
                if(dart != 0){
                    if(aa > path[dart-1]) continue;
                }
                path[dart] = aa;
                result.addAll(gen(a-aa, dart + 1, path));
                path[dart] = -1;
            }

            return result;
        }
    }
}
