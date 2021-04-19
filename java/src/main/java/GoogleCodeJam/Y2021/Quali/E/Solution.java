package GoogleCodeJam.Y2021.Quali.E;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Quali\\E\\E-test.in";
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

    private String generateShit(int S, boolean cheat){

        //order by difficulties
        double[] d = new double[10000];
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            d[i] = r.nextDouble()* 6 - 3;
        }
        Arrays.sort(d);

        for (int i = d.length-1; i >=0 ; i--) {
            double c = r.nextDouble();
            if(cheat && c >= 0.5)
                sb.append("1");
            else {

                double prob = 1 / (1 + Math.exp(S - d[i]));
                double p = r.nextDouble();
                if (p < prob)
                    sb.append("1");
                else
                    sb.append("0");
            }
        }

        return sb.toString();
    }

    //divide into bucket
    int bucketSize = 100;//total 500 point
    int bucketLen = 10000/bucketSize;
    int threshold = 60;    //scale 200 = 350 vs 150. 150 = 352 vs 175
    int threshold2 = 75;

    private void getStatistic(String[] input){
        double[] bb = new double[bucketLen];

        double abup=0, abdown=0, amse=0, alast3B=0, acorrect=0;
        for (int p = 0; p < input.length; p++) {
            for (int j = 0; j < bucketLen; j++) {
                bb[j] = 0;
                for (int k = 0; k < bucketSize; k++) {
                    bb[j] += (input[p].charAt(j*bucketSize+k) == '1' ? 1: 0);
                }
                bb[j] = bb[j] / bucketSize;
            }

            //now we know the upper and lower bucket, draw a line and find out where bucket lie
            int bup = 0, bdown = 0;
            double diff = (bb[0] - bb[bucketLen-1]) / (bucketLen-1);
            double ss = bb[0];
            double mse = 0;
            for (int j = 0; j < bucketLen; j++) {
                if(bb[j] > ss)
                    bup += 1;
                else
                    bdown += 1;
                mse += (bb[j]-ss)* (bb[j]-ss);
                ss = ss - diff;
            }
            double last3B= (bb[bucketLen-1] + bb[bucketLen-2] + bb[bucketLen-3] )/3;
            int correct = 0;
            for (int i = 0; i < input[p].length(); i++) {
                correct += (input[p].charAt(i) == '1' ? 1: 0);
            }

            abup += bup;
            abdown += bdown;
            amse += mse;
            acorrect += correct;

            alast3B += last3B;
        }

        abup /= input.length;
        abdown /= input.length;
        amse /= input.length;
        alast3B /= input.length;
        acorrect /= input.length;

        System.out.format("%.6f %.6f %.6f %.6f %.6f\n", abup, abdown, amse, alast3B, acorrect);
    }



    private double isCheater(String input){
        double[] bb = new double[bucketLen];

        for (int j = 0; j < bucketLen; j++) {
            bb[j] = 0;
            for (int k = 0; k < bucketSize; k++) {
                bb[j] += (input.charAt(j*bucketSize+k) == '1' ? 1: 0);
            }
            bb[j] = bb[j] / bucketSize;
        }

        //now we know the upper and lower bucket, draw a line and find out where bucket lie
        int bup = 0, bdown = 0;
        double diff = (bb[0] - bb[bucketLen-1]) / (bucketLen-1);
        double ss = bb[0];
        for (int j = 0; j < bucketLen; j++) {
            if(bb[j] > ss)
                bup += 1;
            else
                bdown += 1;
            ss = ss - diff;
        }

        double score = 0;
        double last3B = (bb[bucketLen-1] + bb[bucketLen-2] + bb[bucketLen-3] )/3;

        String curveType = "";
        if(bup-bdown > threshold2){      //for the case of S > 2
            //value does not fall below 0.5 error
            curveType = "Convex down >2";
            if(last3B > 0.6) {  //expect 0.5
                score = 4 + (last3B-0.6);

                debug("Convex down" + bb[bucketSize - 1]);
                debug(formatArray(bb));
            }
        }
        else if(bup-bdown > threshold){      //for the case between 1 and 2
            //value does not fall below 0.5 error
            curveType = "Convex down < 2";
            if(last3B > 0.3) {  //expect 0.5
                score = 4 + (last3B-0.3);

                debug("Convex down" + bb[bucketSize - 1]);
                debug(formatArray(bb));
            }
        }
        //conclave upward
        else if (bdown-bup > threshold2){        //for the case of S > 2
            curveType = "Convex up > 2";
            if(last3B > 0.1) {  //expect 0
                score = 4 + (last3B - 0.1);

                debug("Convex upward" + bb[bucketSize - 1]);
                debug(formatArray(bb));

            }
        }
        else if (bdown-bup > threshold){         //for the case between 1 and 2
            curveType = "Convex up < 2";
            if(last3B > 0.15) {  //expect 0.1
                score = 4 + (last3B - 0.15);

                debug("Convex upward" + bb[bucketSize - 1]);
                debug(formatArray(bb));
            }
        }
        else {                          //flat curve
            curveType = "flat -1 < 0 < 1";
            if(last3B > 0.2) {  //expect 0.1
                score = 4+ (last3B - 0.2);  ;

                debug("flat curve" + bb[bucketSize - 1]);
                debug(formatArray(bb));

            }
        }

        return score;
    }

    boolean debugFlag = false;
    private void debug(String s){
        if(debugFlag)
            System.out.println(s);
    }

    private String formatArray(double[] d){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < d.length; i++) {
            sb.append(d[i] + " ");
        }
        return sb.toString();
    }

    private int solve(String[] result) {

        int[] correct = new int[10000];
        for (int i = 0; i < result.length;i++) {
            for (int j = 0; j < result[i].length(); j++) {
                if(result[i].charAt(j) == '1')
                    correct[j] += 1;
            }
        }

        //now we sort
        Integer[] sorted = new Integer[10000];
        for (int i = 0; i < 10000; i++) {
            sorted[i] = i;


        }
        Arrays.sort(sorted, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                if(correct[o1] ==  correct[o2])
                    return o1-o2;
                else return correct[o2] - correct[o1];
            }
        });

        String[] illustrate = new String[100];
        for (int i = 0; i < result.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 10000; j++) {
                builder.append(result[i].charAt(sorted[j]));
            }
            illustrate[i] = builder.toString();
        }

        double score = 0;
        int fcheater = 0;
        for (int i = 0; i < 100; i++) {
            double  x = isCheater(illustrate[i]);
            if(x > score) {
                score = x;
                fcheater = i + 1;
            }
        }


        return fcheater;
    }

    private void run() throws Exception {

        if(true){
            //collect statistic

            /*
            String[] haha=  new String[100];
            for (int i = -3; i <= 3; i++) {
                for (int q = 0; q < 100; q++) {
                    haha[q] =  generateShit(i, false);
                }

                System.out.println(i + " no cheat");
                getStatistic(haha);

                for (int q = 0; q < 100; q++) {
                    haha[q] =  generateShit(i, true);
                }

                System.out.println(i + " cheat");
                getStatistic(haha);
            }

            return;
            */

            int cc=0, wc=0;
            for (int p = 0; p < 100; p++) {
                cc +=2;
                for (int i = -3; i <= 3; i++) {
                    String s3 = generateShit(i, false);
                    double aa = isCheater(s3);
                    if(aa > 0) {
                        System.out.println("normal fuck " + i);
                        wc+=1;
                    }

                    String c3 = generateShit(i, true);
                    aa = isCheater(c3);
                    if(aa == 0) {
                        System.out.println("cheat fuck " + i);
                        wc +=1;
                    }

                }
            }
            System.out.println("Shit percentage: " + 100* wc/cc);

            return;
            
        }

        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        int P = sc.nextInt();
        sc.nextLine();
        String[] result = new String[100];
        for (int i = 1; i <= t; i++) {

            for (int j = 0; j < 100; j++) {
                result[j] = sc.nextLine();
            }

            out.print("Case #" + i + ": ");
            System.out.println(solve(result));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}