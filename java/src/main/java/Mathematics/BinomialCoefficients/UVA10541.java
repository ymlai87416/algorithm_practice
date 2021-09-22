package Mathematics.BinomialCoefficients;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 19/4/2016.
 *
 * problem: https://onlinejudge.org/external/105/10541.pdf
 * #UVA #combinatorics #Lv4 #binomail_coefficients
 */
public class UVA10541 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a= sc.nextInt();

        for(int i=0; i<a; ++i){
            int k =sc.nextInt();
            int nb = sc.nextInt();
            int[] bb = new int[nb];
            for(int j=0; j<nb; ++j){
                bb[j] = sc.nextInt();
            }

            int sum = -1;
            for(int j=0; j<nb; ++j){
                sum += (bb[j]+1);
            }

            int w = k - sum;
            if(w < 0) System.out.println(0);
            else{
                int n = w + nb;
                int r = nb;
                BigInteger result = factorial(n).divide(factorial(r).multiply(factorial(n-r)));
                System.out.println(result);
            }
        }
    }

    static BigInteger factorial(int n){
        BigInteger result = BigInteger.ONE;
        for(int i=2; i<=n; ++i){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
