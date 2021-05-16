package GoogleCodeJam.Y2021.Round1C.C;

import java.io.File;
import java.io.PipedReader;
import java.io.PrintStream;
import java.util.*;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Round1C\\C\\C-test.in";
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

    boolean debugflag = true;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    private int solve(String a, String b) {
        int ans = 0;

        return ans;
    }

    int LIMIT = (int)Math.pow(2, 30);
    //int[] visited = new int[LIMIT];
    HashMap<Integer, Integer> visited = new HashMap<>();

    private int solveS(String a, String b){

        Queue<Integer> queue = new ArrayDeque<>();

        int ia  = Integer.parseInt(a, 2);
        int ib = Integer.parseInt(b, 2);
        if(ia==ib) return 0;

        visited.clear();
        visited.put(ia, 0);

        queue.add(ia);

        while(!queue.isEmpty()){
            int u = queue.poll();
            int d = visited.get(u);

            int notu = ~u & getU(u);
            int u2 = u*2;

            if(notu < LIMIT && notu >= 0) {
                if (!visited.containsKey(notu)) {
                    queue.add(notu);
                    visited.put(notu, d+1);
                }
            }

            if(u2 < LIMIT) {
                if (!visited.containsKey(u2)) {
                    queue.add(u2);
                    visited.put(u2, d+1);
                }
            }

            /*
            if(u2 ==ib || notu==ib)
                break;

             */
        }

        return visited.containsKey(ib) ? visited.get(ib): -1;

    }

    private int getU(int u){

        if(u == 0) return 1;
        int i= 1;
        while(i<= u){
            i *=2;
        }

        return i-1;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(true){

            //return;

            /*
            Random r = new Random();

            debug(Integer.toBinaryString(0), Integer.valueOf(Integer.toBinaryString(~0 & getU(0))));

            for (int i = 0; i < 100; i++) {
                int tt = r.nextInt(256);
                String stt = String.valueOf(tt);
                debug(Integer.toBinaryString(tt), Integer.valueOf(Integer.toBinaryString(~tt & getU(tt))));
            }

             */
        }

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String a = sc.nextLine();
            String[] tt = a.split(" ");
            int r = solveS(tt[0], tt[1]);
            if(r==-1) out.println("IMPOSSIBLE");
            else out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
