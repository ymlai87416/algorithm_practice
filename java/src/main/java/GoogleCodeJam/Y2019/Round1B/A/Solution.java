package GoogleCodeJam.Y2019.Round1B.A;

/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2019\\Round1B\\A\\A-test.in";
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

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    static class Range{
        public int l;
        public int h;
        public Range next;
        public int cnt;
        public Range(int l, int h, int cnt){
            this.l = l;
            this.h = h;
            this.cnt = cnt;
            this.next = null;
        }
    }

    private int[] solveSmall(int p, int q, int[] xx, int[] yy, char[] dd){
        //Row is on X direction which mean west to east = 0 -> q

        ArrayList<ArrayList<Sum>> test = new ArrayList<>();
        for (int j=0; j<q+1; ++j) {
            //input[j] = sc.nextLine();
            ArrayList<Sum> qRow = new ArrayList<Sum>();
            qRow.add(new Sum(0, q, 0) );
            test.add(qRow);
        }

        for(int j=0; j<p; ++j){
            int x = xx[j]; int y = yy[j]; char d = dd[j];

            if(d == 'N') {
                for(int k=y+1; k<=q; ++k){
                    ArrayList<Sum> newQRow = new ArrayList<Sum>();

                    for(Sum s: test.get(k)){
                        ArrayList<Sum> b = s.add(0, q, 1);
                        newQRow.addAll(b);
                    }

                    test.set(k, newQRow);
                }
            }
            else if(d == 'E') {
                for(int k=0; k<=q; ++k){
                    ArrayList<Sum> newQRow = new ArrayList<Sum>();

                    for(Sum s: test.get(k)){
                        ArrayList<Sum> b = s.add(x+1, q, 1);
                        newQRow.addAll(b);
                    }

                    test.set(k, newQRow);
                }
            }
            else if(d == 'S') {
                for(int k=y-1; k>=0; --k){
                    ArrayList<Sum> newQRow = new ArrayList<Sum>();

                    for(Sum s: test.get(k)){
                        ArrayList<Sum> b = s.add(0, q, 1);
                        newQRow.addAll(b);
                    }

                    test.set(k, newQRow);
                }
            }
            else if(d == 'W') {
                for(int k=0; k<=q; ++k){
                    ArrayList<Sum> newQRow = new ArrayList<Sum>();

                    for(Sum s: test.get(k)){
                        ArrayList<Sum> b = s.add(0, x-1, 1);
                        newQRow.addAll(b);
                    }

                    test.set(k, newQRow);
                }
            }

            //System.out.println("ddd");
        }

        //find the maximum
        int max_x, max_y, max_v;
        max_v = max_x = max_y = -1;

        for(int j=0; j<=q; ++j){
            ArrayList<Sum> currQRow  = test.get(j);

            for(Sum s: currQRow) {
                if (s.val > max_v){

                    int new_y = j;
                    int new_x = s.start;

                    max_x = new_x;
                    max_y = new_y;
                    max_v = s.val;

                    //System.out.format("debug: %d %d %d\n", max_x, max_y, max_v);
                }
                else if(s.val == max_v){
                    int new_y = j;
                    int new_x = s.start;

                    if(new_y < max_y || new_x < max_x) {

                        max_x = new_x;
                        max_y = new_y;
                        max_v = s.val;

                        //System.out.format("debug2: %d %d %d\n", max_x, max_y, max_v);
                    }
                }
            }
        }

        return new int[]{max_x, max_y};
    }

    private int[] solve(int P, int Q, int[] x, int[] y, char[] d) {
        int[] r = new int[2];

        int left, right, top, bottom;
        left = 1; right = Q; top = 1; bottom=Q;
        //S -> N 1: Q  x
        //W -> E 1: Q  y

        Range  sn=  new Range(0, Q,0);
        Range we= new Range(0, Q,0);

        for (int i = 0; i < P; i++) {
            if(d[i] == 'N'){
                //find the node which include and then split it, update the later
                //update all
                Range start = null;
                int p = y[i] + 1;
                Range ptr= sn;
                while(ptr!=null){
                    if(ptr.l <= p && ptr.h >= p){
                        //split
                        if(ptr.l == p) {
                            ptr.cnt += 1;
                            start= ptr.next;
                        }
                        else{
                            Range nrr=  new Range(p, ptr.h, ptr.cnt+1);
                            ptr.h = p-1;
                            Range onext = ptr.next;
                            ptr.next = nrr;
                            nrr.next = onext;
                            start = nrr.next;
                        }
                        break;
                    }
                    ptr = ptr.next;
                }

                ptr = start;
                while(ptr != null) {
                    ptr.cnt +=1;
                    ptr = ptr.next;
                }

            }
            else if(d[i] == 'S'){

                Range end = null;
                int p = y[i] - 1;
                Range ptr= sn;
                while(ptr!=null){
                    if(ptr.l <= p && ptr.h >= p){
                        //split
                        if(ptr.h == p) {
                            ptr.cnt += 1;
                            end=ptr;
                        }
                        else{
                            Range nrr=  new Range(p+1, ptr.h, ptr.cnt);
                            ptr.h = p;
                            ptr.cnt+=1;
                            Range onext = ptr.next;
                            ptr.next = nrr;
                            nrr.next = onext;
                            end = ptr;
                        }
                        break;
                    }
                    ptr = ptr.next;
                }

                ptr = sn;
                while(ptr != end) {
                    ptr.cnt +=1;
                    ptr = ptr.next;
                }
            }

            Range dptr = sn;
            String dstr = "SN";
            while(dptr != null){
                dstr = dstr + " -> (" + dptr.l  + " " + dptr.h + " " + dptr.cnt + ")";
                dptr = dptr.next;
            }
            debug(dstr);
        }


        for (int i = 0; i < P; i++) {
            if(d[i] == 'E'){
                //find the node which include and then split it, update the later
                //update all
                Range start = null;
                int p = x[i] + 1;
                Range ptr= we;
                while(ptr!=null){
                    if(ptr.l <= p && ptr.h >= p){
                        //split
                        if(ptr.l == p) {
                            ptr.cnt += 1;
                            start= ptr.next;
                        }
                        else{
                            Range nrr=  new Range(p, ptr.h, ptr.cnt+1);
                            ptr.h = p-1;
                            Range onext = ptr.next;
                            ptr.next = nrr;
                            nrr.next = onext;
                            start = nrr.next;
                        }
                        break;
                    }
                    ptr = ptr.next;
                }

                ptr = start;
                while(ptr != null) {
                    ptr.cnt +=1;
                    ptr = ptr.next;
                }

            }
            else if(d[i] == 'W'){

                Range end = null;
                int p = x[i] - 1;
                Range ptr= we;
                while(ptr!=null){
                    if(ptr.l <= p && ptr.h >= p){
                        //split
                        if(ptr.h == p) {
                            ptr.cnt += 1;
                            end = ptr;
                        }
                        else{
                            Range nrr=  new Range(p+1, ptr.h, ptr.cnt);
                            ptr.h = p;
                            ptr.cnt +=1;
                            Range onext = ptr.next;
                            ptr.next = nrr;
                            nrr.next = onext;
                            end = ptr;
                        }
                        break;
                    }
                    ptr = ptr.next;
                }

                ptr = we;
                while(ptr != end) {
                    ptr.cnt +=1;
                    ptr = ptr.next;
                }
            }

            Range dptr = we;
            String dstr = "WE";
            while(dptr != null){
                dstr = dstr + " -> (" + dptr.l  + " " + dptr.h + " " + dptr.cnt + ")";
                dptr = dptr.next;
            }
            debug(dstr);
        }

        //find the range with max
        int maxV = 0;
        Range ptr = sn;
        while(ptr !=null){
            if(ptr.cnt > maxV){
                r[1] = ptr.l;
                maxV = ptr.cnt;
            }
            ptr = ptr.next;
        }

        maxV = 0;
        ptr = we;
        while(ptr !=null){
            if(ptr.cnt > maxV){
                r[0] = ptr.l;
                maxV = ptr.cnt;
            }
            ptr = ptr.next;
        }

        return r;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(false){

            for (int cc = 0; cc < 1000; cc++) {
                int N=5;
                int Q=10;
                Random r = new Random();
                int[] x = new int[N];
                int[] y = new int[N];
                char[] d = new char[N];
                char[] dd = {'N', 'E', 'S', 'W'};
                for (int i = 0; i < N; i++) {
                    x[i] = r.nextInt(Q+1);
                    y[i] = r.nextInt(Q+1);
                    d[i] = dd[r.nextInt(4)];
                }

                int[] aa = solve(N, Q, x, y, d);
                int[] bb = solveSmall(N, Q, x, y, d);

                if (!(aa[0] == bb[0] && aa[1] == bb[1])){
                    debug(N + " " + Q);
                    for (int i = 0; i < N; i++) {
                        debug(x[i] + " "+ y[i] + " " + d[i]);
                    }
                }
            }


            return;
        }


        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int P = sc.nextInt();
            int Q = sc.nextInt();
            int[] x = new int[P];
            int[] y = new int[P];
            char[] d =new char[P];
            for (int j = 0; j < P; j++) {
                x[j] = sc.nextInt();
                y[j] = sc.nextInt();
                d[j] = sc.nextLine().trim().charAt(0);
            }

            int[] r = solve(P, Q, x, y, d);
            System.out.println(r[0] + " " + r[1]);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

    static class Sum {
        int start;
        int end;
        int val;

        public Sum(int start, int end, int val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }

        public ArrayList<Sum> add(int start2, int end2, int add) {
            ArrayList<Sum> result = new ArrayList<Sum>();

            if (this.start > end2) {   //no intersect before current segment
                result.add(this);
            } else if (this.end < start2) {     // no intersection after
                result.add(this);
            } else if (this.start >= start2 && this.end <= end2) {          //overlap all range
                result.add(new Sum(this.start, this.end, val + add));
            } else if (start2 <= this.start && end2 < this.end) {         //overlap front
                result.add(new Sum(start, end2, val + add));
                result.add(new Sum(end2 + 1, this.end, val));
            } else if (this.start < start2 && end2 < this.end) {            //overlap middle
                result.add(new Sum(start2, end2, val + add));
                result.add(new Sum(this.start, start2 - 1, val));
                result.add(new Sum(end2 + 1, this.end, val));
            } else if (this.start < start2 && this.end <= end2) {           //overlap end
                result.add(new Sum(start, start2-1, val));
                result.add(new Sum(start2, this.end, val + add));
            }

            //if(result.size() == 0)
            //    System.out.format("shit, %d %d %d %d\n", start, end, start2, end2);
            return result;
        }
    }


}


