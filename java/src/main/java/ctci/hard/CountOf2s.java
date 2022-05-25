package ctci.hard;
import java.util.*;

public class CountOf2s {

    public int count2(String s) {
        List<String> prefixList = generatePrefix(s);
        int sum = 0;
        for (String prefix : prefixList) {
            //System.out.println(prefix);
            sum += count2Prefix(prefix);
        }
        return sum;
    }


    String s;
    List<String> result;
    private List<String> generatePrefix(String s){
        this.s = s;
        this.result = new ArrayList<>();

        generateHelper("", 0);

        return result;
    }

    private void generateHelper(String prefix, int index){
        if(index == s.length()) {
            result.add(prefix);
            return;
        }

        char cs = s.charAt(index);
        for(char j='0'; j<cs; ++j){
            StringBuilder temp = new StringBuilder();
            temp.append(prefix); temp.append(j);
            while(temp.length() < s.length())
                temp.append('*');

            result.add(temp.toString());

        }
        generateHelper(prefix+cs, index+1);
    }

    private int count2Prefix(String s){
        int nStar = 0;
        int n2 = 0;
        for(int i=0; i< s.length(); ++i)  {
            char cs = s.charAt(i);
            if(cs == '2') n2++;
            if(cs == '*') nStar++;
        }

        int sum = 0;
        for(int d=0; d<nStar; ++d){
            sum = (int)(Math.pow(10, d)) + sum * 10;;
        }

        sum += n2 * Math.pow(10, nStar);

        return sum;
    }

    public int bf(int n){
        int cnt2 = 0;
        for(int i=1; i<=n ; ++i){
            int ii = i;
            while(ii > 0){
                if(ii%10 == 2) cnt2++;
                ii/=10;
            }
        }
        return cnt2;
    }

    public static void main(String[] args) {
        CountOf2s test = new CountOf2s();
        System.out.println(test.count2("25") + " " + test.bf(25));
        System.out.println(test.count2("3124") + " " + test.bf(3124));
    }

   }
