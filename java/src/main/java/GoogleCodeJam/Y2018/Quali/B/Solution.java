package GoogleCodeJam.Y2018.Quali.B;

import java.io.File;
import java.io.PrintStream;
import java.text.Collator;
import java.util.*;

/**
 * Created by ymlai on 8/4/2018.
 */
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

            //IN = "B-test.in";
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

    private void troubleSort(List<Integer> list){

        boolean done = false;
        while(!done){
            done = true;

            for(int i=0; i<list.size()-2; ++i){
                if(list.get(i) > list.get(i+2)){
                    done = false;
                    Integer temp =  list.get(i);
                    list.set(i, list.get(i+2));
                    list.set(i+2, temp);
                }
            }
        }

        out.println("DEBUG");
        for(int i=0; i<list.size(); ++i){
            out.print(list.get(i) + " ");
        }
        out.println();

    }

    private void solve(int r, List<Integer> odd, List<Integer> even){
        List<Integer> all_list;

        /*all_list = new ArrayList<Integer>(odd.size() + even.size());
        for(int i=0; i<odd.size(); ++i){
            all_list.add(odd.get(i));
            if( even.size() > i)
                all_list.add(even.get(i));
        }
        /troubleSort(all_list);*/

        Collections.sort(odd);
        Collections.sort(even);

        all_list = new ArrayList<Integer>(odd.size() + even.size());
        for(int i=0; i<odd.size(); ++i){
            all_list.add(odd.get(i));
            if( even.size() > i)
                all_list.add(even.get(i));
        }

        int allOK = -1;

        for(int i=0; i<all_list.size()-1; ++i){
            if(all_list.get(i) > all_list.get(i+1)) {
                allOK = i;
                break;
            }
        }

        if(allOK == -1)
            out.println("OK");
        else
            out.println(allOK);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int r = sc.nextInt();
            boolean odd_t = true;
            ArrayList<Integer> odd = new ArrayList<Integer>(r/2+1);
            ArrayList<Integer> even= new ArrayList<Integer>(r/2+1);
            for(int j=0; j<r; ++j){
                if(odd_t)
                    odd.add(sc.nextInt());
                else
                    even.add(sc.nextInt());
                odd_t = !odd_t;
            }

            solve(r, odd, even);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}

