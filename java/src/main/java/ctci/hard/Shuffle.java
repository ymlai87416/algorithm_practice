package ctci.hard;

import java.util.Random;

public class Shuffle {

    public void shuffleDeck(int[] cards){
        Random r = new Random();

        //apply fisher-yale
        for(int i=cards.length-1; i>=0; --i){
            int ir = r.nextInt(i+1);
            swap(cards, i, ir);
        }

    }

    private void swap(int[] cards, int p1, int p2){
        int t = cards[p1];
        cards[p1] = cards[p2];
        cards[p2] = t;
    }


    public static void main(String[] args) {
        int[] cards = new int[52];

        for(int i=0; i<52; ++i)
            cards[i] = i;

        Shuffle sol = new Shuffle();
        sol.shuffleDeck(cards);

        for(int i=0; i<52; ++i)
            System.out.print(cards[i] + ", ");
    }
}
