package StringProcessing.FrequencyCounting;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 18/4/2016.
 *
 * problem: https://onlinejudge.org/external/9/902.pdf
 * #UVA #Lv2 #string #frequency_counting
 */
public class UVA902 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            if(!sc.hasNext()) break;
            int a = sc.nextInt();
            String b = sc.next();
            sc.nextLine();

            TreeMap<Long, Integer> count = new TreeMap<>();
            long sum = 0;
            for(int i=0; i<a; ++i){
                sum = sum * 26 + (b.charAt(i) -'a');
            }
            count.put(sum, 1);

            int max = -1; long maxrep = -1;
            for(int i=a; i<b.length(); ++i){
                Character c = b.charAt(i) ;
                int offset = c - 'a';

                sum = sum % (long)(Math.pow(26, a-1));
                sum = sum * 26 + offset;
                int occur = count.getOrDefault(sum,0) +1;
                if(occur > max) {max = occur; maxrep = sum; }
                count.put(sum, occur);
            }


            StringBuilder sr = new StringBuilder();
            for(int i=0; i<a; ++i){
                sr.append((char)('a' + maxrep % 26));
                maxrep /= 26;
            }
            sr.reverse();
            System.out.println(sr.toString());
        }
    }
}
