package GoogleCodeJam.Y2019.Quali.D;

import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

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

    private String flipBits(String str){
        String result = "";
        for(int i=0; i<str.length(); ++i){
            if(str.charAt(i) == '0')
                result += "1";
            else
                result += "0";
        }
        return result;
    }

    private String[] queries16 = {
            "0000000000000000",
            "0000000011111111",
            "0000111100001111",
            "0011001100110011",
            "0101010101010101",
    };

    private String[] getTruncatedQuery(int length){
        String[] result = new String[5];
        for(int i=0; i<5; ++i)
            result[i] = queries16[i].substring(0, length);
        return result;
    }

    private String[] generateQueries(int length){
        String[] result = new String[5];
        for(int i=0; i<5; ++i) result[i] = "";

        String prev_query;
        prev_query = "";

        boolean bitZero = true;

        //segment the result into 32 first
        for(int i=0; i<length; i+=16){

            String[] query = queries16;

            if(!bitZero)
                result[0] += flipBits(query[0]);
            else
                result[0] += query[0];

            for(int j=1; j<5; ++j){
                result[j] += query[j];
            }

            bitZero = !bitZero;
        }

        return result;
    }

    private ArrayList<Integer> inferResult(String[] answers, int n){
        int prev_idx = 0;
        int cur16_addr = 0;
        ArrayList<Integer> result = new ArrayList<>();

        for(int i=1; i<answers[0].length()+1; ++i){
            char cur;
            if(i == answers[0].length())
                cur = '\0';
            else
                cur= answers[0].charAt(i);

            if(cur != answers[0].charAt(i-1)) {
                String[] aa = new String[4];
                for(int j=1; j<5; ++j){
                    aa[j-1] = answers[j].substring(prev_idx, i);
                }

                ArrayList<Integer> temp = processSegment(3, aa);


                /*
                out.println("debug: processSegment");
                for(int p=0; p<4; ++p)
                    out.println(aa[p]);
                out.println("result:");
                for(int p=0; p<temp.size(); ++p)
                    out.print(temp.get(p) + " ");
                out.println();
                */

                for(int j=0; j<temp.size(); ++j)
                    result.add(temp.get(j) + cur16_addr * 16);

                prev_idx = i;
                cur16_addr += 1;
            }
        }

        //last segment can disappear!!
        if(cur16_addr * 16 < n) {
            for(int j=0; j<16; ++j)
                result.add(j + cur16_addr * 16);
        }


        return result;
    }

    private int[] powToLen = new int[]{1, 2, 4, 8, 16};

    private ArrayList<Integer> processSegment(int queryLenPow, String[] answer){
        if(queryLenPow == 0) {
            if(answer[0].length() == 0)
                return new ArrayList<Integer>(Arrays.asList(0, 1));
            else if(answer[0].compareTo("1") == 0)
                return new ArrayList<Integer>(Arrays.asList(0));
            else if(answer[0].compareTo("0") == 0)
                return new ArrayList<Integer>(Arrays.asList(1));
            else
                return new ArrayList<Integer>();
        }
        else if(answer[0].length() == 0){
            ArrayList<Integer> r = new ArrayList<Integer>();
            for(int i=0; i<powToLen[queryLenPow+1]; ++i)
                r.add(i);

            return r;
        }
        else{

            int zeroCount= 0;
            for(int i=0; i<answer[0].length(); ++i) {
                if (answer[0].charAt(i) == '0')
                    zeroCount++;
            }

            String[] answerA = new String[answer.length-1];
            String[] answerB = new String[answer.length-1];

            for(int i=1; i<answer.length; ++i) {
                answerA[i-1] = answer[i].substring(0, zeroCount);
                answerB[i-1] = answer[i].substring(zeroCount);
            }

            ArrayList<Integer> resultA = processSegment(queryLenPow-1, answerA);
            ArrayList<Integer> resultB = processSegment(queryLenPow-1, answerB);

            ArrayList<Integer> result = new ArrayList<>();
            result.addAll(resultA);
            result.addAll(resultB.stream().map(x -> x + powToLen[queryLenPow]).collect(Collectors.toList()));

            return result;
        }
    }

    private boolean solve(){
        //do log 4 because the max broken computer is limited to 15

        int n = sc.nextInt();
        int b = sc.nextInt();
        int f = sc.nextInt();

        String[] queries = generateQueries(n);
        String[] answers = new String[5];

        for(int i=0; i<5; ++i) {
            out.println(queries[i].substring(0, n));
            out.flush();

            String ans = sc.next();
            //out.println("debug: " + ans);
            answers[i] = ans;
        }

        ArrayList<Integer> x = inferResult(answers, n);

        //out.println("debug: answer");
        for(int i=0; i<x.size(); ++i){
            if(x.get(i) < n)
                out.print(x.get(i) + " ");
        }
        out.println();

        out.flush();

        return sc.nextInt() == 1;
    }

    private void writeQueries(String[] x){
        for(int i=0; i<x.length; ++i)
            System.out.println(x[i]);
        System.out.println();
    }

    private void run() throws Exception {

        /*
        String[] test;
        test = generateQueries(5);
        writeQueries(test);
        test = generateQueries(32);
        writeQueries(test);
        test = generateQueries(256);
        writeQueries(test);
        test = generateQueries(500);
        writeQueries(test);
        test = generateQueries(1024);
        writeQueries(test);
        */

        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            boolean result = solve();
            if(!result) break;
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
