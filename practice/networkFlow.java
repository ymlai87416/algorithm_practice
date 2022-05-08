public class networkFlow {
    
    private int networkFlow_impl(List<List<Integer>> adjList, int source, int target){

    }

    public static void main(String[] args) {
        
    }

    private void test(){
        int nTest = 10;

        for(int i=0; i<nTest; ++i){

            TestCase t = readTestcase(i);
            int out1 = networkFlow_impl(t.adjList, t.source, t.target);
            int out2 = networkFlow_ref(t.adjList, t.source, t.target);

            if(out1 != out2){
                throw new RuntimeException("Test case " + i + " failed");
            }
        }
    }

    private int networkFlow_ref(List<List<Integer>> adjList, int source, int target){

    }

}

class TestCase{
    List<List<Integer>> adjList;
    int source;
    int target;
}