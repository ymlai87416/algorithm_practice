package GoogleCodeJam.Y2009.Round1A.A;


/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2009\\Round1A\\A\\A-test.in";
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

    private int solveSmall(int[] bases, int startPos ) {
        int i = startPos;
        boolean found = false;

        while(!found){

            boolean isHappy = true;
            for (int j = 0; j < bases.length && isHappy; j++) {

                long rs = i;
                String rep = Long.toString(rs, bases[j]);
                TreeSet<String> path = new TreeSet<>();
                path.add(rep);

                do{
                    long nextRs = 0;

                    for (int k = 0; k < rep.length(); k++) {
                        long b = rep.charAt(k) - '0';
                        nextRs += b * b;
                    }

                    String newRep = Long.toString(nextRs, bases[j]);
                    //System.out.print(nextRs + " ");
                    if(path.contains(newRep)) {
                        isHappy =false;
                        break;
                    }
                    path.add(newRep);
                    rs = nextRs;
                    rep = newRep;
                }while(rs != 1);
                //System.out.println();
            }

            if(isHappy)
                found=true;
            else
                i+=1;
        }

        return i;
    }

    public int solveBigPreCompute(){
        //now we try to generate all the combination
        int[] cache = new int[512];
        for(int i=1; i<512; ++i)
        {
            ArrayList<Integer> bases = new ArrayList<>();

            for (int j = 0; j < 10; j++) {
                int k = 1 << j;
                if((i & k) > 0)
                    bases.add(j+2);
            }

            int[] bb = new int[bases.size()];
            for(int j=0; j<bases.size(); ++j)
                bb[j] = bases.get(j);

            //find min bb
            int minpos = 2;
            for (int j = 0; j < i; j++) {
                boolean b = (j & i) > 0;
                boolean c = (j & ~i) == 0;

                if (b && c)
                    minpos = Math.max(minpos, cache[j]);
            }
            //System.out.format("solve %s using minpos: %d\n", Integer.toString(i), minpos);
            cache[i] = solveSmall(bb, minpos);

            System.out.format("%s, %d\n", Integer.toString(i), cache[i]);
        }

        return 0;
    }

    int[] preCompute = new int[] {0, 2, 3, 3, 2, 2, 3, 3, 5, 5, 23, 23, 5, 5, 23, 23, 6, 6, 79, 79, 6, 6, 79, 79, 49, 49, 79, 79, 49, 49, 79, 79, 7, 7, 143, 143, 7, 7, 143, 143, 7, 7, 143, 143, 7, 7, 143, 143, 49, 49, 2207, 2207, 49, 49, 2207, 2207, 49, 49, 2207, 2207, 49, 49, 2207, 2207, 8, 8, 27, 27, 8, 8, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 216, 216, 3879, 3879, 216, 216, 3879, 3879, 1975, 1975, 3879, 3879, 1975, 1975, 3879, 3879, 1001, 1001, 5719, 5719, 1001, 1001, 5719, 5719, 1001, 1001, 5719, 5719, 1001, 1001, 5719, 5719, 47089, 47089, 47089, 47089, 47089, 47089, 47089, 47089, 48769, 48769, 58775, 58775, 48769, 48769, 58775, 58775, 3, 3, 3, 3, 3, 3, 3, 3, 27, 27, 27, 27, 27, 27, 27, 27, 415, 415, 707, 707, 415, 415, 707, 707, 415, 415, 1695, 1695, 415, 415, 1695, 1695, 125, 125, 143, 143, 125, 125, 143, 143, 125, 125, 143, 143, 125, 125, 143, 143, 2753, 2753, 37131, 37131, 2753, 2753, 37131, 37131, 37131, 37131, 37131, 37131, 37131, 37131, 37131, 37131, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 4977, 4977, 10089, 10089, 4977, 4977, 10089, 10089, 4977, 4977, 23117, 23117, 4977, 4977, 23117, 23117, 6393, 6393, 35785, 35785, 6393, 6393, 35785, 35785, 6393, 6393, 128821, 128821, 6393, 6393, 128821, 128821, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 569669, 7, 7, 13, 13, 7, 7, 13, 13, 7, 7, 23, 23, 7, 7, 23, 23, 44, 44, 79, 79, 44, 44, 79, 79, 49, 49, 79, 79, 49, 49, 79, 79, 7, 7, 167, 167, 7, 7, 167, 167, 7, 7, 7895, 7895, 7, 7, 7895, 7895, 49, 49, 6307, 6307, 49, 49, 6307, 6307, 49, 49, 7895, 7895, 49, 49, 7895, 7895, 97, 97, 219, 219, 97, 97, 219, 219, 219, 219, 219, 219, 219, 219, 219, 219, 608, 608, 3879, 3879, 608, 608, 3879, 3879, 3879, 3879, 3879, 3879, 3879, 3879, 3879, 3879, 34527, 34527, 48041, 48041, 34527, 34527, 48041, 48041, 34527, 34527, 120407, 120407, 34527, 34527, 120407, 120407, 245035, 245035, 697563, 697563, 245035, 245035, 697563, 697563, 245035, 245035, 2688153, 2688153, 245035, 245035, 2688153, 2688153, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 1033, 1033, 6073, 6073, 1033, 1033, 6073, 6073, 4577, 4577, 6073, 6073, 4577, 4577, 6073, 6073, 1337, 1337, 1337, 1337, 1337, 1337, 1337, 1337, 29913, 29913, 120149, 120149, 29913, 29913, 120149, 120149, 71735, 71735, 613479, 613479, 71735, 71735, 613479, 613479, 218301, 218301, 711725, 711725, 218301, 218301, 711725, 711725, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 1177, 9867, 9867, 28099, 28099, 9867, 9867, 28099, 28099, 28099, 28099, 28099, 28099, 28099, 28099, 28099, 28099, 48041, 48041, 48041, 48041, 48041, 48041, 48041, 48041, 246297, 246297, 346719, 346719, 246297, 246297, 346719, 346719, 2662657, 2662657, 4817803, 4817803, 2662657, 2662657, 4817803, 4817803, 11814485, 11814485, 11814485, 11814485, 11814485, 11814485, 11814485, 11814485};
    public int solveBig(int[] bases){
        int idx = 0;
        for(int i=0; i<bases.length; ++i){
            int rb = bases[i] -2;
            idx = idx + (1 << rb);
        }
        return preCompute[idx];
    }


    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        //solveBigPreCompute();

        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            String input = sc.nextLine();
            String[] bases = input.split(" ");
            int[] baseInt = new int[bases.length];
            for (int j = 0; j < bases.length; j++) {
                baseInt[j] = Integer.valueOf(bases[j]);
            }

            out.print("Case #" + i + ": ");
            int r = 0;
            r= solveBig(baseInt);

            System.out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}