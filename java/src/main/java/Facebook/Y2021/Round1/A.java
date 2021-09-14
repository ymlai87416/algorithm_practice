package Facebook.Y2021.Round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\A2-test.in";
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\weak_typing_chapter_1_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\weak_typing_chapter_1_output.txt";
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


    private int solve(int N, String s) {
        int st = 0; //undetermine = 0, 1 = X and 2 = O
        int result= 0;
        char[] cc = s.toCharArray();
        for (int i = 0; i < N; i++) {
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


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();

            int r = solve(N, s);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public void generateTestCase() throws Exception{
        //generate
        FileWriter myWriter = new FileWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Round1\\A1_gen.txt");
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

    public static void main(String args[]) throws Exception {
        //new A().generateTestCase();
        new A().run();
    }

}
