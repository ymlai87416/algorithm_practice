package Introduction.GameOtherEasier;


import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 15/4/2016.
 * Start at 0:18 and AC at 0:33
 *
 * problem: https://onlinejudge.org/external/114/11459.pdf
 * #game #simulation #UVA #Lv3
 */
public class UVA11459 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();

        for(int i=0; i<a; ++i){
            int p = sc.nextInt();
            int l = sc.nextInt();
            int d = sc.nextInt();
            TreeMap<Integer, Integer> sl = new TreeMap<Integer, Integer>();
            for(int j=0; j<l; ++j)
                sl.put(sc.nextInt(), sc.nextInt());

            boolean win = false;
            int[] pos = new int[p];
            Arrays.fill(pos, 1);
            int player = 0;
            for(int j=0; j<d ; ++j){
                int mv =sc.nextInt();
                if(win) continue;

                int newp = pos[player]+mv;
                while(sl.containsKey(newp))
                    newp = sl.get(newp);

                pos[player] = newp;
                if(pos[player] == 100) win = true;
                player = (player+1) % p;
            }

            for(int j=0; j<p; ++j){
                System.out.format("Position of player %d is %d.\n", j+1, pos[j]);
            }
        }
    }
}
