package CodeForce.Global14.E;

import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution implements Runnable{

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
                in = new BufferedReader(new FileReader("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\Global14\\E\\input.txt"));
                out = new PrintWriter("C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\CodeForce\\Global14\\E\\output.txt");
            }
            Locale.setDefault(Locale.US);
            String line = in.readLine();
            String[] tok = line.split(" ");

           int n = Integer.parseInt(tok[0]);
           int m = Integer.parseInt(tok[1]);

           loop(0);

           //out.println(solve(n, m));

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

    int[][] dp = new int[401][401];
    int INF = 9999999;
    int n;
    int solve(int n, int m) throws IOException {
        this.n = n;
        for (int i = 0; i < 401; i++) {
            for (int j = 0; j < 401; j++) {
                dp[i][j] = -1;

            }
        }

       long t = dpHelper(0, n, m);

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <=n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

       return (int)t;
    }

    boolean[] v = new boolean[4];
    int[] step = new int[4];
    private void loop(int ptr){

        boolean allOn = true;
        for (int i = 0; i < 4; i++) {
            allOn = allOn && v[i];
        }
        if(allOn){
            for (int i = 0; i < ptr; i++) {
                System.out.print((step[i]+1) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i <4 ; i++) {
            if(v[i]) continue;
            v[i] =true;
            boolean vim1 = i-1 >=0 ? v[i-1]: false;
            boolean via1 = i+1 < 4? v[i+1]: false;
            if(i-2 >= 0 && i-1 >= 0 && v[i-2]) v[i-1] =true;
            if(i+2 <4 && i+1 < 4 && v[i+2]) v[i+1] = true;
            step[ptr] = i;
            loop(ptr+1);
            v[i] = false;
            if(i-1 >=0 )v[i-1] = vim1;
            if(i+1<4) v[i+1] = via1;
        }
    }

    long dpHelper(int a, int b, int m){
        //from [a, b)
        //if a != 0, assume a-1 is one
        //if b != 0 assume b+1 is one

        int l = (a == 0 ? -99999: a-1);
        int r = (b == n ? 99999: b);

        if(dp[a][b] != -1)
            return dp[a][b];

        long result = 0;

        if(a >b) return 0;
        if(a==b) return 1;
        if(a+1==b){
            dp[a][b] = 1;
            return 1;
        }

        for (int i = a; i < b; i++) {
            //turn on a;
            long t1 = 0, t2 = 0;
            long temp = 0;
            if(l+2==i && r-2==i){
                //turn on a will turn on all computer.
                result = (result + 1)%m;
            }
            else if(l+2==i || i==a){
                //turn on a will turn on l, l+1, a
                t1 = 0;
                t2 = Math.max(1, dpHelper(i+1, b, m));
                temp = t2;
            }
            else if(r-2 ==i || i== b-1){
                //turn on will turn on i, i+1, b;
                t1 = Math.max(1, dpHelper(a, i,  m));
                t2 = 0;
                temp = t1;
            }
            else{
                t1 = dpHelper(a, i,  m);
                t2 = dpHelper(i+1, b, m);
                temp = 2*(t1*t2);
            }

            result = (result+temp) %m;
        }

        dp[a][b] = (int)result;

        return result;
    }


}
