package ctci.moderate;
import java.util.*;
public class EnglishInt {
    public String englishInt(int val){

        //the limit is 2B
        int billion = val / 1_000_000_000;
        int million = (val%1_000_000_000)  / 1_000_000;
        int thousand =  (val%1_000_000)  / 1_000;
        int rest = val%1_000;

        List<String> token = new ArrayList<String>();
        if(billion > 0){
            String enVal = process999(billion);
            token.add(enVal + " Billion,");
        }
        if(million> 0){
            String enVal = process999(million);
            token.add(enVal + " Million,");
        }
        if(thousand > 0){
            String enVal = process999(thousand);
            token.add(enVal + " Thousand,");
        }
        if(rest > 0){
            String enVal = process999(rest);
            token.add(enVal );
        }

        return String.join(" ", token);
    }

    String[] digitName = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

String[] teenName = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", };

        String[] tyName = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

private String process999(int val){
        StringBuilder sb = new StringBuilder();
        List<String> token = new ArrayList<String>();
        int hundred = val / 100;
        int ten = (val % 100)/ 10;
        int digit = (val % 10);

        if(hundred> 0)
        token.add(digitName[hundred] + " Hundred");

        if(ten == 1)
        token.add(teenName[digit]);
        else if(ten > 1){
        token.add(tyName[ten]);
        token.add(digitName[digit]);
        }
        else{
        token.add(digitName[digit]);
        }


        return String.join(" ", token);
        }

        
    public static void main(String[] args) {
        EnglishInt test = new EnglishInt();
        System.out.println(test.englishInt(1234));
    }
}
