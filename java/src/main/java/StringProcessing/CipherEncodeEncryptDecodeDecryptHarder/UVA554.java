package StringProcessing.CipherEncodeEncryptDecodeDecryptHarder;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Created by Tom on 21/4/2016.
 * Start at 1:45 and Runtime Error for 3 time and 2:34 finally AC, total time spend 49 minutes....
 * Just because a extra while loop outside.
 */
public class UVA554 {
    static String charOrder = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TreeSet<String> dict = new TreeSet<String>();

        while(true){
            String s = sc.nextLine();
            if(s.trim().compareTo("#") == 0) break;
            dict.add(s);
        }

        while(sc.hasNext()){
            String encrypted = sc.nextLine();
            encrypted = encrypted.toUpperCase();

            String maxs = null;
            int maxw = Integer.MIN_VALUE;
            for(int i=0; i<27; ++i){
                StringBuffer sb = new StringBuffer();
                for(int j=0; j<encrypted.length(); ++j){
                    sb.append(charOrder.charAt((charOrder.indexOf(encrypted.charAt(j))+i) % 27));
                }
                StringTokenizer st = new StringTokenizer(sb.toString());
                int wds = 0;
                while(st.hasMoreTokens()){
                    if(dict.contains(st.nextToken())) ++wds;
                }
                if(wds > maxw){
                    maxw = wds;
                    maxs = sb.toString();
                }
            }

            //StringBuilder sbresult= new StringBuilder(maxs);
            StringTokenizer st = new StringTokenizer(maxs);
            while(st.hasMoreTokens()){
                String s = st.nextToken();
                boolean result=  outputString(s);
                if(!result)
                    result = outputString(s);
                if(!result)
                    System.out.println(s);
            }
            if(temp.trim().length()!=0) System.out.println(temp);

            /*int linex = 0;
            while(linex < maxs.length()){
                linex += 60;
                if(linex >= maxs.length()) break;
                while(sbresult.charAt(linex) != ' ')
                    linex--;
                sbresult.replace(linex, linex+1, "\n");
                //System.out.println("insert space at " + linex);
                linex++;
            }

            System.out.println(sbresult.toString());*/
        }
    }

    static String temp = "";
    static boolean outputString(String token){
        String tempBk = temp;
        temp += (temp.length() == 0 ? token : " " + token);
        if(temp.length() > 60) {
            System.out.println(tempBk);
            temp = "";
            return false;
        }
        return true;
    }
}
