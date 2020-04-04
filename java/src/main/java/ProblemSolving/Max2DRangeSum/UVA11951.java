package ProblemSolving.Max2DRangeSum;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Tom on 7/5/2016.
 *
 * Time limit exceed, have to use the Kadane's algorithm
 */
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

            for(int i=0; i<n; ++i){
                for(int j=1; j<m; ++j)
                    input[i][j] += input[i][j-1];
            }

            for(int i=1; i<n; ++i){
                for(int j=0; j<m; ++j)
                    input[i][j] += input[i-1][j];
            }

            //now do the checking
            int maxvalue = Integer.MAX_VALUE;
            int maxarea = Integer.MIN_VALUE;

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
            }

            bw.write(String.format("Case #%d: %d %d\n", ci+1, maxarea, maxvalue));
        }
        bw.flush();
    }
}
