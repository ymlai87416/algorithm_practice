package ProblemSolving.GreedyNonClassical;

import java.util.Scanner;

/**
 * Created by Tom on 8/5/2016.
 *
 * problem: https://onlinejudge.org/external/107/10718.pdf
 * #UVA #Lv3 #greedy
 */
public class UVA10718 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            long n = sc.nextLong();
            long l = sc.nextLong();
            long u = sc.nextLong();
            sc.nextLine();

            long result = 0x00000000ffffffffL ^ n;
            long temp = 0;
            for(int i=31; i>=0; --i){
                int bit= ((result & 1L << i) == 0) ? 0 : 1;
                if(bit == 1){
                    long ttemp = temp + (1L << i);
                    if(ttemp <= u)
                        temp = ttemp;
                }
                else if(bit == 0){
                    long ttemp = temp + (1L << i) -1;       //set it to 1 if 011...1 cannot larger than lower bound.
                    if(ttemp < l){
                        temp += (1L << i);
                    }
                }
            }

            System.out.println(temp);

        }
    }
}
