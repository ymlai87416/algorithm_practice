package Introduction.Medium;

import java.util.*;

/**
 * Created by Tom on 13/4/2016.
 * Start  at 14:29 and WA at 15:10 and AC at 15:21, total of 52 minutes..... you have  to deal with structure sortting, no fancy stable sort.
 *
 * problem: https://onlinejudge.org/external/101/10141.pdf
 * #ad_hoc #Lv2 #UVA
 */
public class UVA10141{
    static class IntPair implements Comparable<IntPair>{
        double a;
        int b;
        String name;
        int o;
        public IntPair(double a, int b, String name, int order){
            this.a = a; this.b = b; this.name = name; this.o = order;
        }

        @Override
        public int compareTo(IntPair o) {
            if(b > o.b) return -1;
            else if(b < o.b) return 1;
            else{
                if(doubleEqual(a, o.a)) {
                    if( this.o < o.o) return -1;
                    else if(this.o >o.o) return 1;
                    else return 0;
                }
                else if (a < o.a) return -1;
                else return 1;
            }
        }

        boolean doubleEqual(double a, double b){return Math.abs(a-b) < 0.000001;}
    }

    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);

        int cnt =0;
        while(true){
            ++cnt;
            int req =sc.nextInt();
            int prop = sc.nextInt();

            if(req == 0 && prop == 0) break;
            if(cnt != 1) System.out.println();
            sc.nextLine();
            Set<String> reqs = new TreeSet<String>();
            for(int i=0; i<req; ++i){
                reqs.add(sc.nextLine());
            }

            List<IntPair> p = new ArrayList<>();

            for(int i=0; i<prop; ++i){
                String name = sc.nextLine();
                double price = sc.nextDouble();
                int freq = sc.nextInt();
                sc.nextLine();
                Set<String> reqx = new TreeSet<String>();

                int score = freq;
                for(int ii=0; ii<freq; ++ii) sc.nextLine();
                /*int score = 0;
                for(int ii=0; ii<freq; ++ii){
                    String areq = sc.nextLine();
                    if(!reqx.contains(areq) && reqs.contains(areq)) ++score;
                    reqx.add(areq);
                }*/
                p.add(new IntPair(price, score, name, i));
            }

            Collections.sort(p);
            System.out.format("RFP #%d\n", cnt);
            System.out.println(p.get(0).name);
        }
    }
}
