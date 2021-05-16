package CodeForce.R720.A;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution implements Runnable {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Thread(null, new Solution(), "", 256 * (1L << 20)).start();
    }

    public void run() {


        try {

            //test();


            long t1 = System.currentTimeMillis();
            if (System.getProperty("ONLINE_JUDGE") != null) {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            } else {
                in = new BufferedReader(new FileReader("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\R720\\A\\input.txt"));
                out = new PrintWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\R720\\A\\output.txt");
            }
            Locale.setDefault(Locale.US);

            int t = Integer.parseInt(in.readLine());

            String line;
            for (int i = 0; i < t; i++) {
                line = in.readLine();
                String[] tok  = line.split(" ");
                int a = Integer.parseInt(tok[0]);
                int b = Integer.parseInt(tok[1]);

                int c = Math.min(a, b); int d = Math.max(a, b);
                long[] r = solve(c, d);
                if(r!= null){
                    out.println("YES");
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < r.length;j++) {
                        sb.append(r[j]);
                        if(j!= r.length-1) sb.append(" ");
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

    int gcd(int a, int b){
        if(a%b==0) return b;
        return gcd(b, a%b);
    }

    void test() throws IOException {
        for (int i = 990000; i < 1000000; i++) {
            for (int j = i+1; j < 1000000; j++) {
                long[] t = solve(i, j);

                if(t[0] % j == 0 || t[1] % j == 0 || t[2] % i != 0 || t[2] %j != 0 || t[0] % i!=0 || t[1] % i != 0)
                    System.out.println("shit" + i + " " + j);
            }
        }
    }

    long[] solve(int a, int b) throws IOException {
        if(a ==b) return null;

        int gcd_ = gcd(b, a);
        /*

        if(gcd_ > 1) {
            int e = b/gcd_;
            int c = Math.min(a, e);
            int d = Math.max(a, e);
            return solve(c, d);
        }*/

        //find one that is x = AB * N, y = A * P z = A * Q
        //no if B = 1 or A = ?B, you have to be careful
        long x, y, z;
        for (int i = 1; i < 100000; i++) {
            z = 1l*  a * b  * i;
            long zf = b  * i;
            long tt = b;

            //find p and q such that p + q = b * i;
            for (int j = 1; j < Math.min(zf/2, 200000000); j++) {
                long p = j;
                long q = zf-j;

                if(p != q && a* p % b != 0 && a* q % b != 0){
                    x = 1l* a * p;
                    y = 1l* a * q;

                    return new long[]{x, y, z};
                }
            }
        }

       //1999998

        return null;
    }


}
