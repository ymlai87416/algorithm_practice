package DataStructure.BitManipulation;

import java.util.Scanner;

/**
 * Created by Tom on 15/5/2016.
 *
 * from 10:51 to 11:03, total use 12 minutes....
 *
 * problem: https://onlinejudge.org/external/119/11933.pdf
 *
 * #bit_manipulation #UVA #Lv2
 */
public class UVA11933 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int a= sc.nextInt();
            if(a == 0) break;

            int aa = 0;
            int bb = 0;
            int cnt=0;
            for(int i=0; i<32; ++i){
                int t = a & (1 << i);
                if(t == 0) continue;
                if(cnt %2 ==0)
                    aa = aa | t;
                else
                    bb = bb | t;
                ++cnt;
            }

            System.out.format("%d %d\n", aa, bb);
        }
    }
}
