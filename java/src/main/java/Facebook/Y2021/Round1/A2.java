package Facebook.Y2021.Round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class A2 {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\A2-test.in";
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\weak_typing_chapter_2_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\weak_typing_chapter_2_output.txt";
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

    private long solve2(int N, String s) {
        //do a recursive case here, but 800k just corrupt the stack

        long Gi =0, Pi = 0, Gi_1=0, Pi_1=0;
        int lastX=N+1, lastO=N+1;
        char[] cc = s.toCharArray();
        //base case Gi=0 and Pi = 0 for the last character
        if(cc[N-1] == 'X') lastX = N-1;
        if(cc[N-1] == 'O') lastO = N-1;

        //check string s(i ... N)
        for (int i = N-2; i >=0 ; i--) {
            int delta = 0;
            if(cc[i] == 'X'){
                if(lastO != -1 && lastO < lastX)
                    delta = N - lastO;
                lastX = i;
            }
            else if(cc[i] == 'O'){
                if(lastX != -1 && lastX < lastO)
                    delta = N - lastX;
                lastO = i;
            }

            Pi = Pi_1 + delta;



            //now we have Pi, we can calc Gi
            Gi = (Pi + Gi_1 + 0) % 1000000007;
            //debug(s.substring(i) + ":  " + Gi);

            //now move Gi and Pi
            Gi_1 = Gi;
            Pi_1 = Pi;
        }

        return Gi;
    }

    //solve naive
    private long solveN(int N, String s) {
        char[] c = s.toCharArray();
        long sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j <= N; j++) {
                sum = (sum + solveA1(j-i, c, i, j)) %  1000000007;
            }
        }
        return sum;
    }

    private int solveA1(int N, char[] cc, int s, int e) {
        int st = 0; //undetermine = 0, 1 = X and 2 = O
        int result= 0;
        for (int i = s; i < e; i++) {
            if(cc[i] == 'O'){
                if(st == 1){
                    st = 2;
                    result+=1;
                }
                else if(st == 0){
                    st = 2;
                }
            }
            else if(cc[i] == 'X'){
                if(st == 2){
                    st = 1;
                    result+=1;
                }
                else if(st == 0){
                    st = 1;
                }
            }
        }
        return result;
    }

    private void generateTestcase() throws Exception{
        FileWriter myWriter = new FileWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\A2_gen.txt");
        char[] c = "FXO".toCharArray();
        char[] x = new char[800000];
        myWriter.write("20\n");

        //all X => 0
        myWriter.write("800000\n");
        for (int i = 0; i < 800000; i++) {
            x[i] = 'X';
        }
        myWriter.write(new String(x) + "\n");

        //all X => 0
        myWriter.write("800000\n");
        for (int i = 0; i < 800000; i++) {
            x[i] = 'O';
        }
        myWriter.write(new String(x) + "\n");


        //X and O interbetween => 799999
        myWriter.write("800000\n");
        for (int i = 0; i < 800000; i++) {
            x[i] = i%2==0 ? 'X' : 'O';
        }
        myWriter.write(new String(x) + "\n");

        //all 799999 F with X
        myWriter.write("800000\n");
        for (int i = 0; i < 800000; i++) {
            x[i] = i<799999 ? 'F' : 'X';
        }
        myWriter.write(new String(x) + "\n");

        //all 799999 F with O
        myWriter.write("800000\n");
        for (int i = 0; i < 800000; i++) {
            x[i] = i<799999 ? 'F' : 'O';
        }
        myWriter.write(new String(x) + "\n");

        //all F
        myWriter.write("800000\n");
        for (int i = 0; i < 800000; i++) {
            x[i] = 'F';
        }
        myWriter.write(new String(x) + "\n");

        for (int i = 0; i < 14; i++) {
            myWriter.write("800000\n");
            for (int j = 0; j < 800000; j++) {
                int random = (int) (Math.random()*3);
                x[j] = c[random];
            }

            myWriter.write(new String(x) + "\n");
        }

        myWriter.close();
    }

    private void generateTestcase2() throws Exception{
        FileWriter myWriter = new FileWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\A2_gen2.txt");
        char[] c = "FXO".toCharArray();
        char[] x = new char[10000];
        myWriter.write("10\n");

        //all X => 0
        myWriter.write("10000\n");
        for (int i = 0; i < 10000; i++) {
            x[i] = 'X';
        }
        myWriter.write(new String(x) + "\n");

        //all X => 0
        myWriter.write("10000\n");
        for (int i = 0; i < 10000; i++) {
            x[i] = 'O';
        }
        myWriter.write(new String(x) + "\n");


        //X and O interbetween => 799999
        myWriter.write("10000\n");
        for (int i = 0; i < 10000; i++) {
            x[i] = i%2==0 ? 'X' : 'O';
        }
        myWriter.write(new String(x) + "\n");

        //all 799999 F with X
        myWriter.write("10000\n");
        for (int i = 0; i < 10000; i++) {
            x[i] = i<99999 ? 'F' : 'X';
        }
        myWriter.write(new String(x) + "\n");

        //all 799999 F with O
        myWriter.write("10000\n");
        for (int i = 0; i < 10000; i++) {
            x[i] = i<99999 ? 'F' : 'O';
        }
        myWriter.write(new String(x) + "\n");

        //all F
        myWriter.write("10000\n");
        for (int i = 0; i < 10000; i++) {
            x[i] = 'F';
        }
        myWriter.write(new String(x) + "\n");

        for (int i = 0; i < 4; i++) {
            myWriter.write("10000\n");
            for (int j = 0; j < 10000; j++) {
                int random = (int) (Math.random()*3);
                x[j] = c[random];
            }

            myWriter.write(new String(x) + "\n");
        }

        myWriter.close();
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();

            long r = solve2(N, s);
            //long r = solveN(N,s);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        //new A2().generateTestcase2();
        new A2().run();
    }

}
