package GoogleCodeJam.Y2019.Quali.B;

import DataStructure.JavaPriorityQueue.UVA1203;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
            IN = null;
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

    private boolean canDownLater(int r, int col, int row, int[] firstDownCord, int[] lastDownCord){
        for(int i=col+1; i<r; ++i) {
            if (firstDownCord[i] == -1)
                return true;
            else if (row >= lastDownCord[i])
                return true;
            /*
            else
                return false;
                */
        }
        return false;
        /*
        else if (row < firstDownCord[col])
            return false;
        */

    }

    private void solve(int r, String p){
        int[] firstDownCord = new int[r];
        int[] lastDownCord = new int[r];
        TreeMap<Pair, Character> path = new TreeMap<Pair, Character>();

        for(int i=0; i<r; ++i) firstDownCord[i] = -1;
        for(int i=0; i<r; ++i) lastDownCord[i] = -1;

        int cur_x = 0; int cur_y = 0;
        char cur_ps, prev_ps;
        cur_ps = ' ';

        int path_length = (r-1)*2;

        for(int i=0; i<path_length; ++i){
            prev_ps = cur_ps;
            cur_ps = p.charAt(i);
            path.put(new Pair(cur_x, cur_y), cur_ps);
            if(cur_ps == 'E') {
                if(prev_ps =='S')
                    lastDownCord[cur_x] = cur_y;
                cur_x += 1;
            }
            else if(cur_ps == 'S') {
                if(firstDownCord[cur_x] == -1)
                    firstDownCord[cur_x] = cur_y;
                cur_y += 1;
            }
        }

        if(cur_ps == 'S') lastDownCord[cur_x] = cur_y;

        /*
        out.println("debug");
        for(int i=0; i<r; ++i){
            out.println(String.valueOf(i) + ":\t" + String.valueOf(firstDownCord[i]) + "\t" + String.valueOf(lastDownCord[i]));
        }
        out.println("debug");
        */

        cur_x =0; cur_y = 0;
        String result = "";
        for(int i=0; i<path_length; ++i){
            //force move
            //out.println("debug" + cur_x + " " + cur_y);
            Pair cp = new Pair(cur_x, cur_y);
            char pps = ' ';
            if(path.containsKey(cp)){
                pps = path.get(cp);
                //out.println("debug: get" + pps);
            }

            if(pps == 'E'){
                result += 'S';
                cur_y += 1;
            }
            else if(pps == 'S'){
                result += 'E';
                cur_x += 1;
            }
            else if (cur_x+1 == r){
                result += "S";
                cur_y += 1;
            }
            else if (cur_y+1 == r){
                result += "E";
                cur_x += 1;
            }
            else if(canDownLater(r, cur_x, cur_y, firstDownCord, lastDownCord)) {
                result += "E";
                cur_x += 1;
            }
            else{
                //out.println("debug: force down");
                result += "S";
                cur_y += 1;
            }
        }

        out.println(result);
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int r = Integer.parseInt(sc.nextLine());
            String p = sc.nextLine();

            solve(r, p);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}

class Pair implements Comparable<Pair>{
    int x;
    int y;

    public Pair(int x, int y){
        this.x = x; this.y  = y;
    }

    @Override
    public int compareTo(Pair o) {
        if(x < o.x) return -1;
        else if (x > o.x) return 1;
        else {
            if(y < o.y) return -1;
            else if(y > o.y) return 1;
            else return 0;
        }
    }
}
