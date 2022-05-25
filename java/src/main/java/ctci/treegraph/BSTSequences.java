package ctci.treegraph;
import java.util.*;

//Have huge problem about data structure...
public class BSTSequences {
    public List BSTSequence(TreeNode node){
        return helper(node);
    }

    public List<List<Integer>> helper(TreeNode root){
        if(root == null){
            ArrayList<List<Integer>> r = new ArrayList<>();
            r.add(Collections.emptyList());
            return r;
        }

        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> leftResult = helper(root.left);
        List<List<Integer>> rightResult = helper(root.right);

        for(List<Integer> left: leftResult){

            for(List<Integer> right: rightResult){
                tempResult = new ArrayList<>();
                Stack<Integer> prefix = new Stack<Integer>();
                prefix.add(root.val);
                generateAllPossible(left, right, prefix);

                result.addAll(tempResult);
            }
        }

        return result;
    }

    List<List<Integer>> tempResult;

    private void generateAllPossible(List<Integer> arr1, List<Integer> arr2,
                                Stack<Integer> prefix){

        if(arr1.size() == 0 && arr2.size() == 0)
            tempResult.add(new ArrayList<>(prefix));

        if(arr1.size() > 0){
            prefix.push(arr1.get(0));
            generateAllPossible(arr1.subList(1, arr1.size()), arr2, prefix);
            prefix.pop();
        }

        if(arr2.size() > 0){
            prefix.push(arr2.get(0));
            generateAllPossible(arr1, arr2.subList(1, arr2.size()), prefix);
            prefix.pop();
        }

    }

    public static void main(String[] args) {
        BSTSequences test = new BSTSequences();
        TreeNode tn = Utility.createTree(new Integer[]{2, 1,3, null, null, null, null});
        var result = test.BSTSequence(tn);

        result.forEach(x -> System.out.println(x));

        tn = Utility.createTree(new Integer[]{50, 20, 60, 10, 25, null, 70, 5, 15, null,null, 65, 80,
            null, null, null,null, null,null, null, null});
        result = test.BSTSequence(tn);

        result.forEach(x -> System.out.println(x));

    }
}
