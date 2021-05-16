package CodeForce.R720.C;

import java.io.*;
import java.util.ArrayList;
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
            long t1 = System.currentTimeMillis();
            if (System.getProperty("ONLINE_JUDGE") != null) {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            } else {
                in = new BufferedReader(new FileReader("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\R720\\B\\input.txt"));
                out = new PrintWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\R720\\B\\output.txt");
            }
            Locale.setDefault(Locale.US);

            int t = Integer.parseInt(in.readLine());

            String line;
            for (int i = 0; i < t; i++) {
                line = in.readLine();
                int n = Integer.parseInt(line);
                line = in.readLine();
                String[] tok = line.split(" ");
                int[] a = new int[n];
                for (int j = 0; j < n; j++) {
                    a[j] = Integer.parseInt(tok[j]);
                }
                ArrayList<int[]> r = solve(n, a);

                out.println(r.size());
                for (int j = 0; j < r.size(); j++) {
                    out.format("%d %d %d %d\n", r.get(j)[0], r.get(j)[1],r.get(j)[2],r.get(j)[3]);
                }
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

    long lcm(int a, int b){
        int gcd_ = gcd(a, b);
        long v   = 1l * a * b;
        v = v/gcd_;
        return v;
    }

    ArrayList<int[]> solve(int n, int[] a ) throws IOException {

        //choose an integer, and then fix the array
        long[] gcd_ = new long[n-1];
        ArrayList<int[]> result = new ArrayList<>();
        
        for (int j = 0; j < n ; j++) {

            boolean allok = true;
            for (int i = 0; i <n-1; i++) {
                gcd_[i] = gcd(a[i], a[i+1]);
                if(gcd_[i] != 1 ) allok = false;
            }

            if(allok)
                break;

            int  rx, ry;

            boolean fix = false;

            for (int i = 0; i < n-1 && !fix; i++) {
                //swap with an indicies which is bigger than or equal?

                if(gcd_[i] != 1){
                    //swap it with others
                    //choose a index, a ... b =>  a.... c, and then do a swap.
                    //if this position is min, then find a arbitary c
                    //if the target position is min, then this min must be relatively prime
                    int p = a[i]; int q;
                    for (int k = i+1; k < n; k++) {
                        q = a[k];

                        int ap = i-1 >=0 ? a[i-1]: 1;
                        int an = i+1 >= n? 1: a[i+1];

                        rx = -1; ry=-1;

                        if(p > q){
                            //p must be prime of something
                            //q must be prime of a[i-1], q, a[i+1]

                            if(gcd(ap, q) == 1 && gcd(an, q) == 1){
                                rx = q;
                                ry = p;
                                a[i] = rx;
                                a[k] = ry;
                                fix = true;
                                //break;
                            }
                        }
                        else{
                            //q can be any number > p s.t. a[i-1], q, a[i+1] is good
                            for (int l = p+1; l < 2000000000; l++) {
                                if(gcd(ap, l) == 1 && gcd(an, l) == 1){
                                    rx =l;
                                    ry = p;
                                    a[i] = rx;
                                    a[k] = ry;
                                    fix= true;
                                    break;
                                }
                            }
                        }

                        if(!fix) continue;

                        result.add(new int[]{i+1, k+1, rx, ry});
                        break;

                        /*
                        minpq = Math.min(p, q);

                        long ll = lcm((i-1 >=0 ? a[i-1]: 1),  a[i+1]);
                        if(ll > 2000000000)
                            continue;

                        int others = (int) ll+1;
                        if(others <= minpq)
                            others = (minpq/others + 1) * others;


                        if(minpq == p){
                            a[i] = others;
                            a[k] = minpq;
                            rx = others;
                            ry = minpq;
                        }
                        else{
                            a[i] = minpq;
                            a[k] = others;
                            rx = minpq;
                            ry = others;
                        }

                         */
                    }

                }
            }

            System.out.println("**** ");
            for (int k = 0; k < n; k++) {
                System.out.print(a[k] + " ");
            }
            System.out.println("**** ");
            
        }
        
        return result;
    }
}
