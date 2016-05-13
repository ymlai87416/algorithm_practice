package Mathematics.MathSimulationEasier;

import java.util.Scanner;

/**
 * Created by Tom on 12/4/2016.
 * Read at 3:36 and AC at 3:45, total minute 9
 */
public class UVA1225 {
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        int[] caseIdx = new int[10000];
        for(int i=1; i<10000; ++i){
            String numstr = String.valueOf(i);
            sb.append(numstr);
            caseIdx[i] = caseIdx[i-1]+numstr.length();
        }
        String result = sb.toString();

        Scanner sc = new Scanner(System.in);

        int ncase = sc.nextInt();
        for(int i=0; i<ncase; ++i){
            int to = sc.nextInt();
            int toIdx = caseIdx[to];
            int[] digits = new int[10];
            for(int j=0; j<toIdx; ++j){
                int offset = (int)(result.charAt(j) )- '0';
                digits[offset]++;
            }
            System.out.format("%d %d %d %d %d %d %d %d %d %d\n", digits[0],digits[1],digits[2],digits[3],digits[4],
                    digits[5],digits[6],digits[7],digits[8],digits[9]);
        }
    }
}
