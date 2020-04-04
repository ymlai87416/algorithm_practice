package DataStructure.OneDArrayManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Tom on 15/4/2016.
 */
public class UVA11340 {

    static int[] price = new int[256];
    public static void main(String[] args) throws IOException {
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
        int a  = Integer.parseInt(bw.readLine().trim());

        for(int i=0; i< a; ++i){

            int b = Integer.parseInt(readLine(bw));

            Arrays.fill(price, 0);
            for(int j=0; j<b; ++j){
                String c =readLine(bw);
                int d = Integer.parseInt(c.substring(2).trim());

                price[c.charAt(0)] = d;
            }

            int e = Integer.parseInt(readLine(bw));
            int sum = 0;
            for(int j=0; j<e; ++j){
                String c = bw.readLine();

                for(int k=0; k<c.length(); ++k){
                    if(c.charAt(k) > 255) continue;
                    sum += price[c.charAt(k)];
                }
            }

            System.out.format("%.2f$\n", sum/100.0);
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
