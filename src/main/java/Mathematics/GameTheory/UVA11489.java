package Mathematics.GameTheory;

import java.util.Scanner;

/**
 * Created by ymlai on 27/4/2017.
 */
public class UVA11489 {

    static String idx = "0123456789";

    static int minimax_nonRecursive(int id, String input){

        while(true){

            if(input.length() == 0)
                return id==0?0:1;

            int sum = 0;
            for(int i=0; i<input.length(); ++i)
                sum += idx.indexOf(input.charAt(i));

            int r = sum%3;
            int ridx = -1;
            for(int i=0; i<input.length(); ++i) {
                int rr = idx.indexOf(input.charAt(i)) % 3;
                if(rr==r) ridx = i;
            }

            if(ridx == -1)
                return id==0?0:1;

            id = 1-id;
            input = input.substring(0, ridx) + input.substring(ridx+1, input.length());
        }
    }

    static int minimax(int id, String input){
        if(input.length() == 0)
            return id==0?0:1;

        int sum = 0;
        for(int i=0; i<input.length(); ++i)
            sum += idx.indexOf(input.charAt(i));

        int r = sum%3;
        int ridx = -1;
        for(int i=0; i<input.length(); ++i) {
            int rr = idx.indexOf(input.charAt(i)) % 3;
            if(rr==r) ridx = i;
        }

        if(ridx == -1)
            return id==0?0:1;

        return minimax(1-id, input.substring(0, ridx) + input.substring(ridx+1, input.length()));
        /*if(id == 0) // Stan is the maximizing player
        {
            int bestValue =minimax(1-id, input.substring(0, ridx) + input.substring(ridx+1, input.length()));
            return bestValue;
        }
        else
        {
            int bestValue = minimax(1-id, input.substring(0, ridx) + input.substring(ridx+1, input.length()));
            return bestValue;
        }*/
    }

    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);

        int nc = sc.nextInt();
        for(int i=0; i<nc; ++i){
            String a;

            a = sc.next().trim();

            int r = minimax(0, a);
            if(r == 1)
                System.out.format("Case %d: S\n", i+1);
            else
                System.out.format("Case %d: T\n", i+1);
        }
    }
}
