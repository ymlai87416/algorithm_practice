package Mathematics.FibonacciNumber;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 12/4/2016.
 * Startt at 22:48, TLE 2 times, WA: 1 times, Runtime error( forget to remove the package line), and finally got accepted at 1:23
 * Total: 2 hour 30 mins
 *
 * problem: https://onlinejudge.org/external/106/10689.pdf
 * #UVA #combinatorics #fibonacci_number #Lv4
 */
public class UVA10689 {
    public static class FibonacciGenerator{

        public BigInteger genFib(long n)
        {
            BigInteger arr1[][] = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
            if (n != 0) {
                power(arr1, n - 1);
                return arr1[0][0];
            }
            else
                return BigInteger.ZERO;
        }

        private void power(BigInteger arr1[][], long n)
        {
            if (n == 0 || n == 1)
                return;
            BigInteger arr2[][] = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
            power(arr1, n / 2);
            multiply(arr1, arr1);
            if (n % 2 != 0)
                multiply(arr1, arr2);
        }

        private void multiply(BigInteger arr1[][], BigInteger arr2[][])
        {
            BigInteger x = (arr1[0][0].multiply(arr2[0][0])).add(arr1[0][1].multiply(arr2[1][0]));
            BigInteger y = (arr1[0][0].multiply(arr2[0][1])).add(arr1[0][1].multiply(arr2[1][1]));
            BigInteger z = (arr1[1][0].multiply(arr2[0][0])).add(arr1[1][1].multiply(arr2[1][0]));
            BigInteger w = (arr1[1][0].multiply(arr2[0][1])).add(arr1[1][1].multiply(arr2[1][1]));
            arr1[0][0] = x;
            arr1[0][1] = y;
            arr1[1][0] = z;
            arr1[1][1] = w;
        }

        public int genFibModulus(long n, int modulus)
        {
            int arr1[][] = {{1, 1 }, {1, 0}};
            if (n != 0) {
                powerModulus(arr1, n - 1, modulus);
                return arr1[0][0];
            }
            else
                return 0;
        }

        private void powerModulus(int arr1[][], long n, int modulus)
        {
            if (n == 0 || n == 1)
                return;
            int arr2[][] = {{1, 1}, {1, 0}};
            powerModulus(arr1, n / 2, modulus);
            multiplyModulus(arr1, arr1, modulus);
            if (n % 2 != 0)
                multiplyModulus(arr1, arr2, modulus);
        }

        private void multiplyModulus(int arr1[][], int arr2[][], int modulus)
        {
            int x = (arr1[0][0] * (arr2[0][0])) + (arr1[0][1] *(arr2[1][0]));
            int y = (arr1[0][0] *(arr2[0][1])) + (arr1[0][1] *(arr2[1][1]));
            int z = (arr1[1][0] *(arr2[0][0])) + (arr1[1][1] *(arr2[1][0]));
            int w = (arr1[1][0] *(arr2[0][1])) + (arr1[1][1] *(arr2[1][1]));
            arr1[0][0] = x % modulus;
            arr1[0][1] = y % modulus;
            arr1[1][0] = z % modulus;
            arr1[1][1] = w % modulus;
        }
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        FibonacciGenerator generator = new FibonacciGenerator();
        int tcase = scan.nextInt();
        for(int i=0; i<tcase; ++i){
            int a = scan.nextInt();
            int b = scan.nextInt();

            int c = scan.nextInt();
            int d = scan.nextInt();

            if(c == 0){
                String resultS = String.valueOf(a);
                System.out.println(Integer.parseInt(resultS.substring(Math.max(0, resultS.length() - d))));
            }
            else if(c == 1){
                String resultS = String.valueOf(b);
                System.out.println(Integer.parseInt(resultS.substring(Math.max(0, resultS.length() - d))));
            }
            else{
                int result = (generator.genFibModulus(c, 10000) * b ) + (generator.genFibModulus(c - 1, 10000) * a);
                String resultS = String.valueOf(result);
                System.out.println(Integer.parseInt(resultS.substring(Math.max(0, resultS.length() - d))));
            }

        }
    }
}
