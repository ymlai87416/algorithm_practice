package ctci.treegraph;
import javax.swing.*;
import java.util.*;

public class CheckBalanced {

    HashMap<TreeNode, Integer> depth;
    public boolean checkBalanced(TreeNode root){
        depth = new HashMap<>();

        getDepth(root);
        return checkBalancedHelper(root);
    }

    public int getDepth(TreeNode node){
        if(node == null) return 0;
        int leftD = getDepth(node.left);
        int rightD = getDepth(node.right);
        int curD = Math.max(leftD, rightD)+1;

        depth.put(node, curD);
        return curD;
    }

    private boolean checkBalancedHelper(TreeNode node){

        if(node.left != null && !checkBalancedHelper(node.left)) return false;
        if(node.right != null && !checkBalancedHelper(node.right)) return false;

        int leftD = node.left == null ? 0 : depth.get(node.left);
        int rightD = node.right == null ? 0 : depth.get(node.right);

        return (Math.abs(leftD - rightD) <= 1);
    }


    public static void main(String[] args) {
        CheckBalanced sol = new CheckBalanced();
        TreeNode tn1 = createBalancedTree1(4);
        System.out.println(tn1.toString());
        System.out.println(sol.checkBalanced(tn1));

        System.out.println("---------");

        TreeNode tn2 = createBalancedTree2(4);
        System.out.println(tn2.toString());
        System.out.println(sol.checkBalanced(tn2));

        System.out.println("---------");

        TreeNode tn3 = createNotBalancedTree(4);
        System.out.println(tn3.toString());
        System.out.println(sol.checkBalanced(tn3));
    }

    static int currentSession;


    public static TreeNode createBalancedTree1(int n){
        currentSession = new Random().nextInt();
        return createFullTree(n);
    }

    public static TreeNode createBalancedTree2(int n){
        currentSession = new Random().nextInt();
        TreeNode tn = Utility.getTreeNode(currentSession);
        tn.left = createFullTree(n-1);
        tn.right = createFullTree(n-2);

        return tn;
    }

    public static TreeNode createNotBalancedTree(int n){
        currentSession = new Random().nextInt();
        TreeNode tn = Utility.getTreeNode(currentSession);
        tn.left = createFullTree(n-1);
        tn.right = createFullTree(n-3);
        return tn;
    }

    public static TreeNode createFullTree(int n){
        if(n == 0) return null;
        TreeNode tn = Utility.getTreeNode(currentSession);
        tn.left = createFullTree(n-1);
        tn.right = createFullTree(n-1);

        return tn;
    }

}
