package Mathematics.OtherNumberTheoryProblem;

import java.util.Scanner;

/**
 * Created by ymlai on 16/4/2017.
 */
public class UVA756 {

    static long modInverse(long a, long m) {
        // modular inverse
        a = a % m;
        long j = 1, i = 0, b = m, c = a, x, y;
        while (c != 0) {
            x = b / c;
            y = b - x * c;
            b = c;
            c = y;
            y = j;
            j = i - j * x;
            i = y;    }
        if (i < 0) i += m;
        return i;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int c= 0;
        long m23 = modInverse(28*33, 23) * 28*33;
        long m28 = modInverse(23*33, 28) * 23*33;
        long m33 = modInverse(23*28, 33) * 23*28;

        while(true){
            int p, e, i, d;
            p = sc.nextInt();
            e = sc.nextInt();
            i = sc.nextInt();
            d = sc.nextInt();

            if(p==-1 && e==-1 && i==-1 && d==-1)
                break;
            ++c;

            p = (23-(p%23))%23;
            e = (28-(e%28))%28;
            i = (33-(i%33))%33;
            long x = (m23*p + m28*e + m33*i) % 21252;
            x = (x+d) % 21252;

            System.out.format("Case %d: the next triple peak occurs in %d days.\n", c, 21252-x);
        }
    }
}
