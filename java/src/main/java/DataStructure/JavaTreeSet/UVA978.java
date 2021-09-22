package DataStructure.JavaTreeSet;

import java.util.*;

/**
 * Created by Tom on 17/4/2016.
 * Start at  17:15, got TLE at 17:36 and rewrite and get wrong answer at 17:54, finished at 18:13, total 58mins for a simple question...
 *
 * problem: https://onlinejudge.org/external/9/978.pdf
 *
 * #Lv3 #UVA #bst #tree_map
 */
public class UVA978 {

    public static int cntLeft(int[] army){
        int sum = 0;
        for(int i=0; i<army.length; ++i)
            sum += army[i];
        return sum;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        for(int i=0; i<a; ++i){
            int nbf = sc.nextInt();
            int sg = sc.nextInt();
            int sb = sc.nextInt();

            int[] green = new int[102];
            Arrays.fill(green, 0);
            int[] blue = new int[102];
            Arrays.fill(blue, 0);
            for(int j=0; j<sg; ++j){
                int c = sc.nextInt();
                green[c]++;
            }

            for(int j=0; j<sb; ++j){
                int c = sc.nextInt();
                blue[c] ++ ;
            }

            while(true){
                int bluecnt  =cntLeft(blue);
                int greencnt = cntLeft(green);
                if(bluecnt == 0 || greencnt == 0) break;
                List<Integer> blueRecovery = new ArrayList<Integer>();
                List<Integer> greenRecovery = new ArrayList<Integer>();
                for(int j=0; j<nbf && j<bluecnt && j<greencnt; ++j){
                    int gs = 0;
                    int bs = 0;
                    for(int k=blue.length-1; k>=0; --k) {
                        if(blue[k] > 0) {
                            bs = k;
                            blue[k]--;
                            break;
                        }
                    }

                    for(int k=green.length-1; k>=0; --k) {
                        if(green[k] > 0) {
                            gs = k;
                            green[k]--;
                            break;
                        }
                    }


                    if(bs == gs){
                    }
                    else if(bs > gs){
                        blueRecovery.add(bs-gs);
                    }
                    else if(gs > bs){
                        greenRecovery.add(gs-bs);
                    }
                }

                for(int k=0; k<blueRecovery.size(); ++k)
                    blue[blueRecovery.get(k)]++;
                for(int k=0; k<greenRecovery.size(); ++k)
                    green[greenRecovery.get(k)]++;
            }

            int bluecnt  =cntLeft(blue);
            int greencnt = cntLeft(green);

            if(bluecnt == 0 &&greencnt == 0)
                System.out.println("green and blue died");
            else if(bluecnt == 0) {
                System.out.println("green wins");
                for(int j=green.length-1; j>=0; --j){
                    for(int k=0; k<green[j]; ++k)
                        System.out.println(j);
                }
            }
            else {
                System.out.println("blue wins");
                for(int j=blue.length-1; j>=0; --j){
                    for(int k=0; k<blue[j]; ++k)
                        System.out.println(j);
                }
            }
            if(i != a-1) System.out.println();
        }
    }
}
