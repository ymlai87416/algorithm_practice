public class kmp {

    public static void main(String[] args) {
        kmp k = new kmp();
        k.test();
    }

    private void test(){
        Impl impl = new Impl();
        Ref ref = new Ref();
        
        int nTest = 10;

        for(int i=0; i<nTest; ++i){

            TestCase t = readTestcase(i);
            impl.kmpPreProcess(t.pattern);
            ref.kmpPreProcess(t.pattern);

            List<Integer> out1 = impl.kmpSearch(t.text);
            List<Integer> out2 = ref.kmpSearch(t.text);

            if(!compareOutput(out1, out2)){
                throw new RuntimeException("Test case " + i + " failed");
            }
        }
    }  

    private TestCase readTestcase(int i){
        //do nothing 
        return null;
    }

    private List<Integer> kmpSearch_ref(String s, String pattern){
        return null;
    }
}

class Impl{
    //please define global variables here

    void kmpPreProcess(String s) {
        //find the longest prefix of the suffix
    }

    List<Integer> kmpSearch(String s) {
        
        return null;
    }

}

class Ref{
    final int MAX_N = 1_000_001;
    char[] T = new char[MAX_N];
    char[] P = new char[MAX_N]; // T = text, P = pattern
    int[] b = new int[MAX_N];
    int n, m; // b = back table, n = length of T, m = length of P

    void kmpPreprocess(String s) { // call this before calling kmpSearch()

        for(int i=0; i<s.length(); ++i) P[i] = s.charAt(i);
        m = s.length();

        int i = 0, j = -1;
        b[0] = -1; // starting values
        while (i < m) { // pre-process the pattern string P
            while (j >= 0 && P[i] != P[j]) j = b[j]; // different, reset j using b
            i++;
            j++; // if same, advance both pointers
            b[i] = j; // observe i = 8, 9, 10, 11, 12, 13 with j = 0, 1, 2, 3, 4, 5
        }
    } // in the example of P = "SEVENTY SEVEN" above

    List<Integer> kmpSearch(String s) { // this is similar as kmpPreprocess(), but on string T
        
        for(int i=0; i<s.length(); ++i) T[i] = s.charAt(i);
        n = s.length();
        
        int i = 0, j = 0; // starting values
        while (i < n) { // search through string T
            while (j >= 0 && T[i] != P[j]) j = b[j]; // different, reset j using b
            i++;
            j++; // if same, advance both pointers
            if (j == m) { // a match found when j == m
                System.out.format("%s is found at index %d in T\n", String.valueOf(P, 0, m), i - j);
                j = b[j]; // prepare j for the next possible match
            }
        }
    }
}

class TestCase{
    String text;
    String pattern;
}