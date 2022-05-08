public class kmp {

    private List<Integer> kmpSearch_impl(String s, String pattern){
        return null;
    }

    public static void main(String[] args) {
        kmp k = new kmp();
        k.test();
    }

    private void test(){
        int nTest = 10;

        for(int i=0; i<nTest; ++i){

            TestCase t = readTestcase(i);
            List<Integer> out1 = kmpSearch_impl(t.adjList, t.source);
            List<Integer> out2 = kmpSearch_ref(t.adjList, t.source);

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

class TestCase{
    String s;
    String pattern;
}