package GoogleCodeJam.Y2021.Round1B.A;

import java.io.File;
import java.io.PrintStream;
import java.util.*;


/*
fuck: This is a 30 marks, but takes too long to complete....

just use angle from hour to minutes (+ve), complicate myself using acute angle between hour and minutes.
I loop myself every case. This only complicate boundary checking...

The 11 marks can be get by just checking the angle and return the result...

 */
public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2021\\Round1B\\A\\A-test.in";
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

    boolean debugflag = true;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    long nano = 1000000000l;
    long total = 720*60*nano;

    private long[] solve(long a, long b, long c) {

        //a = hour
        //b = min
        long[] r= null;
        r = checkOK(a, b, c);
        if(r!=null) return r;
        //b = sec
        r = checkOK(a, c, b);
        if(r!=null) return r;
        //a = minute
        //b = hour
        r = checkOK(b, a, c);
        if(r!=null) return r;

        //b = second
        r = checkOK(c, a, b);
        if(r!=null) return r;

        //a = second
        //b = hour
        r = checkOK(b, c, a);
        if(r!=null) return r;

        //b = minute
        r = checkOK(c, b, a);
        if(r!=null) return r;

        return new long[]{-1, -1, -1, -1};
    }

    private void output(long[] r){
        for (int i = 0; i < r.length; i++) {
            out.print(" " + r[i]);
        }
        out.println();
    }

    long secondTick = 720* nano;

    private long addOffset(long a, long offset){
        long k = a-offset;
        if(k < 0)
            k += total;
        else if(k >= total)
            k -= total;

        return k;
    }

    private long[] checkOK(long a, long b, long c){

        long ma, mb, mc;
        ma = a; mb = b; mc = c;

        /*
        long aaa = normalAngle(ma-mb);
        long paaa = normalAngle((ma-nano) - (mb-nano*12));
        */
        long aaa = angleHM(a, b);
        long paaa = angleHM(ma-nano, mb-nano*12);
        //long paaa = aaa - nano*(11);
        boolean crossline = (ma >=mb) && (ma-nano <= mb-nano*12) || (ma<=mb) && (ma-nano >= mb-nano*12);

        Map<Long, ArrayList<long[]>> sub = null;
        if(crossline){
            Map<Long, ArrayList<long[]>> sub1, sub2;
            sub1 = lookup.subMap(paaa+1, total);
            sub2 = lookup.subMap(0l, aaa+1);

            sub = new TreeMap<Long, ArrayList<long[]>>();
            for (Long l: sub1.keySet()) {
                sub.put(l, sub1.get(l));
            }
            for (Long l: sub2.keySet()) {
                sub.put(l, sub2.get(l));
            }
        }
        else {
            sub = lookup.subMap(paaa + 1, aaa + 1);
        }

        //negative
        if(paaa < 0) {
            Map<Long, ArrayList<long[]>> sub2 = lookup.subMap(0l, Math.abs(paaa) + 1);
            TreeMap<Long, ArrayList<long[]>> cccc = new TreeMap<>();
            cccc.putAll(sub);
            for (Long t: sub2.keySet()) {
                cccc.put(t, sub2.get(t));
            }
            sub= cccc;
        }


        for(Long subL: sub.keySet()) {

            ArrayList<long[]> possible = lookup.get(subL);
            if (possible == null) continue;

            for (int i = 0; i < possible.size(); i++) {
                long h = possible.get(i)[0];
                long m = possible.get(i)[1];
                long s = possible.get(i)[2];

                //find the angle new
                long nanoPass = Math.abs(aaa-subL) / 11;
                if(nanoPass >= nano) continue;
                long offset = a - (possible.get(i)[3] + nanoPass);
                ma = addOffset(a, offset);
                mb = addOffset(b, offset);
                mc = addOffset(c, offset);

                long n = getNanoSeconds(mc);
                //debug("sh" + h + ", " + m + ", " + s + ", " + n);

                if (!checkMinutes(mb, m, mc)) {
                    //debug("error min");
                    continue;
                }
                if (!checkHour(ma, h, mb)) {
                    //debug("error hour");
                    continue;
                }

                return new long[]{h, m, s, n};
            }
        }

        return null;
    }

    private long normalAngle(long a){
        if(a < 0)
            a += total;
        if(a > total/2)
            a = total - a;

        return a;
    }

    private long getHour(long a){
        return a * 12 / total;
    }

    private long getMinutes(long a){
        return a * 60 / total;
    }

    private long getSeconds(long a){
        return a * 60 / total;
    }

    private long getNanoSeconds(long a){
        long s = a % secondTick;
        return s/720;
    }

    private boolean checkMinutes(long b, long m, long s){
        long remainT = b- (m*total/60);
        return remainT*60 == s;
    }

    private boolean checkHour(long b, long h, long m){
        long remainT = b- (h*total/12);
        return remainT*12 == m;
    }

    TreeMap<Long, ArrayList<long[]>> lookup = new TreeMap<>();

    private long angleHM(long hour, long minute){
        if(minute < hour){
            return (minute+total) - hour;
        }
        else
            return minute - hour;
    }

    private void test(){

        solve(5831000000000l,26772000000000l,7920000000000l);

        long hourhand = 0;
        for (int i = 0; i < 12; i++) {
            long minuteshand = 0;
            for (int j = 0; j < 60; j++) {
                long secondshand = 0;
                for (int k = 0; k < 60; k++) {
                    long[] r = solve(hourhand, minuteshand, secondshand);


                    if((r[0] != i || r[1] !=j || r[2] != k ) || (i==6&&j==30 &&k==0 ) || (i==0&&j==30 &&k==0))
                        debug(hourhand + " "+ minuteshand + " "+ secondshand + " " + i+" " + j + " " + k + " " +r[0]  + " "+ r[1]  + " "+ r[2]);

                    hourhand+= nano;
                    minuteshand += 12*nano;
                    secondshand += 720*nano;
                }
            }
        }
    }

    private void run() throws Exception {

        // out = new PrintStream(new FileOutputStream(OUT));
        long hourhand = 0;

        for (int i = 0; i < 12; i++) {
            long minuteshand = 0;
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {


                    //long a = normalAngle(hourhand-minuteshand);
                    //long a = hourhand-minuteshand;
                    long a = angleHM(hourhand, minuteshand);

                    //if(i==6 && j==30 && k==0) debug("6:30" + a + " " + hourhand + " " + minuteshand);
                    //if(i==0 && j==30 && k==0) debug("0:30" + a + " " + hourhand + " " + minuteshand);

                    long[] ct = new long[4];
                    ct[0] = i; ct[1] = j; ct[2] = k; ct[3]=hourhand;
                    if(lookup.containsKey(a)){
                        lookup.get(a).add(ct);
                    }
                    else{
                        ArrayList<long[]> ttt = new ArrayList<>();
                        ttt.add(ct);
                        lookup.put(a, ttt);
                    }

                    hourhand+= nano;
                    minuteshand += 12*nano;
                }
            }
        }

        if(false){
            test();
            return;
        }
        
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();
            long[] r = solve(a, b, c);
            output(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
