package StringProcessing.SuffixTreeStandard;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tom on 14/5/2016.
 *
 * An example of finding longest repeating sequence using Suffix Array (longest and return result with the least lex order, not most appearing freq).
 */
public class UVA11512 {

    static final int MAX_N = 1001;
    static char[] T = new char[MAX_N]; // the input string, up to 100K characters
    static int n; // the length of input string
    static int[] RA = new int[MAX_N];
    static int[] tempRA = new int[MAX_N]; // rank array and temporary rank array
    static int[] SA = new int[MAX_N];
    static int[] tempSA = new int[MAX_N]; // suffix array and temporary suffix array
    static int[] c = new int[MAX_N]; // for counting/radix sort

    static void countingSort(int k) { // O(n)
        int i, sum, maxi = Math.max(300, n); // up to 255 ASCII chars or length of n
        Arrays.fill(c, 0); // clear frequency table
        for (i = 0; i < n; i++) // count the frequency of each integer rank
            c[i + k < n ? RA[i + k] : 0]++;
        for (i = sum = 0; i < maxi; i++) {
            int t = c[i];
            c[i] = sum;
            sum += t;
        }
        for (i = 0; i < n; i++) // shuffle the suffix array if necessary
            tempSA[c[SA[i] + k < n ? RA[SA[i] + k] : 0]++] = SA[i];
        for (i = 0; i < n; i++) // update the suffix array SA
            SA[i] = tempSA[i];
    }

    static void constructSA() { // this version can go up to 100000 characters
        int i, k, r;
        for (i = 0; i < n; i++) RA[i] = T[i]; // initial rankings
        for (i = 0; i < n; i++) SA[i] = i; // initial SA: {0, 1, 2, ..., n-1}
        for (k = 1; k < n; k <<= 1) { // repeat sorting process log n times
            countingSort(k); // actually radix sort: sort based on the second item
            countingSort(0); // then (stable) sort based on the first item
            tempRA[SA[0]] = r = 0; // re-ranking; start from rank r = 0
            for (i = 1; i < n; i++) // compare adjacent suffixes
                tempRA[SA[i]] = // if same pair => same rank r; otherwise, increase r
                        (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
            for (i = 0; i < n; i++) // update the rank array RA
                RA[i] = tempRA[i];
            if (RA[SA[n-1]] == n-1) break; // nice optimization trick
        }
    }

    static int[] Phi=  new int[MAX_N];
    static int[] PLCP = new int[MAX_N];
    static int[] LCP = new int[MAX_N];
    static void computeLCP() {
        int i, L;
        Phi[SA[0]] = -1; // default value
        for (i = 1; i < n; i++) // compute Phi in O(n)
            Phi[SA[i]] = SA[i-1]; // remember which suffix is behind this suffix
        for (i = L = 0; i < n; i++) { // compute Permuted LCP in O(n)
            if (Phi[i] == -1) { PLCP[i] = 0; continue; } // special case
            while (T[i + L] == T[Phi[i] + L]) L++; // L increased max n times
            PLCP[i] = L;
            L = Math.max(L-1, 0); // L decreased max n times
        }
        for (i = 0; i < n; i++) // compute LCP in O(n)
            LCP[i] = PLCP[SA[i]]; // put the permuted LCP to the correct position
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int nc = sc.nextInt();
        sc.nextLine();

        for(int ci=0; ci<nc; ++ci){
            String a = sc.nextLine();

            n = a.length(); // input T as per normal, without the ‘$’
            for(int i=0; i<a.length(); ++i)
                T[i] = a.charAt(i);
            T[a.length()] = '$'; // add terminating character
            ++n;

            constructSA();
            Arrays.fill(Phi, 0); Arrays.fill(PLCP, 0); Arrays.fill(LCP, 0);
            computeLCP();

            String result = "";
            int maxsize = 0;
            for(int i=0; i<n; ++i){
                if(LCP[i] > maxsize || LCP[i] == maxsize && result.compareTo(a.substring(SA[i], SA[i]+LCP[i])) > 0){
                    maxsize = LCP[i];
                    result = a.substring(SA[i], SA[i]+LCP[i]);
                }
            }

            if(maxsize == 0) {
                System.out.print("No repetitions found!\n");
                continue;
            }
            int rcnt = 0;
            int pos = 0;
            while(true){
                int idx = a.indexOf(result, pos);
                if(idx != -1){
                    ++rcnt;
                    pos = idx+1;
                } else break;
            }

            System.out.format("%s %d\n", result, rcnt);
        }

    }
}
