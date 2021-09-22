package StringProcessing.FrequencyCounting;

import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 *
 * problem: https://onlinejudge.org/external/112/11203.pdf
 * #UVA #Lv4 #string #frequency_counting
 */
public class UVA11203 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc =sc.nextInt();

        for(int i=0; i<nc; ++i){
            String s = sc.next();

            if(!s.matches("\\?+M\\?+E\\?+")) {
                System.out.println("no-theorem");
                continue;
            }

            int xcnt = s.indexOf("M");
            int ycnt = s.indexOf("E") - xcnt - 1;
            int zcnt = s.length() - s.indexOf("E") - 1;

            if(zcnt == xcnt+1+ycnt-1)
                System.out.println("theorem");
            else
                System.out.println("no-theorem");
        }
    }
}
