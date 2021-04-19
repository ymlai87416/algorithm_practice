package GoogleCodeJam.Y2010.Round1C.A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Tom on 30/4/2016.
 */
public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2010\\Round1C\\A\\A-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    class Rope{
        int left;
        int right;
        public Rope(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
    private void solve() {
        int ans = 0;
        int rope = sc.nextInt();
        Rope[] ropes = new Rope[rope];
        for(int i=0; i<rope; ++i){
            int left = sc.nextInt();
            int right = sc.nextInt();
            ropes[i] = new Rope(left, right);
        }

        for(int i=0; i<ropes.length; ++i){
            for(int j=0; j<i; ++j){
                if(ropes[i].left > ropes[j].left && ropes[i].right < ropes[j].right ||
                        ropes[i].left < ropes[j].left && ropes[i].right > ropes[j].right)
                    ++ans;
            }
        }

        out.println(ans);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            solve();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
