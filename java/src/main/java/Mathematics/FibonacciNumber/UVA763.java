package Mathematics.FibonacciNumber;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 12/4/2016.
 *
 * Read: 20:37 one WA, and AC at 20:47
 * problem: https://onlinejudge.org/external/7/763.pdf
 * #UVA #combinatorics #fibonacci_number #Lv3
 */
public class UVA763 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        BigInteger[] result = new BigInteger[101];
        result[0] = BigInteger.ONE; result[1] = BigInteger.valueOf(2L);
        for(int i=2; i<101; ++i){
            result[i] = result[i-1].add(result[i-2]);
        }

        while(true){
            if(!sc.hasNext()) break;
            String a = sc.nextLine();
            String b = sc.nextLine();

            BigInteger aint = BigInteger.ZERO;
            for(int i=a.length()-1, j=0; i>=0; --i, ++j){
                if(a.charAt(i) == '1')
                    aint = aint.add(result[j]);
            }

            BigInteger bint=BigInteger.ZERO;
            for(int i=b.length()-1, j=0; i>=0; --i, ++j){
                if(b.charAt(i) == '1')
                    bint = bint.add(result[j]);
            }

            BigInteger sum = aint.add(bint);
            StringBuilder sb = new StringBuilder();
            boolean leadOne = false;
            for(int j=100; j>=0; --j){
                if(result[j].compareTo(sum) <=0){
                    sum= sum.subtract(result[j]);
                    sb.append('1');
                    leadOne = true;
                }
                else {
                    if(leadOne) sb.append('0');
                }
            }


            if(sb.length() > 0)
                System.out.println(sb.toString());
            else
                System.out.println("0"               );
            if(sc.hasNext()){
                System.out.println();
                sc.nextLine();
            }
            else break;
        }
    }
}
