package Leetcode;

import java.util.*;

public class SerializeAndDeserializeBinaryTree2 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "()";
        //root  (left )  (right)
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(root.val);

        if(!(root.left == null && root.right == null)){
            if(root.left != null){
                String content = serialize(root.left);
                sb.append(content);
            }
            else{
                sb.append("()");
            }

            if(root.right != null){
                String content = serialize(root.right);
                sb.append(content);
            }
            else{
                sb.append("()");
            }
        }

        sb.append(")");
        //System.out.println("S:" + sb.toString());
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        List<String> tt =  tokenized(data);
        Stack<Object> st = new Stack<Object>();
        int lr= 0;

        for(int i=0; i<tt.size(); ++i){
            String ct = tt.get(i);
            if(Character.isDigit(ct.charAt(0)) || ct.charAt(0) == '-')
                ((TreeNode)st.peek()).val = Integer.parseInt(ct);
            else if(ct.compareTo("(") == 0){
                st.push("(");
                st.push(new TreeNode(-9999));
            }
            else if(ct.compareTo(")") == 0){
                //unpop all the Node and add all the node to the first node which sit on top of (
                List<TreeNode> nodes = new ArrayList<TreeNode>();
                while(!st.isEmpty()){
                    Object obj = st.pop();
                    if(obj == null || obj instanceof TreeNode)
                        nodes.add((TreeNode)obj);
                    else{
                        TreeNode r = null;
                        if(nodes.size() == 1){
                            r = nodes.get(nodes.size()-1);
                            if(r.val == -9999) r = null;
                        }
                        //there should be only 3 nodes
                        else if(nodes.size() == 3){
                            r = nodes.get(2);
                            r.left =nodes.get(1);
                            r.right =nodes.get(0);
                        }

                        //push it back to the stack
                        st.push(r);
                        break;
                    }
                }
            }
        }

        return (TreeNode)st.pop();
    }

    private List<String> tokenized(String s){
        int number = -1;
        boolean isNeg = false;
        List<String> result = new ArrayList<String>();
        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            if(c == '-')
                isNeg = true;
            else if(Character.isDigit(c))
                number= number==-1 ? (c-'0') : number * 10 + (c-'0');
            else if(c == '('){
                if(number != -1){
                    result.add(String.valueOf(number * (isNeg?-1: 1)));
                    number=-1;
                    isNeg = false;
                }
                result.add("(");
            }
            else if(c == ')'){
                if(number != -1) {
                    result.add(String.valueOf(number * (isNeg?-1: 1)));
                    number=-1;
                    isNeg = false;
                }
                result.add(")");
            }
        }


        return result;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new  TreeNode(4);
        root.right.right = new TreeNode(5);

        SerializeAndDeserializeBinaryTree2 cc = new SerializeAndDeserializeBinaryTree2();


        root = new TreeNode(-1);
        root.right = new TreeNode(-2);

        String a = cc.serialize(root);
        System.out.println(a);
        TreeNode b = cc.deserialize(a);
        String c = cc.serialize(b);

        System.out.println(c);
        System.out.format("Same? : %s\n", String.valueOf(a.compareTo(c)==0));
    }

    static
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
