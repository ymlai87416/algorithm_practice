package GoogleCodeJam.Y2009.Round1B.B;


/**
 * Created by Tom on 9/4/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2009\\Round1B\\B\\B-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    void swap(char[] c, int a, int b)
    {
        if (a == b)
            return;
        else{
            char t = c[a];
            c[a] = c[b];
            c[b] = t;
        }
    }
    void rev(char[] s, int l, int r)
    {
        while (l < r)
            swap(s, l++, r--);
    }

    int bsearch(char[] s, int l, int r, int key)
    {
        int index = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (s[mid] <= key)
                r = mid - 1;
            else {
                l = mid + 1;
                if (index == -1 || s[index] >= s[mid])
                    index = mid;
            }
        }
        return index;
    }

    private boolean nextpermutation(char[] s)
    {
        int len = s.length, i = len - 2;
        while (i >= 0 && s[i] >= s[i + 1])
            --i;
        if (i < 0)
            return false;
        else {
            int index = bsearch(s, i + 1, len - 1, s[i]);
            swap(s, i, index);
            rev(s, i + 1, len - 1);
            return true;
        }
    }

    private String solve(String s) {
        char[] c = s.toCharArray();
        boolean ok = nextpermutation(c);
        String ans = null;
        if(!ok) {
            Arrays.sort(c);
            int zc = 0;
            int f = -1;
            for(int i=0; i<c.length; ++i) {
                if (c[i] == '0') zc++;
                else {
                    f = i;
                    break;
                }
            }
            ans = c[f] + "";
            for(int i=0; i<zc+1; ++i)
                ans += "0";
            for(int i=f+1; i<c.length; ++i)
                ans = ans + c[i];
        }
        else
            ans = new String(c);
        return ans;
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            String s = sc.nextLine();
            String ans = solve(s);
            System.out.println(ans);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

}