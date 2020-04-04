package GoogleCodeJam.Y2019.Round1A.A;

import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;

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

    String[][] test = new String[1001][1001];
    private int solve(String[] r){
        String suffix;
        int ri_len, rj_len;
        TreeMap<String, Set<Integer>> lookupTable = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) return -1;
                else if(o1.length() <o2.length()) return 1;
                else{
                    return o1.compareTo(o2);
                }
            }
        });

        for(int i=0; i<r.length; ++i){
            for(int j=i+1; j<r.length; ++j){
                ri_len = r[i].length();
                rj_len = r[j].length();
                int k;
                for(k=0; k<Math.min(r[i].length(), r[j].length()) ; ++k){
                    if(r[i].charAt(ri_len - k -1) != r[j].charAt(rj_len - k-1))
                        break;
                }
                suffix = r[i].substring(ri_len-k);

                if(suffix.length() > 0) {

                    if (lookupTable.containsKey(suffix)) {
                        Set s = lookupTable.get(suffix);
                        s.add(i);
                        s.add(j);
                    } else {
                        Set s = new TreeSet();
                        s.add(i);
                        s.add(j);
                        lookupTable.put(suffix, s);
                    }
                }
            }
        }

        int ans = 0;

        /*
        System.out.println("debug");
        for(String s : lookupTable.keySet())
            System.out.println(s);
        System.out.println("end debug");
        */

        //now get the longest first
        while(!lookupTable.isEmpty()){
            String key = lookupTable.firstKey();
            Set<Integer> s = lookupTable.get(key);

            if(s.size() < 2)
                lookupTable.remove(key);
            else{
                lookupTable.remove(key);

                Set<Integer> removeS = new TreeSet<Integer>();
                int cnt= 0;
                for(Integer si : s) {
                    removeS.add(si);
                    cnt ++;
                    if(cnt >= 2) break;
                }

                //remove these 2 numbers from all subset S
                String subKey = key.substring(1);
                while(subKey.length() > 0){
                    if(lookupTable.containsKey(subKey)){
                        lookupTable.get(subKey).removeAll(removeS);
                    }
                    subKey = subKey.substring(1);
                }

                String newKey = key.substring(1);
                if(newKey.length() > 0) {
                    Set<Integer> newS = new TreeSet<Integer>(s);
                    newS.removeAll(removeS);

                    if (lookupTable.containsKey(newKey)) {
                        Set<Integer> origS = lookupTable.get(newKey);
                        origS.addAll(newS);
                    } else {
                        lookupTable.put(newKey, newS);
                    }
                }

                ans = ans+2;
            }
        }

        return ans;
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int p = sc.nextInt();
            sc.nextLine();
            String[] input = new String[p];
            for (int j=0; j<p; ++j)
                input[j] = sc.nextLine();

            out.println(solve(input));
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
