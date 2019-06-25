package StringProcessing.LongestPrefixSuffix;

public class LongestPrefixSuffix2 {// Returns length of the longest prefix
    // which is also suffix and the two do
// not overlap. This function mainly is
// copy of computeLPSArray() in KMP Algorithm
    static int LengthlongestPrefixSuffix(String s)
    {
        int n = s.length();

        int lps[] = new int[n];

        // lps[0] is always 0
        lps[0] = 0;

        // Length of the previous
        // longest prefix suffix
        int len = 0;

        // Loop to calculate lps[i]
        // for i = 1 to n - 1
        int i = 1;
        while (i < n)
        {
            if (s.charAt(i) == s.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else
            {

                // This is tricky. Consider
                // the example. AAACAAAA
                // and i = 7. The idea is
                // similar to search step.
                if (len != 0)
                {
                    len = lps[len - 1];

                    // Also, note that we do
                    // not increment i here
                }

                // If len = 0
                else
                {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        int res = lps[n - 1];

        // Since we are looking for
        // non overlapping parts
        return (res > n / 2) ? n / 2 : res;
    }

    // Function that returns the prefix
    static String longestPrefixSuffix(String s)
    {
        // Get the length of the longest prefix
        int len = LengthlongestPrefixSuffix(s);

        // Stores the prefix
        String prefix = "";

        // Traverse and add charcaters
        for (int i = 0; i < len; i++)
            prefix += s.charAt(i);

        // Returns the prefix
        return prefix;
    }

    // Driver code
    public static void main(String[] args)
    {
        String s = "abcab";
        String ans = longestPrefixSuffix(s);
        if (ans == "")
            System.out.println("-1");
        else
            System.out.println(ans);
    }
}
