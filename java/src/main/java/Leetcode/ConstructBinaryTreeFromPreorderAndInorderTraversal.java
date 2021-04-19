package Leetcode;

/*
problem: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
level: medium
solution: the index of the List<List<>> represent the level, just have to keep track what level curr node is.

#tree #dfs

 */

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        Solution s = new Solution();
        TreeNode r = s.buildTree(preorder, inorder);
        System.out.println(r);
    }

    static
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //enhance using start, end ptr

    static
    class Solution {

        private String arrayToString(int[] a){
            String s = "[";
            for(int i=0; i<a.length; ++i)
                s += " " + a[i];
            s += " ]";
            return s;
        }

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            System.out.println("debug: " + arrayToString(preorder) + " " + arrayToString(inorder));
            if(preorder.length == 0)
                return null;
            else if(preorder.length == 1)
                return new TreeNode(preorder[0]);
            else {
                int[] leftPreorder;
                int[] rightPreorder;
                int[] leftInOrder;
                int[] rightInOrder;

                int root = preorder[0];
                int leftSize = 0;
                for (leftSize = 0; leftSize < inorder.length; ++leftSize) {
                    if (inorder[leftSize] == root) break;
                }

                leftPreorder = new int[leftSize];
                rightPreorder = new int[preorder.length - leftSize - 1];
                leftInOrder = new int[leftSize];
                rightInOrder = new int[preorder.length - leftSize - 1];

                for (int i = 0; i < leftSize; ++i) {
                    leftInOrder[i] = inorder[i];
                }

                for (int i = leftSize + 1; i < inorder.length; ++i) {
                    rightInOrder[i - leftSize - 1] = inorder[i];
                }

                int icnt =1;
                for (; icnt < leftSize+1; ++icnt) {
                    leftPreorder[icnt-1] = preorder[icnt];
                }

                for (; icnt < inorder.length; ++icnt) {
                    rightPreorder[icnt - leftSize - 1] = preorder[icnt];
                }

                /*
                int leftPtr = 0, rightPtr = 0;
                for (int i = 1; i < preorder.length; ++i) {
                    int c = preorder[i];
                    boolean inLeft = false;
                    for (int j = 0; j < leftPreorder.length; ++j) {
                        if (leftInOrder[j] == c) {
                            inLeft = true;
                            break;
                        }
                    }

                    if (inLeft)
                        leftPreorder[leftPtr++] = c;
                    else
                        rightPreorder[rightPtr++] = c;

                }
                */

                TreeNode result = new TreeNode(root);
                result.left = buildTree(leftPreorder, leftInOrder);
                result.right = buildTree(rightPreorder, rightInOrder);

                return result;
            }
        }
    }
}
