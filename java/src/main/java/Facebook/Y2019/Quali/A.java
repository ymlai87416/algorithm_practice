package Facebook.Y2019.Quali;

import Template.Solution;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Quali\\leapfrog_ch_1_input.txt";
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Quali\\leapfrog_ch_1_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2019\\Quali\\leapfrog_ch_1_output.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = false;
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

    private char solve(char[] s) {
        int bcnt= 0;
        for (int i = 0; i < s.length; i++) {
            if(s[i] == 'B') bcnt+=1;
        }

        if(s.length==1) return 'Y';

        int aptr= 0;
        while(aptr!=s.length-1){
            //System.out.println(s);
            if(bcnt == 0) return 'N';
            //base case, ABBBBBB.BBB, with one space left, move B and jump
            if(s.length-aptr == bcnt+2 && bcnt > 0) return 'Y';

            //check if it have adj B, and how far it can reach
            int bptr;
            int kcnt = 0;
            //this may consume too many frog at once
            for (bptr = aptr+1; bptr < s.length; bptr++) {
                if(s[bptr] != 'B') break;
                kcnt+=1;
            }
            if(bptr == s.length) return 'N'; //no place to jump
            else if(bptr == aptr+1 && bcnt > 0){
                //call a beta and jump
                bcnt-=1;
                for (int i = aptr+1; i < s.length; i++) {
                    if(s[i] == 'B'){
                        s[i] = '.';
                        break;
                    }
                }
                aptr = aptr+2;
                s[aptr] = 'A';

            }
            else{
                //aptr = bptr;
                bcnt -= 1;
                aptr= aptr+2;
                s[aptr] = 'A';
                s[bptr] = 'B';

            }
        }

        return 'N';
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String s = sc.nextLine();
            char r = solve(s.toCharArray());
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}
