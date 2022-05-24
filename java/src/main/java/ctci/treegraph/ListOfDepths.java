package ctci.treegraph;
import jdk.jshell.execution.Util;

import java.util.*;
import java.util.stream.Collectors;

public class ListOfDepths {

    public List<List<TreeNode>> listOfDepths(TreeNode root){


        List<List<TreeNode>> result = new ArrayList<>();
        result.add(new ArrayList<TreeNode>());

        result.get(0).add(root);
        List<TreeNode> cur;
        List<TreeNode> next;
        cur = result.get(0);

        while(cur.size() != 0){
            next = new ArrayList<TreeNode>();
            for(int i=0; i<cur.size(); ++i){
                if (cur.get(i).left != null) next.add(cur.get(i).left);
                if (cur.get(i).right != null) next.add(cur.get(i).right);
            }

            if(next.size() > 0)
                result.add(next);
            cur = next;
        }
        return result;
    }


    public static void main(String[] args) {
        TreeNode tn = Utility.createRandomTree();
        ListOfDepths test = new ListOfDepths();
        var result = test.listOfDepths(tn);

        System.out.println(tn.toString());
        for(int i=0; i<result.size(); ++i){
            var result2 = result.get(i).stream().map(x -> x.val).collect(Collectors.toList());
            System.out.println(i + ": " + result2);
        }

    }
}
