package GoogleCodeJam.Y2019.Round1B.A;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
            IN = null;
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

    private int solve(String[] r){
        return 0;
    }



    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int p = sc.nextInt();
            int q = sc.nextInt();

            ArrayList<ArrayList<Sum>> test = new ArrayList<>();
            for (int j=0; j<q+1; ++j) {
                //input[j] = sc.nextLine();
                ArrayList<Sum> qRow = new ArrayList<Sum>();
                qRow.add(new Sum(0, q, 0) );
                test.add(qRow);
            }

            //Row is on X direction which mean west to east = 0 -> q
            for(int j=0; j<p; ++j){
                int x = sc.nextInt();
                int y = sc.nextInt();
                String d = sc.next();

                if(d.compareTo("N") == 0) {
                    for(int k=y+1; k<=q; ++k){
                        ArrayList<Sum> newQRow = new ArrayList<Sum>();

                        for(Sum s: test.get(k)){
                            ArrayList<Sum> b = s.add(0, q, 1);
                            newQRow.addAll(b);
                        }

                        test.set(k, newQRow);
                    }
                }
                else if(d.compareTo("E") == 0) {
                    for(int k=0; k<=q; ++k){
                        ArrayList<Sum> newQRow = new ArrayList<Sum>();

                        for(Sum s: test.get(k)){
                            ArrayList<Sum> b = s.add(x+1, q, 1);
                            newQRow.addAll(b);
                        }

                        test.set(k, newQRow);
                    }
                }
                else if(d.compareTo("S") == 0) {
                    for(int k=y-1; k>=0; --k){
                        ArrayList<Sum> newQRow = new ArrayList<Sum>();

                        for(Sum s: test.get(k)){
                            ArrayList<Sum> b = s.add(0, q, 1);
                            newQRow.addAll(b);
                        }

                        test.set(k, newQRow);
                    }
                }
                else if(d.compareTo("W") == 0) {
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

            out.format("%d %d\n", max_x, max_y);
        }
        sc.close();
        out.close();
    }

    /*
    public void run2() {
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int p = sc.nextInt();
            int q = sc.nextInt();

            int top, left, right, bottom;
            top = 0; left = 0; right = q; bottom = q;

            for(int j=0; j<p; ++j) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                String d = sc.next();

                if(d.compareTo("N") == 0){
                    //new square (0,q), (q, y-1)
                }
                else if(d.compareTo("E") == 0){
                    //new square (), ()
                }
                else if(d.compareTo("S") == 0){
                    //new square (0,y+1), (q, q)
                }
                else if(d.compareTo("W") == 0){

                }
            }
        }
    }
    */

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}

class Sum {
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