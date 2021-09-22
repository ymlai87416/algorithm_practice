package Introduction.GameCard;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Tom on 14/4/2016.
 *
 * Runtime error cause by sc.nextLine, WA because forget to sort.
 * From 2:19 to 3:35. total of 1 hour 16 minutes......
 *
 * problem: https://onlinejudge.org/external/4/462.pdf
 *
 * #card_game #simulation #UVA #Lv3
 */
public class UVA462 {
    public static class Card{
        String card;
        public Card(String c){this.card = c;}
        public char suit(){return card.charAt(1);}
        public char num(){return card.charAt(0);}
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            String hand = sc.nextLine();
            if(hand.trim().length() == 0) break;
            StringTokenizer tokenizer = new StringTokenizer(hand);
            Card[] chand = new Card[13];
            for(int i=0; i<13; ++i){
                chand[i] = new Card(tokenizer.nextToken());
            }
            int point = 0;
            point += rule1(chand);
            point += rule234(chand);
            int point2 = point;
            point += rule567(chand);

            List<Character> suitList = Arrays.asList('S','H','D','C');
            Map<Character, Boolean> stopped =
                    suitList.stream().
                            collect(Collectors.toMap(suit -> suit, suit->{
                                boolean ace = false;
                                boolean kinghere = false;
                                boolean queenhere =false;
                                int suitcnt= 0;
                                for(int i=0; i<chand.length; ++i){
                                    Card cc= chand[i];
                                    if(cc.suit()  == suit) suitcnt++; else continue;
                                    if(cc.num() == 'K') kinghere = true;
                                    else if(cc.num() == 'A') ace = true;
                                    else if(cc.num() == 'Q') queenhere = true;
                                }

                                boolean king1 = kinghere && suitcnt >= 2;
                                boolean queen2 = queenhere && suitcnt >= 3;

                                return ace || king1 || queen2;
                            }));

            Map<Character, Integer> suitcntMap =
                    suitList.stream().
                            collect(Collectors.toMap(suit -> suit, suit->{
                                int suitcnt= 0;
                                for(int i=0; i<chand.length; ++i){
                                    Card cc= chand[i];
                                    if(cc.suit()  == suit) suitcnt++;
                                }
                                return suitcnt;
                            }));

            HashMap<Character, Integer> suitOrder =new HashMap<>();
            suitOrder.put('S', 0); suitOrder.put('H', 1); suitOrder.put('D', 2); suitOrder.put('C', 3);
            if(point < 14)
                System.out.println("PASS");
            else if(point2 >= 16 && stopped.getOrDefault('S', false) &&
                    stopped.getOrDefault('H', false) && stopped.getOrDefault('D', false) &&
                    stopped.getOrDefault('C', false)){
                System.out.println("BID NO-TRUMP");
            } else {

                Map.Entry<Character, Integer> maxEntry = null;
                for(Map.Entry<Character, Integer> entry: suitcntMap.entrySet()){
                    if(maxEntry == null || maxEntry.getValue() < entry.getValue()
                            || (maxEntry.getValue() == entry.getValue()  && suitOrder.get(maxEntry.getKey()) >  suitOrder.get(entry.getKey()))
                            )
                        maxEntry = entry;
                }
                System.out.format("BID %s\n", maxEntry.getKey());
            }
        }
    }

    static int rule1(Card[] cards){
        int result = 0;
        for(int i=0; i<cards.length; ++i){
            Card cc = cards[i];
            if(cc.num() == 'A') result += 4;
            if(cc.num() == 'K') result += 3;
            if(cc.num() == 'Q') result += 2;
            if(cc.num() == 'J') result += 1;
        }
        return result;
    }


    static int rule234(Card[] cards){
        int result;
        List<Character> suits = Arrays.asList('S','H','D','C');
        result = suits.stream().mapToInt(
                suit -> {
                    int suitR = 0;
                    int suitcnt= 0;
                    for(int i=0; i<cards.length; ++i){
                        Card cc = cards[i];
                        if(cc.suit() == suit) ++suitcnt;
                    }
                    for(int i=0; i<cards.length; ++i){
                        Card cc= cards[i];
                        if(cc.suit() == suit){
                            if(cc.num() == 'K' && suitcnt==1) suitR -= 1;
                            if(cc.num() == 'Q' && suitcnt<=2) suitR -= 1;
                            if(cc.num() == 'J' && suitcnt <=3) suitR -=1;
                        }
                    }
                    return suitR;
                }
        ).sum();
        return result;
    }


    static int rule567(Card[] cards){
        int result;
        List<Character> suits = Arrays.asList('S','H','D','C');
        result = suits.stream().mapToInt(
                suit -> {
                    int suitR = 0;
                    int suitcnt= 0;
                    for(int i=0; i<cards.length; ++i){
                        Card cc = cards[i];
                        if(cc.suit() == suit) ++suitcnt;
                    }
                    if(suitcnt == 2) suitR = 1;
                    else if(suitcnt == 1 || suitcnt == 0) suitR = 2;
                    return suitR;
                }
        ).sum();
        return result;
    }

}
