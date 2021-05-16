package GoogleCodeJam.Y2012.Round1A.B;

import javax.swing.*;
import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2012\\Round1A\\B\\B-test.in";
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

    boolean debugflag = false ;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }

    private void debug(Object... s){
        if(debugflag) {
            //System.out.println(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i].toString() + " ");
            }
            System.out.println("\033[0;34m" + sb.toString() + "\033[0;30m");
        }
    }

    static class Pair implements Comparable<Pair>{
        int a, b;
        public Pair(int a, int b){
            this.a = a; this.b = b;
        }


        @Override
        public int compareTo(Pair o) {
            if(b==o.b) return a-o.a;
            return b-o.b;
        }
    }

    int[] a = new int[1001];
    int[] b =new int[1001];
    int[] sa = new int[1001];
    int[] sb =new int[1001];
    HashMap<Integer, Integer> ra = new HashMap<>();
    HashMap<Integer, Integer> rb = new HashMap<>();
    int N;

    static class Level{
        int easy;
        int hard;
        boolean completedFirst;
        int levelNo = 0;
        public Level(int easy, int hard){
            this.easy = easy;
            this.hard =hard;
            completedFirst = false;
        }
    }

    private int solve(int N){
        Level[] levels= new Level[N];

        for (int i = 0; i < N; i++) {
            levels[i] = new Level(sa[i], sb[i]);
            levels[i].levelNo = i;
        }

        int star=  0;
        int bptr = N-1;
        int step=0;
        while(true){

            if(bptr == -1)
                return step;

            boolean found =false;
            //2 star greedy
            for (int i = 0; i <= bptr; i++) {
                if(levels[i].hard <= star && !levels[i].completedFirst){

                    debug(levels[i].levelNo, "2", star);
                    star+= levels[i].completedFirst ? 1 : 2;
                    levels[i] = levels[bptr];
                    found=true;
                    bptr--;
                    ++step;
                }
            }

            if(found) continue;

            //1 star
            found=false;
            if(bptr==-1) return step;
            for (int i = 0; i <= bptr; i++) {
                if(levels[i].hard <= star){

                    debug(levels[i].levelNo, "2-", star);
                    star+= levels[i].completedFirst ? 1 : 2;
                    levels[i] = levels[bptr];
                    found=true;
                    bptr--;
                    ++step;
                }
            }

            if(found) continue;

            if(bptr==-1) return step;

            int maxHardPossible = -1;
            for (int j = 0; j <= bptr; j++) {
                if(levels[j].easy <= star && !levels[j].completedFirst){
                    if(maxHardPossible==-1 || levels[maxHardPossible].hard < levels[j].hard)
                        maxHardPossible = j;
                }
            }

            if(maxHardPossible != -1){
                levels[maxHardPossible].completedFirst = true;
                debug(levels[maxHardPossible].levelNo, "1", star);
                star +=1;
                ++step;
            }
            else
                return -1;

        }

    }

    private int solveShit(int N) {
        int ans = 0;
        //sort it with b, if cannot proceed to next b, then try a until we get to next b
        this.N = N;

        Integer[] tt = new Integer[N];
        for (int i = 0; i < N; i++) {
            tt[i] = i;
        }

        Arrays.sort(tt, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(sb[o1] == sb[o2]){
                    return sa[o2]-sa[o1];
                }
                return sb[o1]-sb[o2];
            }
        });
        for (int i = 0; i < N; i++) {
            b[i] = tt[i];
            rb.put(tt[i], i);
        }


        //the whole sorting concept is wrong... choose a task with highest b, does not mean a small a...
        Arrays.sort(tt, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(sa[o1] == sa[o2]) {
                    //bug 1
                    return rb.get(o2) - rb.get(o1);
                }
                return sa[o1]-sa[o2];
            }
        });
        for (int i = 0; i < N; i++) {
            a[i] = tt[i];
            ra.put(tt[i], i);
        }

        ans = helper(0, 0, 0);
        return ans;
    }

    //int[][][] dp = new int[1001][1001][1001];

    private int helper(int star, int aptr, int bptr){
        //debug(star + " "+ a[aptr] + " " + b[bptr]);
        int step = 0;
        int newStar = 0;
        if(bptr== N) return 0;
        if(star >= sb[b[bptr]]) {
            int g = b[bptr];
            if(ra.get(g) < aptr) newStar = 1; else newStar =2;
            step = addOne(helper(star + newStar, aptr, bptr + 1));
        }
        else{
            if(aptr==N) return -1;
            int g = a[aptr];
            if(rb.get(g) < bptr)
                step = helper(star, aptr+1, bptr);
            else if(star >= sa[a[aptr]])
                step = addOne(helper(star + 1, aptr + 1, bptr));
            else
                step = -1;
        }

        return step;
    }

    private int addOne(int a){
        if(a != -1) return a+1;
        else return -1;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N  = sc.nextInt();
            for (int j = 0; j <N; j++) {
                sa[j] = sc.nextInt();
                sb[j]= sc.nextInt();
            }
            int r = solve(N);
            if(r ==-1) out.println("Too Bad");
            else out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
