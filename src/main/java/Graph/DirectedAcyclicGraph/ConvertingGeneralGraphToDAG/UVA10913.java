package Graph.DirectedAcyclicGraph.ConvertingGeneralGraphToDAG;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by ymlai on 14/4/2017.
 */
public class UVA10913 {
    static int[][] m = new int[76][76];

    static long[][][] dp = new long[76][76][6];
    static int n;
    static int k;
    static long IMPOSSIBLE = Long.MIN_VALUE;
    static long NOTVISITED = Long.MIN_VALUE+1;

    static long dpFunc(int x, int y, int kLeft){
        if(dp[x][y][kLeft] != NOTVISITED)
            return dp[x][y][kLeft];

        long result = IMPOSSIBLE;

        long t;

        int acc = m[x][y];
        int kk = kLeft;
        if(m[x][y] < 0) kk--;

        if(kk < 0){
            dp[x][y][kLeft] = IMPOSSIBLE;
            return IMPOSSIBLE;
        }

        for(int i=kk; i>=0; --i){
            t = capAdd(dpFunc(x+1, y, i),m[x][y]);
            result = Math.max(result, t);
        }

        int tacc = acc;
        int tkk = kk;
        if(y-1 >=0)
            for(int i=y-1; i>=0; --i){
                tacc += m[x][i];
                if(m[x][i] < 0) tkk--;

                if(tkk >=0)
                    for(int j=tkk; j>=0; --j){
                        t = capAdd(dpFunc(x+1, i, j), tacc);
                        result = Math.max(result, t);
                    }
            }

        tacc = acc;
        tkk = kk;

        if(y+1 < n)
            for(int i=y+1; i<n; ++i){
                tacc += m[x][i];
                if(m[x][i] < 0) --tkk;

                if(tkk >=0)
                    for(int j=tkk; j>=0; --j){
                        t = capAdd(dpFunc(x+1, i, j),tacc);
                        result = Math.max(result, t);
                    }
            }

        dp[x][y][kLeft] = result;
        return result;
    }

    static long capAdd(long a, long b){
        if(a == IMPOSSIBLE || b == IMPOSSIBLE)
            return IMPOSSIBLE;
        else
            return a+b;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int nc = 0;
        while(true){
            n = sc.nextInt();
            k = sc.nextInt();

            if(n==0 && k==0)
                break;
            ++nc;

            for(int i=0; i<n; ++i){
                for(int j=0; j<n; ++j)
                    m[i][j] = sc.nextInt();
            }

            for(int i=0; i<n; ++i)
                for(int j=0; j<n; ++j)
                    Arrays.fill(dp[i][j], NOTVISITED);

            for(int j=0; j<n; ++j)
                Arrays.fill(dp[n-1][j], IMPOSSIBLE);

            int acc = 0;
            int kk = 0;
            for(int i=n-1; i>=0; --i){
                if(m[n-1][i] < 0) ++kk;
                acc += m[n-1][i];
                if(kk > k) break;
                dp[n-1][i][kk] = acc;
            }

            long result = IMPOSSIBLE;

            for(int i=k; i>=0; --i)
                result = Math.max(result, dpFunc(0, 0, i));

            if(result == IMPOSSIBLE)
                System.out.format("Case %d: impossible\n", nc);
            else
                System.out.format("Case %d: %d\n", nc, result);
        }
    }
}
