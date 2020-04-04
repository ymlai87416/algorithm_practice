package StringProcessing.FrequencyCounting;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Tom on 14/5/2016.
 */
public class UVA10252 {
    static int[] bfreq = new int[26];
    static char[] result = new char[1001];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            String a = sc.nextLine();
            String b = sc.nextLine();

            Arrays.fill(bfreq, 0);
            for(int i=0; i<b.length(); ++i)
                bfreq[b.charAt(i) -'a'] ++;

            int rcnt = 0;
            for(int i=0; i<a.length(); ++i){
                if(bfreq[a.charAt(i)-'a'] > 0) {
                    //System.out.print(a.charAt(i));
                    result[rcnt++] = a.charAt(i);
                    bfreq[a.charAt(i)-'a']--;
                }
            }
            Arrays.sort(result, 0, rcnt);
            for(int i=0; i<rcnt; ++i) System.out.print(result[i]);
            System.out.println();
        }
    }
}
