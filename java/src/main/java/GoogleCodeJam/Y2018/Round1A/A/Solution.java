package GoogleCodeJam.Y2018.Round1A.A;
/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2018\\Round1A\\A\\A-test.in";
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

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private boolean solve(int row, int col, int hcut, int vcut, char[][] waffle) {

        //just check the condition, for H cut and V cut, if anything no good, return impossible

        int nChoco = 0;
        int nChoPiece = 0;
        int nChoHCut = 0;
        int nChoVcut = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(waffle[i][j] == '@')
                    nChoco++;
            }
        }

        int[] hChoco = new int[row];
        int[] vChoco = new int[col];

        for (int i = 0; i < row; i++) {
            hChoco[i] = 0;
            for (int j = 0; j < col; j++) {
                hChoco[i] += (waffle[i][j]== '@' ? 1: 0);
            }
        }

        for (int i = 0; i < col; i++) {
            vChoco[i] = 0;
            for (int j = 0; j < row; j++) {
                vChoco[i] += (waffle[j][i] == '@' ? 1: 0);
            }
        }

        if( nChoco % ((hcut+1)*(vcut+1)) != 0) return false;
        int nChocoPiece = nChoco / ((hcut+1)*(vcut+1));

        int nChocoHcut = nChoco / (hcut+1);
        int nChocoVcut = nChoco / (vcut+1);

        int hcutC = 0;
        int[] hCutAt = new int[hcut+2];
        hCutAt[0] = 0; hCutAt[hcut+1] = row;
        int ptr = 1;
        for (int i = 0; i < row && ptr!=hcut+1; i++) {
            hcutC += hChoco[i];
            if(hcutC == nChocoHcut){
                hcutC = 0;
                debug("horizontal cut at " + i);
                hCutAt[ptr++] = i+1;
            }
            else if(hcutC > nChocoHcut){
                return false;
            }
        }

        int vcutC = 0;
        int[] vCutAt = new int[vcut+2];
        ptr=1;
        vCutAt[0] = 0; vCutAt[vcut+1] = col;
        for (int i = 0; i < col && ptr!=vcut+1; i++) {
            vcutC += vChoco[i];
            if(vcutC == nChocoVcut){
                vcutC = 0;
                debug("vertical cut at " + i);
                vCutAt[ptr++] = i+1;
            }
            else if(vcutC > nChocoVcut){
                return false;
            }
        }

        //finally we know where the cut is and do the final scan.
        for (int i = 1; i <hcut+2; i++) {
            for (int j = 1; j < vcut+2; j++) {
                int c = numChoco(waffle, hCutAt[i-1], vCutAt[j-1], hCutAt[i], vCutAt[j]);
                if( c!= nChocoPiece)
                    return false;
            }
        }

        return true;
    }
    
    private int numChoco(char[][] waffle, int top, int left, int bottom, int right){
        int c = 0;
        for (int i = top; i < bottom; i++) {
            for (int j = left; j < right; j++) {
                if(waffle[i][j] == '@') c++;
            }
        }
        return c;
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

            //String[] waffle = new String[row];
            char[][] waffle = new char[row][col];
            for(int p=0; p<row; ++p){
                String s = sc.nextLine();
                for (int j = 0; j < s.length(); j++) {
                    waffle[p][j] = s.charAt(j);
                }
            }

            out.println(solve(row, col, hcut, vcut, waffle) ? "POSSIBLE" : "IMPOSSIBLE");
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}