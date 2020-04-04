package ProblemSolving.Max2DRangeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Tom on 7/5/2016.
 *
 * Time limit exceed, but already use the best algorithm...
 */
public class UVA983 {

    static int[][] input = new int[1001][1001];
    static int[][] output = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean first = true;

        while(true){
            String line = br.readLine();

            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            /*for(int i=0; i<1001; ++i){
                Arrays.fill(input[i], 0);
                Arrays.fill(output[i], 0);
            }*/

            for(int i=n-1; i>=0; --i){
                for(int j=0; j<n; ++j){
                    input[i][j] = Integer.parseInt(br.readLine());
                }
            }

            for(int i=0; i<n; ++i){
                for(int j=1; j<n; ++j){
                    input[i][j] += input[i][j-1];
                }
            }

            for(int i=1; i<n; ++i){
                for(int j=0; j<n; ++j){
                    input[i][j] += input[i-1][j];
                }
            }

            for(int i=0; i<n-m+1; ++i){
                for(int j=0; j<n-m+1; ++j){
                    output[i][j] = input[i+m-1][j+m-1];
                    if(i> 0) output[i][j] -= input[i-1][j+m-1];
                    if(j> 0) output[i][j] -= input[i+m-1][j-1];
                    if(j>0 && i>0) output[i][j] += input[i-1][j-1];
                }
            }

            if(first) first = false;
            else System.out.println();

            long val = 0;
            for(int i=n-m; i>=0; --i){
                for(int j=0; j<n-m+1; ++j){
                    val += output[i][j];
                    System.out.println(output[i][j]);
                }
            }
            System.out.println(val);

            line = br.readLine();
            if(line == null) break;
        }
    }

}
