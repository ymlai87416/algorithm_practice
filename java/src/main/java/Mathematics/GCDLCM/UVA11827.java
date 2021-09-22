package Mathematics.GCDLCM;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Created by Tom on 19/4/2016.
 *
 * problem: https://onlinejudge.org/external/118/11827.pdf
 * #UVA #gcd_lcm #Lv2
 */
public class UVA11827 {

    static int gcd(int a,int b) {
        if (b==0) {
            return a;
        }
        int d;
        d = gcd(b, a%b);
        return d;
    }


    static int[] input = new int[101];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<a; ++i){
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s);

            Arrays.fill(input, 0);

            int cnt = 0;
            while(st.hasMoreTokens()){
                input[cnt++] = Integer.parseInt(st.nextToken());
            }

            int tgcd,  mgcd= Integer.MIN_VALUE;
            for(int p=0; p<cnt; ++p){
                for(int q=0; q<p; ++q){
                    if(input[p] > input[q]) tgcd = gcd(input[p], input[q]);
                    else if(input[p] == input[q]) tgcd = input[p];
                    else tgcd = gcd(input[q], input[p]);

                    if(mgcd <tgcd) mgcd= tgcd;
                }
            }

            System.out.println(mgcd);
        }
    }
}
