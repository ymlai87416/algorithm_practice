package StringProcessing.StringMatchingStandard;

import java.io.*;

/**
 * Created by Tom on 14/5/2016.
 *
 * TLE using the KMP search.. what the fuck?
 */
public class UVA11475 {
    static final int MAX_N = 200020;
    static char[] T = new char[MAX_N];
    static char[] P = new char[MAX_N]; // T = text, P = pattern
    static int[] b = new int[MAX_N];
    static int n, m; // b = back table, n = length of T, m = length of P

    static void kmpPreprocess() { // call this before calling kmpSearch()
        int i = 0, j = -1;
        b[0] = -1; // starting values
        while (i < m) { // pre-process the pattern string P
            while (j >= 0 && P[i] != P[j]) j = b[j]; // different, reset j using b
            i++;
            j++; // if same, advance both pointers
            b[i] = j; // observe i = 8, 9, 10, 11, 12, 13 with j = 0, 1, 2, 3, 4, 5
        }
    } // in the example of P = "SEVENTY SEVEN" above

    static int kmpSearch() { // this is similar as kmpPreprocess(), but on string T
        int i = 0, j = 0; // starting values
        while (i < n) { // search through string T
            while (j >= 0 && !compare(i, j)) j = b[j]; // different, reset j using b
            i++;
            j++; // if same, advance both pointers
            if (j == m) { // a match found when j == m
                //System.out.format("%s is found at index %d in T\n", String.valueOf(P, 0, m), i - j);
                if(i>=n/2) return i-j;  //if i(the pos of end matcher) reach the string end, return at once.
                j = b[j]; // prepare j for the next possible match
            }
        }
        return -1;
    }

    static boolean compare(int i, int j){
        //from n/2 ... n, they are * wildcard which match all characters.
        if(i >= n/2) return true;
        return T[i] == P[j];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(
                new InputStreamReader(System.in));
        /*BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(System.out));*/
        while (true) {
            String a = buf.readLine();
            if(a==null) break;

            n = a.length();
            for(int i=0, j=a.length()-1; i<a.length(); ++i, --j) {
                T[i] = a.charAt(i);
                P[j] = T[i];
            }

            m = a.length();
            kmpPreprocess();
            n = n+n;
            int pos = kmpSearch();
            //System.out.println(pos);

            System.out.print(a);
            for(int i=0, j=pos; i<n/2; ++i, ++j){
                if(j < n/2) continue;
                System.out.print(P[i]);
            }
            System.out.println();

            /*bw.write(a);
            for(int i=m; i<n; ++i)
                bw.write(P[i]);
            bw.newLine();*/
        }
        //bw.flush();
    }
}
