package GoogleCodeJam.Y2008.Round1B.C;

/*
    #SquareRootDecomposition
    #JosephusProblem

    Small AC, Big AC
 */

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
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1B\\C\\C-test.in";
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

    //Josephus problem O(nK)
    //Small AC, Big AC
    private int[] solveBig(int K, int[] nq){

        for (int i = 0; i < nq.length; i++) {
            nq[i] = nq[i]-1;
        }
        int[] ans =new int[nq.length];

        for (int i = 0; i <K; i++) {
            int s = i+1;
            int r = K-i;
            for (int j = 0; j < nq.length; j++) {
                if(ans[j] != 0) continue;
                
                int a = nq[j] - s;
                while(a < 0) a +=r;
                if(a < r-1)
                    nq[j] = a;
                else {
                    nq[j] = -1;
                    ans[j] = i + 1;
                }
            }

        }

        return ans;
    }

    //Josephus problem O(K^2)
    //Small AC, Big TLE
    private int[] solveBig1stAttempt(int K, int[] nq){

        int[] deck =buildDeckBig(K);

        HashMap<Integer, List<Integer>> hmap = new HashMap<>();
        for(int i=0; i<nq.length; ++i) {
            if(hmap.containsKey(nq[i]-1))
                hmap.get(nq[i]-1).add(i);
            else {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(i);
                hmap.put(nq[i]-1, a);
            }
        }
        int[] ans = new int[nq.length];

        for(int i=0; i<K; ++i) {
            if (hmap.containsKey(deck[i])) {
                for (Integer ai: hmap.get(deck[i]))
                ans[ai] = i + 1;
            }
        }

        return ans;
    }

    private int[] buildDeckBig(int K){
        int[] deck = new int[K];
        for(int i=0; i<K; ++i)
            deck[i] = (i)% (K-i);

        for(int i=0; i<K-1; ++i){
            //convert the index back to
            int s = K-i-1;
            int r = i+2;
            for (int j = K-i-1; j < K; j++) {
                deck[j] = (deck[j] + s) % r;
            }

        }

        return deck;
    }

    //15pt.
    private int[] solveSmall(int K, int[] nq){
        int[] result = new int[nq.length];

        int[] deck = buildDeck(K);

        //each query can be queried by K
        for(int i=0; i<nq.length; ++i)
            result[i] = deck[nq[i]-1];

        return result;
    }

    private int[] buildDeck(int K){
        int[] deck = new int[K];

        int segment = (int) Math.floor(Math.sqrt(K));
        int[] segmentEmpty;
        segmentEmpty = new int[K % segment== 0 ? K/segment: K/segment+1];

        for(int i=0; i<segmentEmpty.length; ++i)
            segmentEmpty[i] = segment;
        if(K%segment > 0)
            segmentEmpty[segmentEmpty.length-1] = K% segment;

        deck[0] = 1;
        segmentEmpty[0] -=1;
        int previousPos = 0;
        for(int i=2; i<=K; ++i){

            //System.out.println("Curr no: " + i);

            int step = 0;
            int previousSegment = previousPos / segment;
            boolean assigned = false;

            //System.out.println("Previous segment: " + previousSegment);

            for (int j = previousPos+1; j < Math.min(K, (previousSegment+1)*segment) ; j++) {
                if(deck[j] == 0) step++;
                if(step == i){
                    deck[j] = i;
                    //System.out.println("A: no " + i + " at loc " + j);
                    segmentEmpty[previousSegment] -=1;
                    previousPos = j;
                    assigned = true;
                    break;
                }
            }

            if(assigned) continue;

            int curSegment = (previousSegment + 1) % segmentEmpty.length;
            while(true){

                if(step+segmentEmpty[curSegment] >= i){
                    //System.out.println(i + " Locate segment " + curSegment);

                    for (int j = segment * curSegment; j < Math.min(K, segment * (curSegment+1)); j++) {
                        if(deck[j] == 0) step++;
                        if(step == i){
                            deck[j] = i;
                            //System.out.println("B: no " + i + " at loc " + j);
                            segmentEmpty[curSegment] -=1;
                            previousPos = j;
                            break;
                        }
                    }
                    break;
                }
                else {
                    //System.out.println(i + " Skip segment " + curSegment);
                    step = step + segmentEmpty[curSegment];
                    curSegment = (curSegment + 1) % segmentEmpty.length;
                }
            }
        }

        return deck;
    }

    //too slow
    private int[] solveSmallSlow(int K, int[] nq) {
        int[] result = new int[nq.length];

        int[] deck = buildDeck2(K);

        //each query can be queried by K
        for(int i=0; i<nq.length; ++i)
            result[i] = deck[nq[i]-1];

        return result;
    }

    private int[] buildDeck2(int K){
        int[] deck = new int[K];

        int curpos = 0;
        deck[0] = 1;
        for(int i=2; i<=K; ++i){
            int jump = 0;
            while(jump < i){
                curpos=(curpos+1) % K;
                while(deck[curpos] != 0) curpos=(curpos+1) % K;
                jump+=1;
            }
            deck[curpos] = i;
        }

        return deck;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(false){
            int[] a = {6};

            for (int j = 0; j < a.length; j++) {
                int[] deck = buildDeckBig(a[j]);
                for (int i = 0; i < deck.length; i++) {
                    System.out.print(deck[i] + " ");
                }

                System.out.println();
            }

            return;
        }

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            int n = sc.nextInt();
            int nq = sc.nextInt();
            int[] query = new int[nq];
            for (int j = 0; j < nq; j++) {
                query[j] = sc.nextInt();
            }
            int[] r = null;
            //r= solveSmall(n, query);
            r=solveBig(n,query);

            for(int j=0; j<r.length; ++j)
                System.out.print(" " + r[j]);
            System.out.println();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}

