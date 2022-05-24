package ctci.treegraph;
import java.util.*;

//Have huge problem about data structure...
public class BSTSequences {
    List<List<Integer>> result;
    public List BSTSequence(TreeNode node){
        result = new ArrayList<>();
        Stack<TreeNode> candidates = new Stack<>();
        candidates.push(node);
        helper( candidates, new ArrayList<Integer>());

        return result;
    }

    private void helper(Stack<TreeNode> candidates, List<Integer> prefix){
        boolean isEmpty = true;
        for(int i=0; i<candidates.size(); ++i){
            if(candidates.get(i) == null) continue;
            TreeNode cur = candidates.get(i);
            isEmpty = false;
            //add this
            prefix.add(cur.val);
            candidates.pop();
            if(cur.left != null) candidates.push(cur.left);
            if(cur.right != null) candidates.push(cur.right);
            helper(candidates, prefix);
            candidates.pop();
            candidates.pop();
            candidates.push(cur);
            prefix.remove(prefix.size()-1);
        }

        if(isEmpty)
            result.add(new ArrayList<Integer>(prefix));
    }


    public static void main(String[] args) {
        BSTSequences test = new BSTSequences();
        TreeNode tn = Utility.createTree(new Integer[]{2, 1,3, null, null, null, null});
        var result = test.BSTSequence(tn);

        result.forEach(x -> System.out.println(x));
    }
}
