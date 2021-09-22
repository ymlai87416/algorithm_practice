package StringProcessing.JustAdHoc;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 15/5/2016.
 *
 * Base on the formula, no of permuatation = n_size! / n_a! n_b ! n_c !... n_z!
 *
 * problem: https://onlinejudge.org/external/9/941.pdf
 * #UVA #Lv3 #string #ad_hoc
 */

public class UVA941 {
    static char[] ch;
    static BigInteger[] factorials = new BigInteger[21];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        factorials[0] = BigInteger.ONE;
        factorials[1] = BigInteger.ONE;
        for(int i=2; i<=20; ++i){
            factorials[i] = factorials[i-1].multiply(BigInteger.valueOf(i));
        }

        int nc =sc.nextInt();
        sc.nextLine();
        for(int ci=0; ci<nc; ++ci){
            String b = sc.nextLine();
            String c = sc.nextLine();
            ch = b.toCharArray();
            Arrays.sort(ch);

            TreeMap<Character, Integer> freqMap = new TreeMap<>();
            int cnt = 1;
            for(int i=1; i<ch.length; ++i){
                if(ch[i] != ch[i-1]){
                    freqMap.put(ch[i-1], cnt);
                    cnt = 1;
                } else
                    ++cnt;
            }
            freqMap.put(ch[ch.length-1], cnt);

            System.out.println(solve(freqMap, new BigInteger(c)));
        }
    }

    static String solve(TreeMap<Character, Integer> freqMap, BigInteger c){
        TreeMap<Character, BigInteger> set = new TreeMap<>();
        int tsize = 0;
        BigInteger bi = BigInteger.ONE;
        for(Map.Entry<Character, Integer> entry : freqMap.entrySet()){
            tsize += entry.getValue();
            bi = bi.multiply(factorials[entry.getValue()]);
        }

        if(tsize == 1)
            return String.valueOf(freqMap.firstKey());

        BigInteger bb = factorials[tsize-1];
        for(Map.Entry<Character, Integer> entry : freqMap.entrySet()){
            set.put(entry.getKey(), bb.multiply(factorials[entry.getValue()]).divide(bi).divide(factorials[entry.getValue()-1]));
        }

        BigInteger count = BigInteger.ZERO;
        for(Map.Entry<Character, BigInteger> entry : set.entrySet()){
            count = count.add(entry.getValue());
            if(count.compareTo(c) > 0) {
                int a = freqMap.get(entry.getKey())-1;
                if(a == 0) freqMap.remove(entry.getKey());
                else freqMap.put(entry.getKey(), a);
                return entry.getKey() + solve(freqMap, c.subtract(count).add(entry.getValue()));
            }
        }

        return "";
    }


}
