package StringProcessing.CipherEncodeEncryptDecodeDecryptHarder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 21/4/2016.
 *
 * Start at 2:38 and AC at 2:56, total time is 18 minutes.
 *
 * problem: https://onlinejudge.org/external/113/11385.pdf
 * #UVA #Lv3 #string
 */
public class UVA11385 {
    static int[] fibo = new int[100];
    static int[] fiboB = new int[46];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        fiboB[0] = 0;
        fiboB[1] = 1;
        fiboB[2] = 2;
        for(int i=3; i<46; ++i)
            fiboB[i] = fiboB[i-1]+fiboB[i-2];

        int t = sc.nextInt();

        for(int i=0; i<t; ++i){
            int s = sc.nextInt();
            for(int j=0; j<s; ++j){
                fibo[j] = sc.nextInt();
            }
            sc.nextLine();

            String en = sc.nextLine();

            char[] result= new char[50];
            Arrays.fill(result, (char)0);

            int cnt=0;
            for(int j=0; j<en.length() && cnt <s; ++j){
                if(!(en.charAt(j) >= 'A' && en.charAt(j) <='Z'))
                    continue;
                result[Arrays.binarySearch(fiboB, fibo[cnt])] = en.charAt(j);
                ++cnt;
            }

            StringBuilder sb = new StringBuilder();
            boolean leadingSpace = true;
            for(int j=49; j>=1; --j){
                if(leadingSpace){
                    if(result[j] == (char)0) continue;
                    else {
                        sb.append(result[j]);
                        leadingSpace=false;
                    }
                }
                else{
                    if(result[j] == (char)0) sb.append(" ");
                    else sb.append(result[j]);
                }
            }
            System.out.println(sb.reverse().toString());
        }

    }
}
