package StringProcessing.InputParsingNonRecursive;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tom on 15/5/2016.
 */
public class UVA11878 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String plusPattern = "([0-9]+)\\+([0-9]+)=(.*)";
        Pattern plus = Pattern.compile(plusPattern);

        String minusPattern = "([0-9]+)\\-([0-9]+)=(.*)";
        Pattern minus = Pattern.compile(minusPattern);

        int correctAns = 0;
        while(true){
            if(!sc.hasNext()) break;
            String a = sc.nextLine();

            Matcher matcher = plus.matcher(a);
            if(matcher.find()){
                int result = Integer.parseInt(matcher.group(1)) + Integer.parseInt(matcher.group(2));
                if(matcher.group(3).compareTo("?") != 0)
                    if(result == Integer.parseInt(matcher.group(3))) correctAns++;
            } else{
                matcher = minus.matcher(a);
                if(matcher.find()){
                    int result = Integer.parseInt(matcher.group(1)) - Integer.parseInt(matcher.group(2));
                    if(matcher.group(3).compareTo("?") != 0)
                        if(result == Integer.parseInt(matcher.group(3))) correctAns++;
                }
            }
        }
        System.out.println(correctAns);
    }
}
