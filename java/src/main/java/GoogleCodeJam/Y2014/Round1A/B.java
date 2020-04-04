package GoogleCodeJam.Y2014.Round1A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by Tom on 14/3/2017.
 * 1:37 to 2:47
 *
 * flaw in implementation which cause 1 small data set error.
 */
public class B {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            FILENAME = "B-large-practice";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    int ans;
    int v;
    static int[][] adjMatrix = new int[1005][1005];
    static int[] adjCnt = new int[1005];
    static TreeMap<Pair, Integer> dp;
    static TreeMap<Pair, Integer> dp2;

    private void solve() {
        if(v == 1){ ans=0; return; }
        if(v == 2){ ans=1; return;}
        if(v == 3){ ans=0; return; }

        dp = new TreeMap<Pair, Integer>();
        dp2 = new TreeMap<Pair, Integer>();
        ans = Integer.MAX_VALUE;

        for(int i=1; i<=v; ++i){
            if(adjCnt[i] >= 2){
                //make it a root
                int rm = root(0, i);
                ans = Math.min(ans, rm);
            }
        }
    }

    static final int INF=  2000;

    private int root(int from, int to){
        Pair p = new Pair(from, to);
        if(dp2.containsKey(p)) return dp2.get(p);

        int ob = 0;
        if(from == 0) ob = adjCnt[to];
        else ob = adjCnt[to]-1;

        int deleteCnt = 0;
        switch(ob){
            case 0:
                break;
            case 1: //remove this node
                for(int i=0; i<adjCnt[to]; ++i){
                    int v = adjMatrix[to][i];
                    if(v == from) continue;
                    deleteCnt = remove(to, v);
                }

                break;
            case 2: //so far so good
                for(int i=0; i<adjCnt[to]; ++i){
                    int v = adjMatrix[to][i];
                    if(v == from) continue;
                    deleteCnt += root(to, v);
                }

                break;
            default:
                //choose 2 children which have many grandchildren;
                int[] removeCnt = new int[adjCnt[to]];
                int[] traverseCnt = new int[adjCnt[to]];

                int skipIdx = -1;
                for(int i=0; i<adjCnt[to]; ++i){
                    int v = adjMatrix[to][i];
                    if(v  == from) {
                        removeCnt[i] = INF;
                        skipIdx = i;
                    }
                    else{
                        int dcnt = remove(to, v);
                        removeCnt[i] = dcnt;
                    }
                }

                for(int i=0; i<adjCnt[to]; ++i){
                    int v = adjMatrix[to][i];
                    if(v  == from) traverseCnt[i] = INF;
                    else{
                        int dcnt = root(to, v);
                        traverseCnt[i] = dcnt;
                    }
                }

                int min = Integer.MAX_VALUE;
                for(int i=0; i<adjCnt[to]; ++i){
                    if(i == skipIdx) continue;
                    for(int j=i+1; j<adjCnt[to]; ++j){
                        if(j == skipIdx) continue;
                        //select i,j
                        int temp = 0;
                        for(int k=0; k<adjCnt[to]; ++k){
                            if(k == skipIdx) continue;
                            if(k == i || k == j)
                                temp += traverseCnt[k];
                            else
                                temp += removeCnt[k];
                        }

                        min = Math.min(min, temp);
                    }
                }

                deleteCnt = min;
                break;
        }

        dp2.put(p, deleteCnt);

        return deleteCnt;
    }

    private int remove(int a, int b){
        Pair p = new Pair(a, b);
        if(dp.containsKey(p)) return dp.get(p);

        int result = dfs(b, a);
        dp.put(p, result);
        return result;
    }

    private int dfs(int node, int banned){
        if(adjCnt[node] == 0) return 1;

        int deleteCnt = 0;
        for(int i=0; i<adjCnt[node]; ++i){
            int v = adjMatrix[node][i];
            if(v == banned)
                continue;
            else
                deleteCnt +=  dfs(v, node);
        }
        return deleteCnt +1;
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            Arrays.fill(adjCnt, 0);

            v= sc.nextInt();
            for(int j=0; j<v-1; ++j){
                int a, b;
                a = sc.nextInt();
                b = sc.nextInt();

                adjMatrix[a][adjCnt[a]++]=b;
                adjMatrix[b][adjCnt[b]++]=a;
            }

            solve();

            out.println(ans);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new B().run();
    }
}

class Pair implements Comparable<Pair>{
    int a;
    int b;
    public Pair(int a, int b){
        this.a = a; this.b = b;
    }


    @Override
    public int compareTo(Pair o) {
        if(a < o.a)
            return -1;
        else if(a > o.a)
            return 1;
        else{
            if(b < o.b)
                return -1;
            else if(b > o.b)
                return 1;
            else
                return 0;
        }
    }
}
