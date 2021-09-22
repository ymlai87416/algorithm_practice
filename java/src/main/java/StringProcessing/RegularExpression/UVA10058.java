package StringProcessing.RegularExpression;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tom on 10/5/2016.
 *
 * problem: https://onlinejudge.org/external/100/10058.pdf
 * #UVA #Lv4 #string #regular_expression
 */
public class UVA10058 {

    static Pattern article = Pattern.compile("a|the");
    static Pattern noun = Pattern.compile("tom|jerry|goofy|mickey|jimmy|dog|cat|mouse");
    static Pattern verb = Pattern.compile("hate|love|know|like|hates|loves|knows|likes");
    static boolean statement(String s){
        int wp = s.lastIndexOf(" , ");
        if(wp != -1)
            return statement(s.substring(0, wp)) & action(s.substring(wp+3, s.length()));
        else
            return action(s);
    }

    static boolean action(String s){
        int wp = s.indexOf(" ");
        int wp2 = s.indexOf(" ", wp+1);

        while(wp != -1 && wp != -1){
            boolean temp = activeList(s.substring(0, wp)) && verb(s.substring(wp+1, wp2)) && activeList(s.substring(wp2+1, s.length()));
            if(temp) return true;
            wp = wp2;
            wp2 = s.indexOf(" ", wp+1);
        }
        return false;
    }

    static boolean activeList(String s) {
        int wp = s.lastIndexOf(" and ");
        boolean result;
        if(wp != -1)
            result = actor(s) || (activeList(s.substring(0, wp)) && actor(s.substring(wp+5, s.length())));
        else
            result = actor(s);
        return result;
    }

    static boolean actor(String a){
        int wp = a.indexOf(" ");
        boolean result;
        if(wp != -1)
            result = noun(a) || (article(a.substring(0, wp)) && noun(a.substring(wp+1, a.length())));
        else
            result = noun(a);

        return result;
    }

    static boolean article(String a){
        Matcher matcher = article.matcher(a);
        return matcher.matches();
    }

    static boolean noun(String a){
        Matcher matcher = noun.matcher(a);
        return matcher.matches();
    }

    static boolean verb(String a){
        Matcher matcher = verb.matcher(a);
        return matcher.matches();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            String a = sc.nextLine();
            a = a.replaceAll(",", " , ");
            a = a.replaceAll(" +", " ").trim();

            if(statement(a))
                System.out.println("YES I WILL");
            else
                System.out.println("NO I WON'T");
        }
    }
}
