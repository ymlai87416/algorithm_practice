package GoogleCodeJam.Y2009.Round1C;

import Template.CodeJamTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Tom on 5/5/2016.
 */
public class CenterOfMass {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;


    static{
        try{
            FILENAME = "B-large-practice (1)";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true)); ;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solve() {
        int ans = 0;

        int nfly = sc.nextInt();
        int[] xa = new int[nfly];
        int[] ya = new int[nfly];
        int[] za = new int[nfly];
        int[] vxa = new int[nfly];
        int[] vya = new int[nfly];
        int[] vza = new int[nfly];

        double dt0 = 0;
        double dt_turn = 0;
        double t_turn = 0;

        double sum1, sum2;
        sum1=0; sum2=0;
        for(int i=0; i<nfly; ++i){
            int x= sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            int vx = sc.nextInt();
            int vy = sc.nextInt();
            int vz = sc.nextInt();

            xa[i] = x; ya[i] = y; za[i] = z;
            vxa[i] = vx; vya[i] = vy; vza[i] = vz;

            sum1 += 2*x*vx + 2*y*vy + 2*z*vz;
            sum2 += 2*vx + 2*vy + 2*vz;
        }

        double avgx = 0, avgy =0, avgz = 0;
        double avgvx = 0, avgvy = 0, avgvz = 0;
        for(int i=0; i<nfly; ++i){
            avgx += xa[i];
            avgy += ya[i];
            avgz += za[i];
            avgvx += vxa[i];
            avgvy += vya[i];
            avgvz += vza[i];
        }
        avgx /= nfly; avgy /= nfly; avgz /= nfly;
        avgvx /= nfly; avgvy /= nfly; avgvz /= nfly;

        sum1 = avgx * avgvx + avgy * avgvy + avgz * avgvz;
        sum2 = avgvx * avgvx + avgvy * avgvy + avgvz * avgvz;
        if(Math.abs(sum2) >  1e-6)
            t_turn = -sum1 / sum2;
        else
            t_turn = 0;

        if(t_turn < 0) t_turn = 0;
        dt_turn = 0;
        dt_turn = Math.pow(avgx + avgvx * t_turn, 2) + Math.pow(avgy + avgvy * t_turn, 2) + Math.pow(avgz + avgvz * t_turn, 2);
        dt0 = Math.pow(avgx , 2) + Math.pow(avgy , 2) + Math.pow(avgz , 2);
        dt_turn =  Math.sqrt(dt_turn);
        dt0 = Math.sqrt(dt0);

        if(dt0 < dt_turn)
            out.format("%.8f %.8f\n", dt0, 0.0, dt0);
        else
            out.format("%.8f %.8f\n", dt_turn, t_turn);

    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            solve();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new CenterOfMass().run();
    }

}
