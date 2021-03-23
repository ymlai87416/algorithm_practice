package GoogleCodeJam.Y2020.Round1A.A;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2020\\RoundA\\A\\A-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private String solve(ArrayList<String> inputs){
        //do nothing
        //given a set, find the min prefix and suffix, and then send the whole ans to recursive function. done

        System.out.println("Debug:");
        for(int i=0; i<inputs.size(); ++i) {
            System.out.println(inputs.get(i));
        }

        String prefix = "";
        String suffix = "";

        if(inputs.size() == 0)
            return "";
        else if(inputs.size()== 1)
            return inputs.get(0).replaceAll("\\*", "");

        for(int i=0; i< inputs.size(); ++i){
            StringBuilder sb = new StringBuilder();
            String temp;
            for (int j = 0; j <inputs.get(i).length() ; j++) {
                if(inputs.get(i).charAt(j) != '*')
                    sb.append(inputs.get(i).charAt(j));
                else
                    break;
            }
            temp = sb.toString();
            if(temp.length() > prefix.length())
                prefix = temp;

            sb = new StringBuilder();
            for (int j = inputs.get(i).length()-1; j >=0 ; j--) {
                if(inputs.get(i).charAt(j) != '*')
                    sb.append(inputs.get(i).charAt(j));
                else
                    break;
            }
            reverseString(sb);
            temp = sb.toString();
            if(temp.length() > suffix.length())
                suffix = temp;
        }

        //case: prefix, suffix empty, all string start by * and end by *
        if(prefix.length() == 0 && suffix.length() == 0){
            boolean allStart = true;
            for(int i=0; i<inputs.size(); ++i) {
                if(inputs.get(i).compareTo("*") !=0) {
                    allStart = false;
                    break;}
            }
            if(allStart) return "";

            for(int i=0; i<inputs.size(); ++i) {
                if(inputs.get(i).length() > 1) {
                    String temp = inputs.get(i);
                    inputs.set(i, temp.substring(1));
                    String ans = solve(inputs);
                    inputs.set(i, temp);
                    if (ans.compareTo("*") != 0) return ans;
                }
            }
            return "*";
        }

        //longest prefix and suffix on hand
        for(int i=0; i<prefix.length(); ++i){
            String suf = suffix.substring(0, Math.min(prefix.length()-i, suffix.length()));
            if(prefix.endsWith(suf)){
                String b = prefix.substring(0, i) + suf;
                String ans;
                boolean isAns = true;
                for (int j = 0; j < inputs.size(); j++) {
                    ans = compatable(b, inputs.get(j), 0, 0, -1, "");
                    if(ans==null){
                        isAns = false;
                        break;
                    }
                }
                if(!isAns) continue;
                else return b;
            }
        }

        //prefix+suffix?
        String b = prefix + suffix;
        String ans;
        boolean isAns = true;
        for (int j = 0; j < inputs.size(); j++) {
            ans = compatable(b, inputs.get(j), 0, 0, -1, "");
            if(ans==null){
                isAns = false;
                break;
            }
        }
        if(isAns) return b;

        b = prefix+"*"+suffix; //the worst, can suffix and prefix merge, then we get the answer.

        ArrayList<String> newInput = new ArrayList<>();
        for(int i=0; i<inputs.size(); ++i) {
            String temp = compatable(b, inputs.get(i), 0, 0, b.indexOf("*"), "");
            if(temp == null) return "*";
            if(temp.compareTo("*") !=0)
                newInput.add(temp);
        }
        Collections.sort(newInput);
        newInput= removeDuplicate(newInput);
        String midAns = solve(newInput);
        if(midAns.compareTo("*") != 0)
            return prefix+midAns+suffix;
        else
            return "*";
    }

    private ArrayList<String> removeDuplicate(ArrayList<String> as){
        ArrayList<String> result = new ArrayList<>();
        if(as.size()==0) return result;
        result.add(as.get(0));
        for(int i=1; i<as.size(); ++i){
            if(as.get(i).compareTo(as.get(i-1)) == 0)
                continue;
            else result.add(as.get(i));
        }
        return result;
    }

    private void reverseString(StringBuilder sb){
        StringBuilder sb2 = new StringBuilder();
        for(int i=0, j=sb.length()-1; i<j; ++i, --j){
            char c = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, c);
        }
    }

    private String solve2(ArrayList<String> inputs){
        //find prefix and suffix
        String prefix = "";
        String suffix = "";
        StringBuilder sb;
        for(int i=0; i<inputs.size(); ++i){
            int spos = inputs.get(i).indexOf('*');
            String temp = inputs.get(i).substring(0, spos);
            if(temp.length() > prefix.length())
                prefix = temp;
        }

        for(int i=0; i<inputs.size(); ++i){
            int spos = inputs.get(i).lastIndexOf('*');
            String temp = inputs.get(i).substring(spos+1);
            if(temp.length() > suffix.length())
                suffix = temp;
        }

        ArrayList<String> newInput = new ArrayList<>();
        //check if compatible
        String midAns = "";
        for(int i=0; i<inputs.size(); ++i){
            int spos = inputs.get(i).indexOf('*');
            int epos = inputs.get(i).lastIndexOf('*');
            String tprefix=inputs.get(i).substring(0, spos) ;
            String tsuffix = inputs.get(i).substring(epos+1);
            if(!prefix.startsWith(tprefix))
                return "*";
            if(!suffix.endsWith(tsuffix))
                return "*";

            int nStar = 0;
            for(int j=0; j<inputs.get(i).length(); ++j){
                if(inputs.get(i).charAt(j) == '*') nStar++;
            }
            if(nStar == 1){
                //already statisfy
                continue;
            }
            else{
                String[] temp = inputs.get(i).substring(spos+1, epos).split("\\*");
                for (int j = 0; j < temp.length ; j++) {
                    midAns += temp[j];
                }
            }
        }

        return prefix+midAns+suffix;
    }

    //this approach does not work.... have to loop throught alot
    private String compatable(String a, String b, int ptr1, int ptr2, int query, String t){
        //base case
        if(ptr1 == a.length() && ptr2 ==b.length())
            return t;
        if(ptr1 == a.length()-1 && a.charAt(ptr1)=='*') {
            if(ptr1 == query)
                return b.substring(ptr2);
            else
                return t;
        }
        if(ptr2 == b.length()-1 && b.charAt(ptr2)=='*')
            return t;
        if(ptr1 == a.length() && ptr2 != b.length() || ptr1 != a.length() && ptr2 == b.length())
            return null;

        char ca= a.charAt(ptr1);
        char cb =b.charAt(ptr2);

        if(ca != '*' && cb != '*'){
            if(ca == cb) {
                String aa = compatable(a, b, ptr1 + 1, ptr2 + 1, query, t);
                //System.out.println("debug: " + a.substring(ptr1) + ", " + b.substring(ptr2) + " ans: " + (aa == null));
                return aa;
            }
            else
                return null;
        }
        else{
            //if anyone is *, advance either * or others
            if(ptr1 == query) {
                //match as further as possible
                String aa  = compatable(a, b, ptr1, ptr2 + 1, query, t + b.charAt(ptr2));
                if(aa != null) return aa;
                aa = compatable(a, b, ptr1 + 1, ptr2, query, t);
                //System.out.println("debug: " + a.substring(ptr1) +", " + b.substring(ptr2) + " ans: " + (aa==null));
                return aa;
            }
            else{
                String aa  = compatable(a, b, ptr1 + 1, ptr2, query, t);
                if(aa != null) return aa;
                aa = compatable(a, b, ptr1, ptr2 + 1, query, t);
                //System.out.println("debug: " + a.substring(ptr1) +", " + b.substring(ptr2) + " ans: " + (aa==null));
                return aa;
            }
        }
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();


        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");

            int size = sc.nextInt();
            sc.nextLine();
            ArrayList<String> inputs = new ArrayList<>();

            for(int p=0; p<size; ++p){
                String line = sc.nextLine();
                line = line.replaceAll("(\\*)+", "\\*");
                inputs.add(line);
            }

            String result = solve2(inputs);
            out.format("%s\n", result);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
