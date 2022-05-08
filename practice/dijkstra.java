public class dijkstra {
    
    /*
        input: Adjacent graph, source node
        output: a list of array
    */

    public List<Integer> dijkstra_impl(List<List<Integer>> adjList, int source){
        return null;
    } 

    private List<Integer> bellmonford_impl(List<List<Integer>> adjList, int source){
        return null;
    }


    public static void main(String[] args) {
        dijkstra d = new dijkstra();
        d.test();
    }

    //this is the test case 
    public void test(){
        int nTestA = 10;
        int nTestB = 10;

        for(int i=0; i<nTest; ++i){

            TestCase t = readTestcase(i);
            List<Integer> out1 = dijkstra_impl(t.adjList, t.source);
            List<Integer> out2 = dijkstra_ref(t.adjList, t.source);

            if(!compareOutput(out1, out2)){
                throw new RuntimeException("Test case " + i + " failed");
            }
        }

        for(int i=0; i<nTest; ++i){

            TestCase t = readTestcase(i);
            List<Integer> out1 = bellmonford_impl(t.adjList, t.source);
            List<Integer> out2 = bellmonford_ref(t.adjList, t.source);

            if(!compareOutput(out1, out2)){
                throw new RuntimeException("Test case " + i + " failed");
            }
        }
    }

    private TestCase readTestcase(int i){
       //do nothing 
       return null;
    }

    private boolean compareOutput(List<Integer> output1, List<Integer> output2){
        return true;
    }


    //reference question: 
    //this is the reference implementation
    public List<Integer> dijkstra_ref(List<List<Integer>> adjList, int source){
        return null;
    } 

    private List<Integer> bellmonford_ref(List<List<Integer>> adjList, int source){
        return null;
    }

}

class TestCase{
    List<List<Integer>> adjList;
    int source;
}

/*
To restore
*/