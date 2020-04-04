package Introduction.GameCard;

import java.util.Scanner;

/**
 * Created by Tom on 14/4/2016.
 * Start at 23: 39, WA because forget the card has 52, AC at 0:08, total time 29 mins
 */
public class UVA12247 {
    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);

        while(true){
            int[] s = new int[3];
            int[] p = new int[2];
            s[0] = sc.nextInt();
            s[1] = sc.nextInt();
            s[2] = sc.nextInt();
            p[0] = sc.nextInt();
            p[1] = sc.nextInt();
            if(s[0] == 0 && s[1] == 0 && s[2] == 0 &&
                    p[0] == 0 && p[1] == 0 ) break;
            int r=sim(s, p, 0, 0);

            while(r!=-1){
                boolean found = false;
                for(int i=0; i<3; ++i){
                    if(s[i] == r) found= true;
                }
                for(int i=0; i<2; ++i){
                    if(p[i] == r) found= true;
                }
                if(!found) break;
                ++r;
            }

            System.out.println(r > 52 ? -1 : r);

        }
    }

    public static int sim(int[] s, int[] p, int pos, int lose){
        if(pos==2)
            if(lose == 2) return -1;
            else if(lose==0) return 1;
            else{
                for(int i=0; i<3; ++i) if(s[i] != 0) return s[i]+1;
            }
        int m=0;
        for(int i=0; i<3; ++i){
            int r;
            if(s[i] == 0) continue;
            int reg = s[i];
            if(reg > p[pos]){
                s[i] = 0;
                r= sim(s, p, pos+1, lose+1);
                s[i] =reg;
            } else{
                s[i] = 0;
                r = sim(s, p, pos+1, lose);
                s[i] = reg;
            }
            if(r==-1) return -1;
            if(r > m) m=r;
        }
        return m;
    }
}
