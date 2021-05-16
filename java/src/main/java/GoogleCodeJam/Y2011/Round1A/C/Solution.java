package GoogleCodeJam.Y2011.Round1A.C;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    //very difficult. next later
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1A\\C\\C-test.in";
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

    ArrayList<Card> deck;

    private int solveSmall(ArrayList<Card> deck, int deckPtr) {
        int ans = 0;

        //add as many turns as possible? without running out card on hand?
        //given a limit T, what you choice?

        this.deck = deck;

        ans = helper(deckPtr, 1);

        return ans;
    }

    private int helper(int deckptr, int turnLeft){

        int score = 0;
        if(turnLeft == 0) return 0;

        //discard all the t card
        for (int i = 0; i < deckptr; i++) {
            Card card = deck.get(i);
            if (card.t != 0) {
                turnLeft += card.t - 1;

                deckptr = Math.min(deckptr + card.c, deck.size());
                score += card.s;
            }
        }

        int ans = 0;
        int deckptr2 = deckptr;
        while(true){
            //now find a place where turnleft < then the number of card
        }
    }

    private int findMaxScore(int deckptr, int turnLeft){
        //remove all t

        return 0;
    }

    //record the max if I decide not to draw more than i and left turn j.
    int[][] dp = new int[81][324000];

    static class Card{
        int c;
        int s;
        int t;
        public Card(int c, int s, int t){
            this.c = c; this.s = s; this.t = t;
        }
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            int N = sc.nextInt();

            ArrayList<Card> deck = new ArrayList<>();

            int cc, cs, ct;
            for (int j = 0; j < N; j++) {
                cc = sc.nextInt();
                cs = sc.nextInt();
                ct = sc.nextInt();

                Card c = new Card(cc, cs, ct);
                deck.add(c);
            }

            int deckPtr = N;

            int M = sc.nextInt();

            for (int j = 0; j < M; j++) {
                cc = sc.nextInt();
                cs = sc.nextInt();
                ct = sc.nextInt();

                Card c = new Card(cc, cs, ct);
                deck.add(c);
            }
            
            int r = solveSmall(deck, deckPtr);
            out.println(r);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}
