package GoogleCodeJam.Y2019.Round1C.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2019\\Round1C\\A\\A-test.in";
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

    boolean[] elim = new boolean[256];
    boolean[] backup= new boolean[256];
    int A;
    String[] C;
    private String solve(int A, String[] c){
        //this robot has to win every robot, if I win this robot, i don't need to consider it next round
        this.A = A;
        this.C = c;
        for (int i = 0; i < A; i++) {
            elim[i] = false;
        }

        return helper(0, "");
    }

    private char getResult(String cc, int step){
        step = step % cc.length();
        return cc.charAt(step);
    }

    private String helper(int step, String prog){
        boolean[] RSP = new boolean[3];
        String idx = "RSP";
        for (int i = 0; i < 3; i++) {
            RSP[i] = false;
        }
        for (int i = 0; i < A; i++) {
            if(!elim[i]){
                char c =getResult(C[i], step);
                RSP[idx.indexOf(c)] = true;
            }
        }

        if(!RSP[0] && !RSP[1] && !RSP[2]) return prog;

        String t;

        //if have RSP, then impossible
        if(RSP[0] &&  RSP[1] && RSP[2]) return null;
        else if(RSP[0] &&  RSP[1] && !RSP[2]){
            //rock and scissor, do rock
            elim(step, 'S');
            t = helper(step+1, prog+"R");
            return t;
        }
        else if(RSP[0] &&  !RSP[1] && RSP[2]){
            //rock and paper, do paper
            //do paper
            elim(step, 'R');
            t = helper(step+1, prog+"P");
            return t;
        }
        else if(!RSP[0] &&  RSP[1] && RSP[2]){
            //scissor and paper, do scissor
            //do Scissors
            elim(step, 'P');
            t = helper(step+1, prog+"S");
            return t;
        }
        else if(RSP[0] &&  !RSP[1] && !RSP[2]){
            return prog + "P";
        }
        else if(!RSP[0] &&  RSP[1] && !RSP[2]){
            return prog+"R";
        }
        else if(!RSP[0] &&  !RSP[1] && RSP[2]){
            return prog+"S";
        }
        //if have one only, then return success
        //else try both and see see
        return null;
    }

    private void elim(int step, char c){
        for (int i = 0; i < A; i++) {
            if(!elim[i] && getResult(C[i], step) == c)
                elim[i] = true;
        }
    }

    private boolean verify(String prog, int A, String[] C){
        boolean[] elim = new boolean[A];
        int step = 0;
        while(true){
            int cnt = 0;
            //every round eliminate 1
            char c = getResult(prog, step);
            char w = c == 'R' ? 'P' : (c == 'S' ? 'R' : 'S');
            for (int i = 0; i < A; i++) {
                if(!elim[i]){
                    char o = getResult(C[i], step);
                    if(o == w) return false;
                    if(o != c) cnt++;
                    elim[i] = true;
                }
            }

            if(cnt == 0) break;
            step++;
        }
        return true;
    }


    private void run() throws Exception {

        if(false){
            for (int k = 0; k < 100; k++) {
                int A=7;
                Random r = new Random();
                String d = "RSP";
                String[] C = new String[A];
                for (int i = 0; i < A; i++) {
                    int rlen = r.nextInt(5) + 1;
                    for (int j = 0; j < rlen; j++) {
                        char dd = d.charAt(r.nextInt(3));
                        if(C[i] == null)
                        C[i] = "" + dd;
                        else C[i] = C[i] + dd;
                    }
                }

                try {
                    String prog = solve(A, C);

                    if(prog != null && !verify(prog, A, C)){
                        debug("Answer: " + prog);
                        debug("" + A);
                        for (int i = 0; i < A; i++) {
                            debug(C[i]);
                        }
                    }
                }catch(Exception ex){
                    debug("" + A);
                    for (int i = 0; i < A; i++) {
                        debug(C[i]);
                    }
                }
            }

            return;
        }

        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int A = sc.nextInt();
            sc.nextLine();
            String[] prog = new String[A];
            for (int j = 0; j < A; j++) {
                prog[j] = sc.nextLine();
            }
            String rr = solve(A, prog);
            if(rr == null) System.out.println("IMPOSSIBLE");
            else System.out.println(rr);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
