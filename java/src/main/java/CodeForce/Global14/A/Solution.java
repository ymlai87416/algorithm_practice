package CodeForce.Global14.A;

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
        new Thread(null, new Solution(), "", 256 * (1L << 20)).start();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            if (System.getProperty("ONLINE_JUDGE") != null) {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            } else {
                in = new BufferedReader(new FileReader("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\Global14\\A\\input.txt"));
                out = new PrintWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\Global14\\A\\output.txt");
            }
            Locale.setDefault(Locale.US);

            int t = Integer.parseInt(in.readLine());

            String line;
            for (int i = 0; i < t; i++) {
                line = in.readLine();
                String[] tok  = line.split(" ");
                int n = Integer.parseInt(tok[0]);
                int x = Integer.parseInt(tok[1]);
                line = in.readLine();
                tok = line.split(" ");
                int[] g = new int[n];
                for (int j = 0; j < n; j++) {
                    g[j] = Integer.parseInt(tok[j]);
                }

                boolean r = solve(n, x, g);
                if(r){
                    out.println("YES");
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < n;j++) {
                        sb.append(g[j]);
                        if(j!= n-1) sb.append(" ");
                    }
                    out.println(sb.toString());
                }
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

    boolean solve(int n, int x, int[] g) throws IOException {
        int sum = 0;
        Arrays.sort(g);
        for (int i = 0; i < n; i++) {
            sum += g[i];
            //switch with last one
            if(sum ==x){
                if(i==n-1){
                    //can only be no, because it is the total
                    return false;
                }
                else {
                    swap(g, i, n - 1);
                    return true;
                }
            }
        }

        return true;
    }

    private void swap(int[] g, int a, int b){
        int t = g[a];
        g[a] = g[b];
        g[b] = t;
    }

}
