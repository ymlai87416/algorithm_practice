package Facebook.Y2021.Quali;

import Facebook.Y2019.Quali.B;

import java.io.File;
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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\consistency_chapter_2_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2021\\Quali\\consistency_chapter_2_output.txt";
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

    private boolean isVow(char c){
        return c == 'A' ||c == 'E' ||c == 'I' ||c == 'O' ||c == 'U';
    }

    private int solve(String s) {
        //now the distinct word
        ArrayList<Character> cons = new ArrayList<>();
        HashSet<Character> disChar = new HashSet<>();

        boolean hasCons = false;
        boolean hasVow = false;
        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            if(isVow(c)) hasVow = true; else hasCons = true;
            disChar.add(c);
        }
        
        if(!hasCons) disChar.add('B');
        if(!hasVow) disChar.add('A');
        
        //now calc the distant and return the min
        int minSec = Integer.MAX_VALUE;
        for (Character _tc: disChar) {
            char tc = _tc;
            int secR = 0;
            boolean isTargetVow = isVow(tc);

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if(c == tc) continue;
                else if(isVow(c) && isTargetVow) secR +=2;
                else if(isVow(c) && !isTargetVow) secR +=1 ;
                else if(!isVow(c) && isTargetVow) secR +=1;
                else if(!isVow(c) && !isTargetVow) secR += 2;
            }

            //System.out.format("Target to %c: Require: %d\n", tc, secR);
            if(secR < minSec) minSec = secR;
        }
        return minSec;
    }

    final int block = 99999;
    int[][] distMatrix = new int[26][26];

    private void calcDistMatrix(char[][] link){
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                distMatrix[i][j] = i==j ? 0:block;
            }
        }
        for (int i = 0; i < link.length; i++) {
            int u = link[i][0] - 'A';
            int v = link[i][1] - 'A';
            distMatrix[u][v] = 1;
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    distMatrix[i][j] = Math.min(distMatrix[i][j], addBlock(distMatrix[i][k], distMatrix[k][j]));
                }
            }
        }
    }

    private int addBlock(int a, int b){
        if(a == block || b==block) return block;
        else return a+b;
    }

    private int solve2(String s, int N, char[][] link) {
        calcDistMatrix(link);
        
        //now check all the possible case and then find the min
        int minR = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            int tempR = 0;
            boolean ok = true;
            for (int j = 0; j < s.length(); j++) {
                int ci = s.charAt(j) - 'A';
                int sR = distMatrix[ci][i];

                if(sR == block) {
                    ok = false;
                    break;
                }

                tempR += sR;
            }

            if(ok && tempR < minR){
                minR = tempR;
            }
        }

        //if impossible, return -1;
        if(minR == Integer.MAX_VALUE) return -1;
        else return minR;

    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String str = sc.nextLine();
            int N = parseInt(sc.nextLine());

            String temp;
            char[][] link = new char[N][2];
            for (int j = 0; j < N; j++) {
                temp = sc.nextLine();
                link[j][0] = temp.charAt(0);
                link[j][1] = temp.charAt(1);
            }
            int r = solve2(str, N, link);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }

}
