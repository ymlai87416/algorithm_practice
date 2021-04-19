package ProblemSolving.Max1DRangeSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 problem: https://onlinejudge.org/external/7/787.pdf
 level:
 solution:

 #dp #max1DRangeSum

 **/

public class UVA787 {
    public static void main(String[] args){
        BufferedReader buf = new BufferedReader(
                new InputStreamReader(System.in));
        try{
            String str;
            int[] input = new int[104];
            BigInteger[] prods = new BigInteger[104];
            while(true){
                if((str=buf.readLine()) == null) break;
                if(str.trim().length() ==0) break;

                StringTokenizer st = new StringTokenizer(str);

                TreeSet<Integer> zeroPos = new TreeSet<>();
                int cnt = 0;
                while(st.hasMoreTokens()) {
                    input[cnt] = Integer.parseInt(st.nextToken());
                    if(input[cnt] == 0) {
                        zeroPos.add(cnt);
                        input[cnt] = 1;
                    }
                    if(input[cnt] == -999999) break;
                    prods[cnt] = (cnt - 1 < 0 ? BigInteger.ONE : prods[cnt-1]).multiply(BigInteger.valueOf(input[cnt]));
                    ++cnt;
                }

                BigInteger result = null;
                for(int i=0; i<cnt; ++i){
                    for(int j=i; j<cnt; ++j){
                        BigInteger temp;
                        Integer xtemp = zeroPos.lower(j+1);
                        if(xtemp != null && xtemp >= i)
                            temp = BigInteger.ZERO;
                        else
                            temp = prods[j].divide(i-1 < 0 ? BigInteger.ONE : prods[i-1]);
                        if(result == null || result.compareTo(temp) < 0)
                            result = temp;
                    }
                }

                System.out.println(result.toString());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
