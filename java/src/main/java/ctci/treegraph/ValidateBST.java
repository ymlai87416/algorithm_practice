package ctci.treegraph;

import java.util.*;

public class ValidateBST {
    public boolean validateBST(TreeNode root){

        return validateBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //assume that no 2 nodes share same value
    private boolean validateBSTHelper(TreeNode root, int left, int right){

        if(root.left == null && root.right == null)
            return left <= root.val && root.val <= right;

        boolean result;
        if(root.left != null){
            result = validateBSTHelper(root.left, left, root.val);  //duplicate allow in left.
            if(!result) return false;
        }
        if(root.right != null){
            result = validateBSTHelper(root.right, root.val+1, right);
            if(!result) return false;
        }

        return true;
    }


    public static void main(String[] args) {

        ValidateBST test=  new ValidateBST();

        TreeNode tn1 = testCase1();
        System.out.println(tn1);
        System.out.println(test.validateBST(tn1));

        TreeNode tn2 = testCase2();
        System.out.println(tn2);
        System.out.println(test.validateBST(tn2));

        TreeNode tn3 = testCase3();
        System.out.println(tn3);
        System.out.println(test.validateBST(tn3));

        TreeNode tn4 = testCase4();
        System.out.println(tn4);
        System.out.println(test.validateBST(tn4));
    }

    private static TreeNode testCase1(){
        return Utility.createTree(Arrays.asList(20, 20, null, null, null).toArray(new Integer[0]));
    }

    private static TreeNode testCase2(){
        return Utility.createTree(Arrays.asList(20, null, 20, null, null).toArray(new Integer[0]));
    }

    private static TreeNode testCase3(){
        return Utility.createTree(Arrays.asList(20, 10, 30, null, 25, null, null, null, null).toArray(new Integer[0]));
    }

    private static TreeNode testCase4(){
        return Utility.createTree(Arrays.asList(20, 10, 30, 5, 15, null, null, 3, 7, null, 17,
                    null, null, null, null, null, null).toArray(new Integer[0]));
    }
}
