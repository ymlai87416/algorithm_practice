/**
 * Created by Tom on 9/4/2016.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            //IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\A\\A-test.in";
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

    private int[] solve(int N) {
        boolean hasAns = false;

        cacheQuery = new HashMap<String, Integer>();
        
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = i+1;
        }
        try {
            pivot(ans, 0, N);
        }
        catch(Exception ex){
            //limit exceed, just output the result
            if(ex.getMessage().compareTo("invalid query")== 0)
                System.exit(0);
        }


        return ans;
    }

    private void remapIndex(int[] ans){
        for (int i = 0; i < ans.length; i++) {
            temp[ans[i]-1] = i+1;
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] = temp[i];
        }
    }

    int[] debug;

    private void solveDebug() {
        int T = 100;
        int N = 50;
        Q = 17000;

        for (int t = 0; t < T; t++) {

            System.out.println("Test case: " + t);

            debug = new int[N];
            int[] finalAns = new int[N];
            List<Integer> shuffle = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                shuffle.add(i+1);
            }
            Collections.shuffle(shuffle);
            for (int i = 0; i < N; i++) {
                debug[i] =shuffle.get(i);
            }

            for (int i = 0; i < N; i++) {
                finalAns[i] = debug[i];
            }
            remapIndex(finalAns);

            System.out.println("Answer: ");
            System.out.println(formatArray(debug, 0, N));
            
            int[] ans = new int[N];
            for (int i = 0; i < N; i++) {
                ans[i] = i+1;
            }
            try {
                pivot(ans, 0, N);

                boolean correct = true;
                for (int i = 0; i < N; i++) {
                    if(ans[i] != finalAns[i]){
                        correct =false;
                    }
                }

                if(!correct) {
                    correct = true;
                    for (int i = 0; i < N; i++) {
                        if (ans[i] != finalAns[N - 1 - i]) {
                            correct = false;
                        }
                    }
                }

                if(!correct){
                    System.out.println("Shit not correct!!!");

                    System.out.println("Computed result: ");
                    System.out.println(formatArray(ans, 0, N));
                }
                else
                    System.out.println("Correct!!!");

            }
            catch(Exception ex){
                //limit exceed, just output the result
                if(ex.getMessage().compareTo("shit, no quota") == 0){
                    System.out.println("shit, no quota");
                }
                else if(ex.getMessage().compareTo("invalid query")== 0) {
                    System.out.println("invalid query");
                }
                else
                    ex.printStackTrace();
            }
            
            
        }
    }

    private String formatArray(int[] ans, int s, int e){
        StringBuilder sb = new StringBuilder();
        for (int i = s; i < e; i++) {
            if(i == s) sb.append(ans[i]);
            else sb.append(" " + ans[i]);
        }
        return sb.toString();
    }

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) System.out.println(s);
    }

    /*
    partition (arr[], low, high)
    {
        // pivot (Element to be placed at right position)
        pivot = arr[high];

        i = (low - 1)  // Index of smaller element and indicates the
                       // right position of pivot found so far

        for (j = low; j <= high- 1; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;    // increment index of smaller element
                swap arr[i] and arr[j]
            }
        }
        swap arr[i + 1] and arr[high])
        return (i + 1)
    }

     */

    int[] temp = new int[51];

    private int query(int a, int b, int c) throws Exception{

        //query cache first
        int[] qs = new int[3];
        qs[0] = a; qs[1] = b; qs[2] = c;
        Arrays.sort(qs);
        String qq = qs[0]+" "+qs[1] + " " + qs[2];
        if(cacheQuery.containsKey(qq))
            return cacheQuery.get(qq);
        else{
            if(Q == 0) throw new Exception("shit, no quota");
            --Q;
            int r;
            System.out.println(a + " " + b + " " + c);
            System.out.flush();
            //larger when pivot is median
            r = sc.nextInt();

            cacheQuery.put(qq, r);

            if(r == -1) throw new Exception("invalid query");
            return r;
        }        
    }

    private int queryX(int a, int b, int c) throws Exception{
        if(Q == 0) throw new Exception("shit, no quota");
        --Q;
        debug("\033[0;32m" + a + " " + b + " " + c + "\033[0;30m");
        --a; --b; --c;
        if(a == b || b==c || a==c) return -1;
        int imin, imax;


        int r = 0;
        imin = Math.min(debug[a], Math.min(debug[b], debug[c]));
        imax = Math.max(debug[a], Math.max(debug[b], debug[c]));

        if(debug[a] != imin && debug[a] != imax) r= a+1;
        if(debug[b] != imin && debug[b] != imax) r= b+1;
        if(debug[c] != imin && debug[c] != imax) r= c+1;

        debug("\033[0;32m" + r + "\033[0;30m");
        return r;
    }


    private int pivot(int[] ans, int s, int e) throws Exception{
        debug("pivot:  " + s + " " + e);
        //random
        //find left, find right
        if(e-s==1) return s;
        if(e-s==2) return s;

        //select a pivot with at least one right
        Random r = new Random();
        int pivot = r.nextInt(e-s)+s;
        int nn;

        //move pivot to end
        swap(ans, pivot, e-1);
        int i=s, j=s;

        //init setting is i=s-1, j=s;
        //assume i < pivot, then it is the setting.
        for(j=s+1; j< e-1; ++j) {
            nn = query(ans[i], ans[j], ans[e-1]);
            if(nn != ans[e-1]){
                i++;
                swap(ans, i, j);
            }
        }

        swap(ans, e-1, i+1);
        boolean reverse;
        //pivot now at i+1
        int leftStart = s;
        int leftEnd = i+1;

        int pl = -1, pr = -1;

        //debug
        debug("D pivot at " + (i+1) + " array: ");
        debug(formatArray(ans, s, e));

        if(leftEnd - leftStart > 0) {
            pl = pivot(ans, leftStart, leftEnd);
            reverse = false;
            if(leftEnd - leftStart > 1) {
                nn = query(ans[i-1], ans[i], ans[i+1]);
                reverse = nn != ans[i];

                if (reverse)
                    debug("D left reverse detected.");
                else
                    debug("D left no reverse.");

                if (reverse) {
                    //swapPivot(ans, pl, leftStart, leftEnd);
                    reverse(ans, leftStart, leftEnd);
                }
            }
        }

        int rightStart = i+2;
        int rightEnd = e;

        if (rightEnd - rightStart > 0) {
            reverse= false;
            pr = pivot(ans, rightStart, rightEnd);

            if(rightEnd - rightStart > 1) {
                nn = query(ans[i+1], ans[i+2], ans[i+3]);
                reverse = nn != ans[i+2];

                if (reverse)
                    debug("D right reverse detected.");
                else
                    debug("D right no reverse.");

                if (reverse) {
                    reverse(ans, rightStart, rightEnd);
                }
            }

        }

        return i+1;
    }

    /*
        backup here

        if (pl != i) {
                    int lmid = i;
                    System.out.println(ans[pl] + " " + ans[i + 1] + " " + ans[lmid]);
                    System.out.flush();
                    nn = sc.nextInt();
                    if (nn != ans[lmid]) reverse = true;
                } else {
                    int lmid = pl - 1;
                    System.out.println(ans[pl] + " " + ans[i + 1] + " " + ans[lmid]);
                    System.out.flush();
                    nn = sc.nextInt();
                    if (nn != ans[pl]) reverse = true;
                }

        if (pr != i + 2) {
                    int rmid = i + 2;
                    System.out.println(ans[pr] + " " + ans[i + 1] + " " + ans[rmid]);
                    System.out.flush();
                    nn = sc.nextInt();
                    if (nn != ans[rmid]) reverse = true;
                } else {
                    int rmid = pr + 1;
                    System.out.println(ans[pr] + " " + ans[i + 1] + " " + ans[rmid]);
                    System.out.flush();
                    nn = sc.nextInt();
                    if (nn != ans[pr]) reverse = true;
                }
     */

    
    private void reverse(int[] ans, int s, int e){
        for (int i = s, j=e-1; i <j; i++, --j) {
            int t = ans[i];
            ans[i] = ans[j];
            ans[j] = t;
        }
    }

    private void swap(int[] ans, int i, int j){
        int t = ans[i];
        ans[i] = ans[j];
        ans[j] = t;
    }

    int Q;

    HashMap<String, Integer> cacheQuery;

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(false){
            solveDebug();
            return;
        }

        int t = sc.nextInt();
        int N = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            //out.print("Case #" + i + ": ");
            
            int[] ans = solve(N);
            for (int j = 0; j < N; j++) {
                if(j==0) System.out.print(ans[j]);
                else System.out.print(" "+ans[j]);
            }
            System.out.println();

            int jr = sc.nextInt();
            if(jr != 1)
                break;
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}