package GoogleCodeJam.Y2016.QualificationRound; /**
 * Created by Tom on 9/4/2016.
 */
/**
 * Created by Tom on 9/4/2016.
 */
import java.io.*;
import java.util.Scanner;

public class JamCoin2 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C-small-attempt1";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    private void solve(int coinLen, int ncoin) {
        out.println("1000000000000001 3 2 5 2 7 2 3 2 7");
        out.println("1000000000000101 13 11 3 4751 173 3 53 109 3");
        out.println("1000000000000111 3 2 5 2 7 2 3 2 11");
        out.println("1000000000001001 73 5 3 19 19 3 5 19 3");
        out.println("1000000000001101 3 2 5 2 7 2 3 2 11");
        out.println("1000000000010011 3 2 5 2 7 2 3 2 7");
        out.println("1000000000011001 3 2 5 2 7 2 3 2 11");
        out.println("1000000000011011 5 1567 15559 6197 5 5 1031 7 83");
        out.println("1000000000011111 3 2 3 2 7 2 3 2 3");
        out.println("1000000000100101 3 2 5 2 7 2 3 2 7");
        out.println("1000000000101011 3 7 13 3 5 43 3 73 7");
        out.println("1000000000101111 5 2 3 2 37 2 5 2 3");
        out.println("1000000000110001 3 2 5 2 7 2 3 2 11");
        out.println("1000000000110101 23 17 11 23 5 299699 43 239 59");
        out.println("1000000000110111 3 2 3 2 7 2 3 2 3");
        out.println("1000000000111011 17 2 3 2 73 2 17 2 3");
        out.println("1000000000111101 3 2 3 2 7 2 3 2 3");
        out.println("1000000001000011 3 2 5 2 7 2 3 2 11");
        out.println("1000000001001001 3 2 5 2 7 2 3 2 7");
        out.println("1000000001001111 3 2 3 2 7 2 3 2 3");
        out.println("1000000001010101 3 7 13 3 5 17 3 53 7");
        out.println("1000000001010111 5 2 3 2 37 2 5 2 3");
        out.println("1000000001011001 11 5 281 101 5 67 5 13 19");
        out.println("1000000001011011 3 2 3 2 7 2 3 2 3");
        out.println("1000000001011101 17 2 3 2 1297 2 11 2 3");
        out.println("1000000001011111 59 113 7 157 19 1399 7 43 107");
        out.println("1000000001100001 3 2 5 2 7 2 3 2 11");
        out.println("1000000001100011 23 19 11 105491 5 47 11117 1787 127");
        out.println("1000000001100111 3 2 3 2 7 2 3 2 3");
        out.println("1000000001101011 5 2 3 2 37 2 5 2 3");
        out.println("1000000001101101 3 2 3 2 7 2 3 2 3");
        out.println("1000000001110011 3 2 3 2 7 2 3 2 3");
        out.println("1000000001110101 5 2 3 2 37 2 5 2 3");
        out.println("1000000001111001 3 2 3 2 7 2 3 2 3");
        out.println("1000000001111011 31 557 7 19 23 1129 7 5441 241");
        out.println("1000000001111101 7 19 43 17 55987 23 7 7 31");
        out.println("1000000001111111 3 2 5 2 7 2 3 2 7");
        out.println("1000000010000011 167 2 11 2 58427 2 23 2 839");
        out.println("1000000010000101 3 2 5 2 7 2 3 2 11");
        out.println("1000000010001001 5 2 7 2 1933 2 29 2 157");
        out.println("1000000010010001 3 2 5 2 7 2 3 2 7");
        out.println("1000000010010111 3 2 3 2 7 2 3 2 3");
        out.println("1000000010011001 7 1667 179 13 5 11 23 7 311");
        out.println("1000000010011011 11 2 3 2 13 2 47 2 3");
        out.println("1000000010011101 3 2 3 2 7 2 3 2 3");
        out.println("1000000010100011 3 1259 421 3 5 8893 3 67 17");
        out.println("1000000010100111 5 2 3 2 37 2 5 2 3");
        out.println("1000000010101001 3 5 13 3 5 43 3 73 7");
        out.println("1000000010110011 47 2 3 2 11 2 204311 2 3");
        out.println("1000000010110101 3 2 3 2 7 2 3 2 3");
    }

    private void solveBig(int coinLe, int ncoin){

    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.println("Case #" + i + ": ");
            int coinLen = sc.nextInt();
            int ncoin = sc.nextInt();
            solve(coinLen, ncoin);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new JamCoin2().run();
    }

}
