package Facebook.Y2016.QualificationRound;

import Geometry.PointsAndLines.UVA920;

import java.io.*;
import java.util.*;

//start at 21:36, end at 22:20....
public class Problem1 {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            FILENAME = "C:\\Users\\Tom\\Documents\\algorithm_practice\\boomerang_constellations";
            IN = FILENAME + ".txt";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, true));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    static class Pair implements Comparable<Pair>{
        int first, second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if(first < o.first) return 1;
            else if(first > o.first) return -1;
            else return second - o.second;
        }
    }

    static class Pair2 implements Comparable<Pair2>{
        int first;
        long second;
        public Pair2(int first, long second){
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair2 o) {
            if(first < o.first) return 1;
            else if(first > o.first) return -1;
            else if(second == o.second) return 0;
            else return second - o.second > 0 ? 1: -1;
        }
    }

    static Pair[] points = new Pair[2000];

    private void solve(int c, int k, Pair[] pairs) {
        TreeMap<Pair2, Integer> dists =new TreeMap<Pair2, Integer >();
        for(int i=0; i<k; ++i){
            for(int j=i+1; j<k; ++j){
                long dd = distance(pairs[i], pairs[j]);
                Pair2 dd2 = new Pair2(i, dd);
                Pair2 dd3 = new Pair2(j, dd);
                if(dists.containsKey(dd2)){
                    dists.put(dd2, dists.get(dd2)+1);
                }
                else
                    dists.put(dd2, 1);

                if(dists.containsKey(dd3)){
                    dists.put(dd3, dists.get(dd3)+1);
                }
                else
                    dists.put(dd3, 1);
            }
        }

        long result = 0;
        for(Map.Entry<Pair2, Integer> item : dists.entrySet()){
            if(item.getValue() < 2)
                continue;
            long xx = item.getValue();
            result += xx * (xx-1) / 2;
        }
        String output = String.format("Case #%d: %d", c, result);
        System.out.println(output);
        out.println(output);
    }


    private long distance(Pair x, Pair y){
        return (x.first - y.first) *  (x.first - y.first) + (x.second - y.second) * (x.second - y.second);
    }


    private void run() throws Exception {

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            int k = sc.nextInt();

            for(int j=0; j<k; ++j){
                int x = sc.nextInt();
                int y = sc.nextInt();
                points[j] = new Pair(x, y);
            }

            solve(i, k , points);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Problem1().run();
    }
}
