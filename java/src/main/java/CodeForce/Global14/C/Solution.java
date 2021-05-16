package CodeForce.Global14.C;

import java.awt.Point;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import static java.lang.Math.*;

public class Solution implements Runnable {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Thread(null, new CodeForce.Global14.B.Solution(), "", 256 * (1L << 20)).start();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            if (System.getProperty("ONLINE_JUDGE") != null) {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            } else {
                in = new BufferedReader(new FileReader("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\Global14\\C\\input.txt"));
                out = new PrintWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\Global14\\C\\output.txt");
            }
            Locale.setDefault(Locale.US);

            int t = Integer.parseInt(in.readLine());

            String line;
            for (int i = 0; i < t; i++) {
                line = in.readLine();
                String[] tok = line.split(" ");
                int n = Integer.parseInt(tok[0]);
                int m = Integer.parseInt(tok[1]);
                int x = Integer.parseInt(tok[2]);

                boolean r = solve(n, m, x);
                if(r)
                    out.println("YES");
                else
                    out.println("NO");
            }


            in.close();
            out.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Time = " + (t2 - t1));
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    String readString() throws IOException {
        while (!tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

    long readLong() throws IOException {
        return Long.parseLong(readString());
    }

    double readDouble() throws IOException {
        return Double.parseDouble(readString());
    }

    // solution

    boolean solve(int n, int m, int x) throws IOException {
            return false;
    }

}