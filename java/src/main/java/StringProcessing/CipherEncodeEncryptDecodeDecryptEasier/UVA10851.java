package StringProcessing.CipherEncodeEncryptDecodeDecryptEasier;

import java.util.Scanner;

/**
 * Created by Tom on 21/4/2016.
 *
 * Start at 17:10 WA at 17:27 and finished at 17:29, total time: 19 minutes....
 */
public class UVA10851 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        sc.nextLine();

        for(int i=0; i<t; ++i){
            String[] ss = new String[10];
            for(int j=0; j<10; ++j)
                ss[j] = sc.nextLine();

            int M = ss[0].trim().length()-2;

            StringBuilder sb = new StringBuilder();


            for(int m=1; m<=M; ++m){
                int result = 0;
                for(int j=1; j<10; ++j){
                    result = result + (ss[j].charAt(m) == '/' ? 0 : 1) * (int)Math.pow(2, j-1);
                }
                sb.append((char)result);
            }

            System.out.println(sb.toString());
            if(i!=t-1) sc.nextLine();
        }
    }
}
