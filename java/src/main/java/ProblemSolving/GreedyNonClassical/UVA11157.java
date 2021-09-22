package ProblemSolving.GreedyNonClassical;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Tom on 8/5/2016.
 *
 * problem: https://onlinejudge.org/external/111/11157.pdf
 * #UVA #Lv3 #greedy
 */
public class UVA11157 {
    static int[] dist = new int[103];
    static boolean[] type = new boolean[103];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();

        for(int ci=0; ci<nc; ++ci){
            int n = sc.nextInt();
            int tdist = sc.nextInt();

            dist[0] = 0;
            type[0] = true;
            dist[n+1] = tdist;
            type[n+1] = true;

            for(int i=1; i<=n; ++i){
                String str = sc.next();

                StringTokenizer st =new StringTokenizer(str, "-");

                String types =st.nextToken();
                if(types.compareTo("B") == 0) type[i] = true;
                else type[i] = false;

                dist[i] = Integer.parseInt(st.nextToken());
            }

            int maxhop = 0;
            int curpos = 0;
            int endpos = 0;
            while(true){
                curpos = endpos;
                if(endpos == n+1) break;
                endpos = curpos+1;
                while(!type[endpos]) endpos++;

                if(endpos == curpos+1){
                    maxhop = Math.max(dist[endpos] - dist[curpos], maxhop);
                    continue;
                }

                int temphop = curpos+1;
                int hop1=dist[temphop] - dist[curpos];
                while(temphop < endpos){
                    int nexthop = Math.min(temphop+2, endpos);
                    hop1 = Math.max(hop1, dist[nexthop] - dist[temphop]);
                    temphop = nexthop;
                }

                temphop = curpos+2;
                hop1 = Math.max(dist[temphop] - dist[curpos], hop1);
                while(temphop < endpos){
                    int nexthop = Math.min(temphop+2, endpos);
                    hop1 = Math.max(hop1, dist[nexthop] - dist[temphop]);
                    temphop = nexthop;
                }

                maxhop = Math.max(maxhop, hop1);
            }

            System.out.format("Case %d: %d\n", ci+1, maxhop);

        }
    }
}
