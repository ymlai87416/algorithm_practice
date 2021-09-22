package MoreAdvanceTopic.ChallengingBacktrackProblem;

import java.util.Scanner;

/**
 * Created by Tom on 12/3/2017.
 *
 * problem: https://onlinejudge.org/external/111/11195.pdf
 * #UVA #Lv3 #backtracking
 */
public class UVA11195 {

    static int ans;
    static int[] map = new int[16];
    static int nrow;
    static int OK;

    public static void backtrack(int rw, int ld, int rd, int level){
        //System.out.println("backtrack " + rw + " " + ld + " " + rd + " " + level);
        if(level == nrow){
            ans++;
            return;
        }
        int pos = OK & (~(rw | ld | rd | map[level]));

        while(pos != 0){
            int p = pos & -pos;  //extract the last 1 bit count from the right.
            pos -= p;

            backtrack(rw | p, (ld|p) << 1, (rd | p) >> 1, level+1);
        }
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int cnt = 0;
        while(true){
            cnt+= 1;
            nrow = sc.nextInt();
            if(nrow == 0) break;

            ans = 0;

            sc.nextLine();
            OK = 0;
            for(int i=0; i<nrow; ++i){
                OK = (OK << 1)  + 1;
                String line =sc.nextLine();
                map[i] = 0;
                for(int j=0; j<nrow; ++j)
                    if(line.charAt(j) == '.')
                        map[i] = map[i] << 1;
                    else
                        map[i] = (map[i] << 1) + 1;
            }

            backtrack(0, 0, 0, 0);

            System.out.println(String.format("Case %d: %d", cnt, ans));
        }

    }
}
