package ctci.recursion;
import java.util.*;

public class Parens {

    char[] data;
    List<String> result;
    public List<String> parens(int N){
        data = new char[N*2];
        result = new ArrayList<String>();

        parensHelper(0, 0, N);

        return result;

    }

    private void parensHelper(int index, int op, int k){


        if(op == 0 && k == 0){
            result.add(new String(data) );
        }

        if(op > 0){
            data[index] = ')';
            parensHelper(index+1, op-1, k);
        }

        if(k > 0){
            data[index] = '(';
            parensHelper(index+1, op+1, k-1);
        }
    }

    public static void main(String[] args) {
        Parens test = new Parens();
        //1, 1, 2, 5, 14, 42, 132, 429, 1430
        var result = test.parens(4);
        System.out.println("size: " +result.size());
        result.forEach(x -> System.out.println(x));
    }
}
