package ProblemSolving.Max2DRangeSum;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 problem: https://onlinejudge.org/external/119/11951.pdf
 level:
 solution: dp reduce from O(n^4) to O(n^3)

 #dp #max_2d_range_sum #UVA #Lv3

 **/

public class UVA11951 {

    static int[][] input = new int[101][101];

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nc = Integer.parseInt(br.readLine());

        for(int ci=0; ci<nc; ++ci){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            for(int i=0; i<n; ++i){
                line = br.readLine();
                st = new StringTokenizer(line);
                for(int j=0; j<m; ++j){
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            //now do the checking
            int minvalue = Integer.MAX_VALUE;
            int maxarea = Integer.MIN_VALUE;

            //choose x dimension from x1 => x2, and then sum it across y and then use kandane to solve it
            for (int i = 0; i < n; i++) {
                int[] dp = new int[m];
                for (int j = i; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        dp[k] = dp[k] + input[j][k];
                    }

                    int csum = 0; int x= 0;
                    for (int y = 0; y < m; y++) {
                        csum += dp[y];
                        while(csum > K) csum -= dp[x++];
                        int area = (j-i+1)*(y-x+1);
                        if (area > maxarea || (area == maxarea && csum < minvalue)) {
                            maxarea = area;
                            minvalue = csum;
                        }
                    }
                }
            }

            /*
            for(int i=0; i<n; ++i){
                for(int j=1; j<m; ++j)
                    input[i][j] += input[i][j-1];
            }

            for(int i=1; i<n; ++i){
                for(int j=0; j<m; ++j)
                    input[i][j] += input[i-1][j];
            }

            for(int i=0; i<n; ++i){
                for(int j=0; j<m; ++j){
                    for(int k=1; k<=n-i; ++k){
                        for(int l=1; l<=m-j; ++l){
                            int temp = input[i+k-1][j+l-1];
                            if(i> 0) temp -= input[i-1][j+l-1];
                            if(j> 0) temp -= input[i+k-1][j-1];
                            if(j>0 && i>0) temp += input[i-1][j-1];
                            int area = k*l;
                            if(temp <= K){
                                if(maxarea < area){
                                    maxarea = area;
                                    maxvalue = temp;
                                } else if(maxarea == area && maxvalue > temp){
                                    maxvalue = temp;
                                }
                            }
                        }
                    }
                }


            }*/

            bw.write(String.format("Case #%d: %d %d\n", ci+1, maxarea, minvalue));
        }
        bw.flush();
    }
}
