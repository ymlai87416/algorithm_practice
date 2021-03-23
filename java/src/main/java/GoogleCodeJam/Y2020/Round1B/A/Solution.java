package GoogleCodeJam.Y2020.Round1B.A;

import java.io.BufferedReader;
import java.io.File;
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
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2020\\Round1B\\A\\A-test.in";
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

    private String solve(int x, int y){

        //brute force approach with BFS
        Queue<Object[]> stack= new ArrayDeque<Object[]>();
        stack.offer(new Object[]{0L, 0L, ""});
        while(!stack.isEmpty()){
            Object[] b = stack.poll();
            long tx = (long)b[0];
            long ty = (long)b[1];
            String ss = (String)b[2];
            if(tx == x && ty == y) return ss;

            int step = ss.length();
            if(step > 8) continue;
            long stepSize = (long)Math.pow(2, step);

            stack.offer(new Object[]{tx-stepSize, ty, ss+"W"});
            stack.offer(new Object[]{tx+stepSize, ty, ss+"E"});
            stack.offer(new Object[]{tx, ty-stepSize, ss+"S"});
            stack.offer(new Object[]{tx, ty+stepSize, ss+"N"});
        }
        return "IMPOSSIBLE";
    }


    private String solveBig(int x, int y){
        //System.out.println(x+" " + y + "shit");
        int absx=Math.abs(x); int absy=Math.abs(y);

        if(x==0 && y==1) return "N";
        else if(x==0 && y==-1) return "S";
        else if(x==1 && y==0) return "E";
        else if(x==-1 && y==0) return "W";
        else if(absx%2==0 && absy%2==0) return null;
        else if(absx%2==1 && absy%2==1) return null;
        else if(absx%2==1){

            String east = solveBig((x-1)/2, y/2);
            if(east != null) return "E" + east ;
            else{
                String west = solveBig((x+1)/2, y/2);
                if(west != null) return "W" + west ;
                else return null;
            }

        }
        else{

            String north = solveBig(x/2, (y-1)/2);
            if(north != null) return "N" + north ;
            else{
                String south = solveBig(x/2, (y+1)/2);
                if(south != null) return  "S" + south ;
                else return null;
            }
        }
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            int _t = sc.nextInt();
            int _b = sc.nextInt();
            out.print("Case #" + i + ": ");
            String result = solveBig(_t, _b);
            out.println(result == null ? "IMPOSSIBLE": result);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
