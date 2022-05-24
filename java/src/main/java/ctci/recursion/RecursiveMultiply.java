package ctci.recursion;

public class RecursiveMultiply {

    public int recursiveMultiply(int a, int b){
        if(b == 0) return 0;
        if(b == 1) return a;
        int bhalf = b >> 1;
        int subR = recursiveMultiply(a, bhalf);
        if(bhalf + bhalf == b)
            return subR+subR;
        else
            return  subR+subR+a;
    }

    public static void main(String[] args) {
        RecursiveMultiply test = new RecursiveMultiply();
        System.out.println("31x35=" +test.recursiveMultiply(31, 35));
    }
}
