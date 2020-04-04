package DataStructure.OneDArrayManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Tom on 17/4/2016.
 *
 * TLE solution....
 */
public class UVA12356 {

    static int[] left = new int[100001];
    static int[] right = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader( new InputStreamReader(System.in) );
        while(true){

            String line = null;

            line = readLine(sc);
            StringTokenizer st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(s == 0 && b == 0) {
                readLine(sc);
                break;
            }

            left[0] = -1; right[s-1] = -1;
            for(int i=1; i<s; ++i)
                left[i] = i-1;
            for(int i=0; i<s-1; ++i)
                right[i] = i+1;

            for(int i=0; i<b; ++i){
                line = readLine(sc);
                st = new StringTokenizer(line);
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                l--; r--;
                if(left[l] != -1)right[left[l]] = right[r];
                if(right[r] != -1)left[right[r]] = left[l];
                System.out.format("%s %s\n", left[l] == -1 ? '*' : String.valueOf(left[l]+1), right[r] == -1 ? '*' : String.valueOf(right[r]+1));
            }
            System.out.println("-");

        }
    }

    static String readLine(BufferedReader br) throws IOException {
        String s;
        do{
            s = br.readLine();
        } while(s.trim().length() == 0);
        return s;
    }
}
