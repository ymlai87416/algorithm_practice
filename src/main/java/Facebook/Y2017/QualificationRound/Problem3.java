package Facebook.Y2017.QualificationRound;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//start at 11:02, end at 12:37
public class Problem3 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\fighting_the_zombie_example_input";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private double prob(int face, int roll, int target){
        if(target > 400) return 0;
        if(target <= 0) return 1;

        int faceIdx = 0;
        for(int i=0; i<6; ++i)
            if(dice[i] == face)
                faceIdx = i;

        BigDecimal t = new BigDecimal(diceCnt[faceIdx][roll][target]);
        BigDecimal a = new BigDecimal(diceCnt[faceIdx][roll][0]);

        double aa=  t.divide(a, 10, RoundingMode.HALF_UP).doubleValue();
        return aa;
    }



    private void solve(int h, int s, String[] spells) {
        double output = -1;

        for(int i=0; i<s; ++i){
            Matcher m = p1.matcher(spells[i]);
            Matcher m2 = p2.matcher(spells[i]);
            int x,y,z;
            x =0; y=0;
            z= 0;
            if(m.matches()){
                String xs = m.group(1);
                String ys = m.group(2);
                x = Integer.parseInt(xs);
                y = Integer.parseInt(ys);
            }
            else if(m2.matches()){
                String xs = m2.group(1);
                String ys = m2.group(2);
                String zs = m2.group(3);

                x = Integer.parseInt(xs);
                y = Integer.parseInt(ys);
                z = Integer.parseInt(zs);
            }

            double calcP = prob(y, x, h-z);

            //System.out.println("Spell: " + spells[i] + " Prob: " + calcP);

            if(output < calcP)
                output = calcP;
        }


        String outputS = String.format("%.6f", output);
        //System.out.println(outputS);
        out.println(outputS);
    }

    String[] spells = new String[10];

    int[] dice = new int[6];
    BigInteger[][][] diceCnt = new BigInteger[6][21][401];

    Pattern p1, p2;

    private void preRun(){

        String pattern1 = "([0-9]+)d([0-9]+)";
        String pattern2 = "([0-9]+)d([0-9]+)([+-][0-9+]+)";
        p1 = Pattern.compile(pattern1);
        p2 = Pattern.compile(pattern2);


        dice[0] = 4; dice[1] = 6; dice[2]= 8; dice[3] = 10; dice[4]=12; dice[5]=20;

        for(int i=0; i<6; ++i){
            int df = dice[i];

            diceCnt[i][0][0]= BigInteger.ONE;
            for(int k=1; k<=400; ++k)
                diceCnt[i][0][k] = BigInteger.ZERO;

            diceCnt[i][1][0]= BigInteger.ZERO;
            for(int k=1; k<=df; ++k)
                diceCnt[i][1][k] = BigInteger.ONE;
            for(int k=df+1; k<=400; ++k)
                diceCnt[i][1][k] = BigInteger.ZERO;

            for(int j=2; j<=20; ++j){
                diceCnt[i][j][0] = BigInteger.ZERO;

                for(int k=1; k<=df; ++k) {

                    for(int l=k; l<=400; ++l) {
                        if(diceCnt[i][j][l] == null) diceCnt[i][j][l] = BigInteger.ZERO;
                        diceCnt[i][j][l] = diceCnt[i][j][l].add(diceCnt[i][j - 1][l - k]);
                    }
                }
            }
        }

        for(int i=0; i<6; ++i){
            for(int j=0; j<=20; ++j){
                for(int k=399; k>=0; --k){
                    diceCnt[i][j][k] = diceCnt[i][j][k].add(diceCnt[i][j][k+1]);
                }
            }
        }
    }

    private void run() throws Exception {

        preRun();

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            //System.out.print("Case #" + i + ": ");
            out.print("Case #" + i + ": ");
            int h = sc.nextInt();
            int s = sc.nextInt();
            sc.nextLine();
            String spellsListR = sc.nextLine();
            String[] spellsList = spellsListR.split(" ");

            for(int j=0; j<s; ++j){
                spells[j] = spellsList[j];
            }

            solve(h, s, spells);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem3().run();
    }
}
