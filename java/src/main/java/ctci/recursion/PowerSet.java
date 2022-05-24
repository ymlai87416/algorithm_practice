package ctci.recursion;
import java.util.*;

public class PowerSet {

    List<List<Integer>> powerSet;
    List<Integer> set;

    public List<List<Integer>>  findPowerSet(List<Integer> set){
        this.set = set;
        powerSet = new ArrayList<>();

        powerSetHelper(0, new Stack<Integer>());

        return powerSet;
    }

    private void powerSetHelper(int index, Stack<Integer> stack){
        if(index == set.size()){
            powerSet.add(new ArrayList<Integer>(stack));
            return;
        }

        //not add
        powerSetHelper(index+1, stack);

        stack.push(set.get(index));
        powerSetHelper(index+1, stack);
        stack.pop();
    }

    public static void main(String[] args) {
        List<Integer> data = List.of(1,2,3,4,5);
        PowerSet test = new PowerSet();

        var result = test.findPowerSet(data);
        System.out.println("Power set: ");
        result.forEach(x -> System.out.println(x));
    }
}
