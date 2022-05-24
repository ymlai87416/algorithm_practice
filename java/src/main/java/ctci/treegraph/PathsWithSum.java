package ctci.treegraph;
import java.util.*;

public class PathsWithSum {
    HashMap<Integer, Integer> prefixSum;
    int result;
    int target;
    public int pathsWithSum(TreeNode root, int target){
        prefixSum = new HashMap<>();
        result = 0;
        this.target = target;
        helper(root, 0);

        return result;
    }

    private void helper(TreeNode root, int ps){
        int mySum = ps + root.val;

//we have to contribute result
        int diff = target - mySum;
        result += prefixSum.getOrDefault(diff, 0);

        prefixSum.put(mySum, prefixSum.getOrDefault(mySum, 0) + 1);

        if(root.left != null)
            helper(root.left, mySum);
        if(root.right != null)
            helper(root.right, mySum);

        prefixSum.put(mySum, prefixSum.getOrDefault(mySum, 0)-  1);
    }


    public static void main(String[] args) {
        PathsWithSum test=  new PathsWithSum();

        TreeNode tn = Utility.createTree(new Integer[] {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1, null, null,
            null, null, null, null, null, null});

        var result = test.pathsWithSum(tn, 8);

        System.out.println(result);
    }
}
