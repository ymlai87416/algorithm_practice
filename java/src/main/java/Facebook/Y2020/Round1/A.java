package Facebook.Y2020.Round1;

import Template.Solution;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class A {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2020\\Round1\\perimetric_chapter_1_input.txt";
            OUT = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\Facebook\\Y2020\\Round1\\perimetric_chapter_1_output.txt";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            if(OUT == null)
                out = new PrintStream(System.out);
            else out = new PrintStream(OUT);
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

    int[] Lall = new int[1000000];
    int[] Hall = new int[1000000];
    private int solve(int N, int K, int W, int[] L, int AL, int BL, int CL, int DL, int[] H, int AH, int BH, int CH, int DH) {
        int ans = 0;

        //get all the room L, W, and H
        //keep track of the last room
        int Li_2 = 0, Li_1 = 0, Hi_2 = 0, Hi_1 = 0;
        int Li = 0, Wi = 0, Hi = 0;

        int Pv=0, Pf=0;
        long product = 0;


        for (int i = 0; i < N; i++) {
            if(i <= K){
                Lall[i] = L[i];
                Hall[i] = H[i];
            }
            else{
                Lall[i] = (AL * Li_2 + BL * Li_1 + CL) % DL;
                Hall[i] = (AH * Hi_2 + BH * Hi_1 + CH) % DH;
            }

            //now push the shit
            Li_2 = Li_1;
            Hi_2 = Hi_1;
            Li_1 = Li;
            Hi_1 = Hi;
        }

        Node root = new Node(Lall[0], Lall[N]+W, 0);

        for (int i = 0; i < N; i++) {
            root.addInterval(Lall[i], Lall[i]+W, Hall[i]);
        }

        return root.perimeter;
    }


    /*
    we have a sliding windows of L and L+W, within we know range and height
    if the range have some part outside, just split it
     */
    private long solveDequeue(int N, int K, int W, int[] L, int AL, int BL, int CL, int DL, int[] H, int AH, int BH, int CH, int DH){
        ArrayDeque<Rect> rects = new ArrayDeque<>();
        long pi = 0; //
        long result = 1;

        long Li_2 = 0, Li_1 = 0, Hi_2 = 0, Hi_1 = 0;
        long Li = 0, Wi = 0, Hi = 0;

        for (int i = 0; i < N; i++) {
            if(i < K){
                Li = L[i];
                Hi = H[i];
                Wi = W;
            }
            else{
                Li = (AL * Li_2 + BL * Li_1 + CL) % DL +1;
                Hi = (AH * Hi_2 + BH * Hi_1 + CH) % DH + 1;
                Wi = W;
            }
            
            //now calculate the increase of perimeter for each rectangle if it include in Pi
            //keep all last i rectangle is oki
            if(rects.size() > W){
                rects.pop();
            }

            Rect tallestRect = null;
            Iterator<Rect> it = rects.iterator();
            long delta;
            boolean overlapped = false;

            while(it.hasNext()) {
                Rect curr = it.next();
                //what is the tallest rectangle at the starting point
                if(curr.L + curr.W < Li)
                    continue;
                overlapped = true;

                if(tallestRect == null)
                    tallestRect = curr;
                else if(tallestRect.H < curr.H)
                    tallestRect = curr;

                //if it is tallest, then add
                //if it is shorter than all rectangle cover point Li, change only in width

                //if it is not covered, then the delta is the perimeter
            }

            if(!overlapped){
                delta = 2 * (Wi + Hi);
            }
            else{
                if(Hi > tallestRect.H){
                    delta = 2 * (Li - Li_1) + 2 * (Hi - tallestRect.H);
                }
                else{
                    delta = 2 * (Li - Li_1);
                }
            }

            pi = pi + delta;
            pi = pi % 1000000007;
            debug("pi: " + pi);

            result = (result * pi ) %  1000000007;

            rects.offerLast(new Rect(Hi, Li, Wi));

            //now push the shit
            Li_2 = Li_1;
            Hi_2 = Hi_1;
            Li_1 = Li;
            Hi_1 = Hi;
        }

        //then sum the whole stuff here and give the answer
        return result;
    }

    private long solveA1(){
        int N = sc.nextInt();
        int K = sc.nextInt();
        int W = sc.nextInt();
        int[] L = new int[K];
        for (int j = 0; j < K; j++) {
            L[j] = sc.nextInt();
        }
        int AL = sc.nextInt();
        int BL = sc.nextInt();
        int CL = sc.nextInt();
        int DL = sc.nextInt();

        int[] H = new int[K];
        for (int j = 0; j < K; j++) {
            H[j] = sc.nextInt();
        }

        int AH = sc.nextInt();
        int BH = sc.nextInt();
        int CH = sc.nextInt();
        int DH = sc.nextInt();

        //int r = solve(N, K, W, L, AL, BL, CL, DL, H, AH, BH, CH, DH);
        long r = solveDequeue(N, K, W, L, AL, BL, CL, DL, H, AH, BH, CH, DH);

        return r;
    }

    private long solveA2(){
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] L = new int[K];
        for (int j = 0; j < K; j++) {
            L[j] = sc.nextInt();
        }
        int AL = sc.nextInt();
        int BL = sc.nextInt();
        int CL = sc.nextInt();
        int DL = sc.nextInt();

        int[] W = new int[K];
        for (int j = 0; j < K; j++) {
            W[j] = sc.nextInt();
        }

        int AW = sc.nextInt();
        int BW = sc.nextInt();
        int CW = sc.nextInt();
        int DW = sc.nextInt();

        int[] H = new int[K];
        for (int j = 0; j < K; j++) {
            H[j] = sc.nextInt();
        }

        int AH = sc.nextInt();
        int BH = sc.nextInt();
        int CH = sc.nextInt();
        int DH = sc.nextInt();

        long r = solveUF(N, K, L, AL, BL, CL, DL, W, AW, BW, CW, DW, H, AH, BH, CH, DH);

        return r;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            //long r = solveA1();
            long r = solveA2();

            out.println(r);
        }
        sc.close();
        out.close();
    }

    //solve A2
    private long solveUF(int N, int K, int[] L, int AL, int BL, int CL, int DL,
                         int[] W, int AW, int BW, int CW, int DW, int[] H, int AH, int BH, int CH, int DH){

        long pi = 0; //
        long result = 1;

        long Li_2 = 0, Li_1 = 0, Hi_2 = 0, Hi_1 = 0, Wi_2 = 0, Wi_1 = 0;
        long Li = 0, Wi = 0, Hi = 0;

        //treemap -> region left => how region and parameter
        TreeMap<Integer, Region> regions = new TreeMap<>();
        for (int i = 0; i < N; i++) {

            if(i < K){
                Li = L[i];
                Hi = H[i];
                Wi = W[i];
            }
            else{
                Li = (AL * Li_2 + BL * Li_1 + CL) % DL +1;
                Hi = (AH * Hi_2 + BH * Hi_1 + CH) % DH + 1;
                Wi = (AW * Wi_2 + BW * Wi_1 + CW) % DW + 1;
            }

            //find a list of region

            var subMap = regions.subMap((int) Li, true, (int)(Li+Wi), true);

            if(subMap.size() == 0){
                //regions.put((int)Li, new Region());
            }
            if(subMap.size() == 1){
                //if only 1

                //subMap.

            }else {
                //if multiple, merge the region and delete not the left most



            }

            //add delta to the pi and calc the result


            //now push the shit
            Li_2 = Li_1;
            Hi_2 = Hi_1;
            Li_1 = Li;
            Hi_1 = Hi;
        }

        return result;
    }

    public static void main(String args[]) throws Exception {
        new A().run();
    }
}

class Region{
    public long L;
    public long perimeter;

    public Region(long L, long perimeter){

    }
}

class Rect{
    public long H;
    public long L;
    public long W;

    public Rect(long H, long L, long W){
        this.H = H; this.L = L; this.W = W;
    }

}

//interval tree may not work nlogn => 100K input, but now it is 1M
//space complexity is great in this implementation
class Node{
    public int left;
    public int right;
    public int perimeter;
    public int height;  //only leave have height > 0, else -1;
    public int leftmostH;
    public int rightmostH;
    public Node nodeL;
    public Node nodeR;

    public Node(int left, int right, int height){
        //a simple rectangle
        this.left = left;
        this.right = right;
        this.height = height;
        this.leftmostH = height;
        this.rightmostH = height;
        this.perimeter = (this.right-this.left) * this.height * 2;
    }

    public void addInterval(int nLeft, int nRight, int nHeight){
        //must be within the interval
        if(!within(this.left, this.right, nLeft, nRight)) System.out.println("shit");

        if(height != -1){
            //break the interval
            int h = this.height;
            if(this.left == nLeft && this.right == nRight){
                this.height = Math.max(this.height, nHeight);
                this.leftmostH = this.rightmostH = this.height;
                this.perimeter = (this.right-this.left) * this.height * 2;
            }
            else if(this.left < nLeft){
               //break the node into 2
                this.nodeL = new Node(this.left, nLeft, this.height);
                this.nodeR = new Node(nLeft, right, this.height);

                this.nodeR.addInterval(nLeft, nRight, nHeight);

                //update the perimeter
                this.height = -1;
                this.leftmostH = this.nodeL.leftmostH;
                this.rightmostH = this.nodeR.rightmostH;
                this.perimeter = this.nodeL.perimeter + this.nodeR.perimeter
                        - Math.min(this.nodeL.rightmostH, this.nodeR.leftmostH);
            }
            else{
                //break the node into 2
                this.nodeL = new Node(this.left, nRight, this.height);
                this.nodeR = new Node(nRight, this.right, this.height);

                this.nodeL.addInterval(nLeft, nRight, nHeight);

                //update the perimeter
                this.height = -1;
                this.leftmostH = this.nodeL.leftmostH;
                this.rightmostH = this.nodeR.rightmostH;
                this.perimeter = this.nodeL.perimeter + this.nodeR.perimeter
                        - Math.min(this.nodeL.rightmostH, this.nodeR.leftmostH);
            }
        }
        else {

            if (nodeL != null && overlap(nodeL.left, nodeL.right, nLeft, nRight)) {
                this.nodeL.addInterval(Math.max(nodeL.left, nLeft), Math.min(nodeL.right, nRight), nHeight);
            }
            if (nodeR != null && overlap(nodeR.left, nodeR.right, nLeft, nRight)) {
                this.nodeR.addInterval(Math.max(nodeR.left, nLeft), Math.min(nodeR.right, nRight), nHeight);
            }

            //update the perimeter
            this.leftmostH = this.nodeL.leftmostH;
            this.rightmostH = this.nodeR.rightmostH;
            this.perimeter = this.nodeL.perimeter + this.nodeR.perimeter
                    - Math.min(this.nodeL.rightmostH, this.nodeR.leftmostH);
        }
    }

    public boolean overlap(int l1, int r1, int l2, int r2){
        return l2 < r1;
    }

    public boolean within(int l1, int r1, int l2, int r2){
        return l1 <= l2 && r2 <= r1;
    }

    public int[] walkInterval(){
        return null;
    }
}