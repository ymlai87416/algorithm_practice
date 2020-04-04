package GoogleCodeJam.Y2018.Round1A.A; /**
 * Created by Tom on 9/4/2016.
 */
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(System.in);
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solve(int row, int col, int hcut, int vcut, String[] waffle) {
        int ans = 0;
        boolean debug = false;

        //only 1 possible horizontal cut and 1 possible vertical cut
        int share = (hcut+1)* (vcut+1);

        int[] hpoint = new int[hcut+1];
        int[] vpoint = new int[vcut+1];

        int choco = 0;
        for(int i=0; i<waffle.length; ++i){
            for(int j=0; j<waffle[i].length(); ++j)
                if(waffle[i].charAt(j) == '@')
                    choco++;
        }
        if(choco % share != 0){
            out.println("IMPOSSIBLE");
        }
        else{
            int choco_per_piece = choco/share;
            int choco_per_vcut = choco/(vcut+1);
            int choco_per_hcut = choco/(hcut+1);

            int curv = 0;
            for(int i=0; i<vcut; ++i){
                int choco_cnt = 0;
                while(choco_cnt < choco_per_vcut) {
                    for(int j=0; j<row; ++j)
                        if(waffle[j].charAt(curv) == '@')
                            choco_cnt++;
                    curv+=1;
                }

                if(choco_cnt != choco_per_vcut){
                    out.println("IMPOSSIBLE");
                    return;
                }
                vpoint[i] = curv;
            }
            vpoint[vcut] = col;

            if(debug) {
                System.out.println("debug");
                for (int i = 0; i <= vcut; ++i)
                    System.out.print(vpoint[i] + " ");
            }

            int curh = 0;
            for(int i=0; i<hcut; ++i){
                int choco_cnt = 0;
                while(choco_cnt < choco_per_hcut) {
                    for(int j=0; j<col; ++j)
                        if(waffle[curh].charAt(j) == '@')
                            choco_cnt++;
                    curh+=1;
                }
                if(choco_cnt != choco_per_hcut){
                    out.println("IMPOSSIBLE");
                    return;
                }
                hpoint[i] = curh;
            }

            hpoint[hcut] = row;

            if(debug) {
                System.out.println("debug");
                for (int i = 0; i <= hcut; ++i)
                    System.out.print(hpoint[i] + " ");
            }

            for(int i=0; i<=hcut; ++i){
                for(int j=0; j<=vcut; ++j){
                    int left = i==0 ? 0: vpoint[i-1];
                    int top = j==0? 0: hpoint[j-1];
                    int right = vpoint[i];
                    int bottom = hpoint[j];

                    int sq_choco = checkSquareChoco(waffle, left, top, right, bottom);
                    if(sq_choco != choco_per_piece) {
                        out.println("IMPOSSIBLE");
                        return;
                    }
                }
            }

            out.println("POSSIBLE");
        }
    }

    private int checkSquareChoco(String[] waffle, int left, int top, int right, int bottom){
        int result = 0;
        for(int i=top; i<bottom; ++i){
            for(int j=left; j<right; ++j){
                if(waffle[i].charAt(j) == '@')
                    ++result;
            }
        }

        return result;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int row  =sc.nextInt();
            int col = sc.nextInt();
            int hcut = sc.nextInt();
            int vcut = sc.nextInt();
            sc.nextLine();

            String[] waffle = new String[row];
            for(int p=0; p<row; ++p){
                waffle[p] = sc.nextLine();
            }

            solve(row, col, hcut, vcut, waffle);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}