package GoogleCodeJam.Y2016.QualificationRound; /**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class CountingSheep {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "A-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true)); ;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }


    private void solve(int sheep) {
        if(sheep == 0) {
            out.println("INSOMNIA");
            return;
        }

        boolean[] visited = new boolean[10];
        Arrays.fill(visited, false);

        int i, cnt;
        for( i=1; ; ++i){
            cnt = i * sheep;
            String cntStr = String.valueOf(cnt);
            for(int j=0; j<cntStr.length(); ++j){
                int offset = (int)(cntStr.charAt(j));
                offset -= (int)'0';
                visited[offset] = true;
            }

            boolean stop = true;
            for(int k=0; k<10; ++k)
                stop = stop && visited[k];
            if(stop) break;
        }

        out.println(cnt);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int sheep = sc.nextInt();
            solve(sheep);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new CountingSheep().run();
    }

}