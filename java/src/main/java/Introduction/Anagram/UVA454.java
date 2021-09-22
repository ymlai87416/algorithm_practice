package Introduction.Anagram;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Tom on 23/4/2016.
 *
 * Start at 1:17:49 and end at 1:37, total of 20 minutes...
 *
 * #problem: https://onlinejudge.org/external/4/454.pdf
 *
 * #anagram #UVA #Lv3
 */
public class UVA454 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        sc.nextLine();

        sc.nextLine();

        for(int i=0; i<t; ++i){
            TreeSet<String> dictionary = new TreeSet<String>();

            while(true){
                if(!sc.hasNext()) break;
                String s = sc.nextLine();
                if(s.trim().length() == 0) break;
                dictionary.add(s);
            }

            for(String ss : dictionary){
                Set<String> comp = dictionary.tailSet(ss, false);
                for(String tt: comp){
                    if(isAnagram(ss, tt))
                        System.out.format("%s = %s\n", ss, tt);
                }
            }

            if(i!=t-1) System.out.println();
        }
    }

    static boolean isAnagram(String s, String t){
        String ss = s.replaceAll("\\s", "");
        String tt = t.replaceAll("\\s", "");
        if(ss.length() != tt.length()) return false;
        char[] css = ss.toCharArray();
        char[] ctt = tt.toCharArray();
        Arrays.sort(css);
        Arrays.sort(ctt);
        for(int i=0; i<css.length; ++i){
            if(css[i] != ctt[i]) return false;
        }
        return true;
    }

}
