package Introduction.SuperEasy;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 14/5/2016.
 *
 * problem: https://onlinejudge.org/external/117/11727.pdf
 * #UVA #Lv1 #ad_hoc
 */
public class UVA12250 {
    public static void main(String[] args){
        TreeMap<String, String> mapper = new TreeMap<>();

        mapper.put("HELLO", "ENGLISH");
        mapper.put("HOLA", "SPANISH");
        mapper.put("HALLO", "GERMAN");
        mapper.put("BONJOUR", "FRENCH");
        mapper.put("CIAO", "ITALIAN");
        mapper.put("ZDRAVSTVUJTE", "RUSSIAN");

        Scanner sc = new Scanner(System.in);

        int ci = 0;
        while(true){
            String a = sc.next();
            if(a.trim().compareTo("#") == 0) break;

            String b = mapper.get(a);
            if(b == null)
                System.out.format("Case %d: UNKNOWN\n", ++ci);
            else
                System.out.format("Case %d: %s\n", ++ci, b);
        }

    }
}
