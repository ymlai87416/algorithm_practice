package Introduction.GameCard;

import java.util.Scanner;

/**
 * Created by Tom on 14/4/2016.
 * Read at 22:39 and finished at 23:35,  for the Yth card, please model it in the paper...... waste a lot of time for debug.
 */
public class UVA10646 {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);

        int a=sc.nextInt();
        sc.nextLine();

        for(int i=0; i<a; ++i){
            String[] stack = new String[52];
            for(int j=0; j<52; ++j){
                stack[j] = sc.next();
            }

            //do the stack[0] = bottom
            int y = 0;
            String[] hand = new String[25];
            for(int j=51, k=24; k>=0; --j, --k ) {
                hand[k] = stack[j];
                stack[j] = null;
            }

            //debugCard(stack);

            for(int j=0; j<3; ++j){
                String topcardx = topcard(stack);
                int x = 0;

                if(num(topcardx) >='2' && num(topcardx) <='9')
                    x = num(topcardx) - '0';
                else
                    x = 10;
                //System.out.println(y + "---" + x);

                y += x;
                removeCard(stack, (1+(10-x)));

                //debugCard(stack);
            }

            int k;
            for(k=51; k>=0; --k) if(stack[k]!= null) break;

            for(int p=0; p<25; ++p)stack[++k] = hand[p];

            //debugCard(stack);

            String target = stack[y-1];

            System.out.format("Case %d: %s\n", i+1, target );
        }
    }

    public static char num(String card){
        return card.charAt(0);
    }
    public static char suit(String card){
        return card.charAt(1);
    }

    public static String topcard(String[] stack){
        for(int i=51; i>=0; --i){
            if(stack[i] != null) return stack[i];
        }
        return null;
    }

    public static void removeCard(String[] stack, int a){
        for(int i=51, j=0; i>=0 && j<a ; --i){
            if(stack[i] == null) continue;
            stack[i] = null; ++j;
        }
    }

    public static void debugCard(String[] stack){
        System.out.println("----");
        for(int i=0; i<52; ++i){
            if(stack[i]!=null) System.out.format("%s ", stack[i]);
        }
        System.out.println("----");
    }
}
